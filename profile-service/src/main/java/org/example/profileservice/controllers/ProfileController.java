package org.example.profileservice.controllers;

import org.example.profileservice.dtos.ProfileDto;

import org.example.profileservice.dtos.RequestProfile;
import org.example.profileservice.entities.Profile;
import org.example.profileservice.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        ProfileDto profileDto = new ProfileDto(profile.getId(),
                profile.getUsername(), profile.getAboutMe(), profile.getDisplayName(),
                profile.getProfilePictureUrl(), profile.getLocation(), profile.getBirthdate(),
                profile.getGender(), profile.getSecondaryImageUrl(), profile.getPhoneNumber());
        return ResponseEntity.ok(profileDto);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody RequestProfile requestProfile,@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        if (username.equalsIgnoreCase(requestProfile.username())) {
            Profile profile = profileService.createProfile(new Profile(
                    requestProfile.username(),
                    requestProfile.aboutMe(),
                    requestProfile.displayName(),
                    requestProfile.profilePictureUrl(),
                    requestProfile.location(),
                    requestProfile.birthdate(),
                    requestProfile.gender(),
                    requestProfile.secondaryImageUrl(),
                    requestProfile.phoneNumber()
            ));
            ProfileDto profileDto = new ProfileDto(profile.getId(),
                    profile.getUsername(), profile.getAboutMe(), profile.getDisplayName(),
                    profile.getProfilePictureUrl(), profile.getLocation(), profile.getBirthdate(),
                    profile.getGender(), profile.getSecondaryImageUrl(), profile.getPhoneNumber());
            return ResponseEntity.ok(profileDto);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable Long id, @RequestBody ProfileDto profileDto, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        if (username.equalsIgnoreCase(profileDto.username())) {

            Profile profile = profileService.updateProfile(
                id,
                new Profile(
                        profileDto.username(),
                        profileDto.aboutMe(),
                        profileDto.displayName(),
                        profileDto.profilePictureUrl(),
                        profileDto.location(),
                        profileDto.birthdate(),
                        profileDto.gender(),
                        profileDto.secondaryImageUrl(),
                        profileDto.phoneNumber()
                ));
        ProfileDto updatedProfileDto = new ProfileDto(profile.getId(),
                profile.getUsername(), profile.getAboutMe(), profile.getDisplayName(),
                profile.getProfilePictureUrl(), profile.getLocation(), profile.getBirthdate(),
                profile.getGender(), profile.getSecondaryImageUrl(), profile.getPhoneNumber());
        return ResponseEntity.ok(updatedProfileDto);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping
    public ResponseEntity<ProfileDto> searchProfileByUsername(@RequestParam String username) {
        Profile profile = profileService.getByUsername(username);
        if(profile == null){
            return ResponseEntity.notFound().build();
        }
        ProfileDto profileDto = new ProfileDto(profile.getId(),
                profile.getUsername(), profile.getAboutMe(), profile.getDisplayName(),
                profile.getProfilePictureUrl(), profile.getLocation(), profile.getBirthdate(),
                profile.getGender(), profile.getSecondaryImageUrl(), profile.getPhoneNumber());
        return ResponseEntity.ok(profileDto);
    }

}
