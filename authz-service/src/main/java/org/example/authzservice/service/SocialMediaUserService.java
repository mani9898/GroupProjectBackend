package org.example.authzservice.service;



import org.example.authzservice.entity.MediaUser;
import org.example.authzservice.entity.UserStatus;
import org.example.authzservice.exceptions.InvalidUserCred;
import org.example.authzservice.exceptions.UserAlreadyExistsException;
import org.example.authzservice.repo.SocialMediaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class SocialMediaUserService {

    private static final int MAX_ATTEMPTS = 5;
    private static final long ATTEMPT_WINDOW_MINUTES = 15; // reset attempts after this idle time
    private static final long BLOCK_MINUTES = 15; // lock duration
    @Autowired
    JwtService jwtService;
    @Autowired
    private SocialMediaUserRepository socialMediaUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional()
    public MediaUser register(MediaUser mediaUser) throws UserAlreadyExistsException {
        String username = mediaUser.getUsername();
        String password = mediaUser.getPassword();
        if (username == null || password == null) {
            throw new RuntimeException("Username and password must not be null");
        }

        if (socialMediaUserRepository.findByUsername(mediaUser.getUsername()) != null) {
            List<String> suggestions = generateUsernameSuggestions(username);
            throw new UserAlreadyExistsException("This username is taken. Here are some suggestions", suggestions);
        }
        if (socialMediaUserRepository.findByEmail(mediaUser.getEmail()) != null) {
            throw new UserAlreadyExistsException("This email is taken", new ArrayList<>());
        }
        mediaUser.setRole("USER");
        mediaUser.setUpdated(Instant.now());
        mediaUser.setStatus(UserStatus.ACTIVE);
        mediaUser.setPassword(passwordEncoder.encode(mediaUser.getPassword()));
        return socialMediaUserRepository.save(mediaUser);

    }

    private List<String> generateUsernameSuggestions(String username) {
        String base;
        if (username == null || username.isBlank()) {
            base = "user";
        } else {
            base = username.length() >= 3 ? username.substring(0, 3) : username;
        }

        base = base.toLowerCase();
        List<String> suggestions = new ArrayList<>(3);
        Random rnd = new Random();
        int attempts = 0;

        while (suggestions.size() < 3 && attempts < 10000) {
            attempts++;
            String candidate = base + (attempts); // incrementing suffix ensures termination
            if (socialMediaUserRepository.findByUsername(candidate) == null && !suggestions.contains(candidate)) {
                suggestions.add(candidate);
                continue;
            }
            String randCandidate = base + (rnd.nextInt(900) + 100);
            if (socialMediaUserRepository.findByUsername(randCandidate) == null && !suggestions.contains(randCandidate)) {
                suggestions.add(randCandidate);
            }
        }
        return suggestions;

    }

    public Map<String, String> login(String username, String password) {
        if (username == null || password == null) {
            throw new InvalidUserCred("USERNAME_AND_PASSWORD_MISSING", "username or password is null");
        }

        MediaUser mediaUser = socialMediaUserRepository.findByUsername(username);

        if (mediaUser == null) {
            throw new InvalidUserCred("INVALID_CREDENTIALS", "username or password is incorrect");
        }

        Instant now = Instant.now();

        // Check if account is currently blocked
        if (mediaUser.getBlockedUntil() != null && now.isBefore(mediaUser.getBlockedUntil())) {
            throw new InvalidUserCred("USER_BLOCKED", "Account is locked until " + mediaUser.getBlockedUntil().toString());
        }

        // Reset attempts if last update is older than ATTEMPT_WINDOW_MINUTES
        Instant lastUpdated = mediaUser.getUpdated();
        if (lastUpdated == null || lastUpdated.isBefore(now.minus(Duration.ofMinutes(ATTEMPT_WINDOW_MINUTES)))) {
            mediaUser.setAttempted(0);
        }

        // Verify password
        if (!passwordEncoder.matches(password, mediaUser.getPassword())) {
            int attempts = mediaUser.getAttempted() + 1;
            mediaUser.setAttempted(attempts);
            mediaUser.setUpdated(now);

            if (attempts >= MAX_ATTEMPTS) {
                mediaUser.setBlockedUntil(now.plus(Duration.ofMinutes(BLOCK_MINUTES)));
            }

            socialMediaUserRepository.save(mediaUser);

            if (attempts >= MAX_ATTEMPTS) {
                throw new InvalidUserCred("ACCOUNT_LOCKED", "Too many failed attempts. Account locked until " + mediaUser.getBlockedUntil().toString());
            } else {
                throw new InvalidUserCred("INVALID_CREDENTIALS", "username or password is incorrect");
            }
        }

        // Successful login: reset counters and persist
        mediaUser.setAttempted(0);
        mediaUser.setBlockedUntil(null);
        mediaUser.setLoggedIn(now);
        mediaUser.setUpdated(now);
        socialMediaUserRepository.save(mediaUser);

        String token = jwtService.generateToken(mediaUser);
        return Map.of("token", token);
    }
}