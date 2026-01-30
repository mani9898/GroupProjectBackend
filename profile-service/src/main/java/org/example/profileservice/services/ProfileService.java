package org.example.profileservice.services;


import org.example.profileservice.entities.Profile;
import org.example.profileservice.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }

    public Profile updateProfile(Long id, Profile updatedProfile) {
        if (this.profileRepository.existsById(id)) {
            return profileRepository.save(updatedProfile);
        } else {
            throw new IllegalArgumentException("Profile not found");
        }
    }

    public List<Profile> getProfiles(List<Long> followerIds) {
        return profileRepository.findAllById(followerIds);
    }
}
