package org.example.authzservice.controller;

import org.example.authzservice.service.SocialMediaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/backend")
public class AuthzController {

    @Autowired
    SocialMediaUserService socialMediaUserService;

    @PostMapping("/follower")
    public ResponseEntity<Void> checkUserFollower(
            @RequestParam String followerUsername) {

        boolean isFollower = socialMediaUserService.getMediaUser(followerUsername);

        if (isFollower) {
            return ResponseEntity.ok().build();     // 200
        }
        return ResponseEntity.notFound().build();  // 404
    }
}
