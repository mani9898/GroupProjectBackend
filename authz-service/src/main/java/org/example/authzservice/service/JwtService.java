package org.example.authzservice.service;

import com.nimbusds.jose.jwk.RSAKey;
import org.example.authzservice.entity.MediaUser;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final RSAKey rsaKey;

    public JwtService(JwtEncoder jwtEncoder, RSAKey rsaKey) {
        this.jwtEncoder = jwtEncoder;
        this.rsaKey = rsaKey;
    }

    public String generateToken(MediaUser user) {
        Instant now = Instant.now();
        long expiry = 3600L; // Token valid for 1 hour

        // Prefix ROLE_ for Spring Security
        List<String> authorities = List.of("ROLE_" + user.getRole());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("http://localhost:9000")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(user.getUsername())
                .claim("scope", authorities)   // Use 'scope' claim Spring understands by default
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public RSAKey getRsaKey() {
        return rsaKey;
    }
}

