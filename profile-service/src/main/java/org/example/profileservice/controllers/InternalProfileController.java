package org.example.profileservice.controllers;

import org.example.profileservice.dtos.FollowerDto;
import org.example.profileservice.dtos.ProfileDto;
import org.example.profileservice.entities.Profile;
import org.example.profileservice.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/profiles")
public class InternalProfileController {
    private ProfileService profileService;

    public InternalProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/followers" )
    public ResponseEntity<Iterable<FollowerDto>> getProfiles(@RequestBody List<Long> followerIds) {
        List<Profile> profiles = profileService.getProfiles(followerIds);
        List<FollowerDto> followerDtos = profiles.stream().map(profile -> new FollowerDto(
                profile.getId(),
                profile.getUsername(),
                profile.getDisplayName(),
                profile.getProfilePictureUrl()
        )).toList();
        return ResponseEntity.ok(followerDtos);
    }
}
