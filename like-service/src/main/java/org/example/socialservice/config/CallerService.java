package org.example.socialservice.config;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;

@Service
public class CallerService {

    private final AuthzClient client;

    public CallerService(AuthzClient client) {
        this.client = client;
    }

    public boolean isFollower(String followerUsername) {
        try {
            ResponseEntity<Void> response =
                    client.checkUserFollower(followerUsername);

            return response.getStatusCode().is2xxSuccessful(); // return true if follower
        } catch (FeignException.NotFound e) {
            return false; // not a follower
        }
    }
}


