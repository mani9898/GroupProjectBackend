package org.example.socialservice.controller;

import org.example.socialservice.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {

    private FollowService followService;

    @PostMapping("/{followeeId}/follow")
    public ResponseEntity<Void> follow(@PathVariable Long followeeId, @AuthenticationPrincipal Jwt jwt) {
        Long followerId = Long.valueOf(jwt.getSubject());
        followService.followUser(followerId, followeeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{followeeId}/follow")
    public ResponseEntity<Void> unfollow(@PathVariable Long followeeId, @AuthenticationPrincipal Jwt jwt) {
        Long followerId = Long.valueOf(jwt.getSubject());
        followService.unfollowUser(followerId, followeeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<Long>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<Long>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowing(userId));
    }
}
