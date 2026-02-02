package org.example.socialservice.controller;

import java.util.List;

import org.example.socialservice.dtos.AuthServiceClient;
import org.example.socialservice.service.SocialService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social")
public class SocialController {

    private final SocialService service;

    private final AuthServiceClient authServiceClient;
    
    public SocialController(SocialService service, AuthServiceClient authServiceClient) {
        this.service = service;
        this.authServiceClient = authServiceClient;
    }

    private String currentUser(Jwt jwt) {
        return jwt.getSubject(); // username
    }

    /* ---------- Likes ---------- */

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<Void> like(@PathVariable Long postId,
                     @AuthenticationPrincipal Jwt jwt) {
        service.likePost(postId, currentUser(jwt));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{postId}/like")
    public ResponseEntity<Void> unlike(@PathVariable Long postId,
                       @AuthenticationPrincipal Jwt jwt) {
        service.unlikePost(postId, currentUser(jwt));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/{postId}/likes/count")
    public ResponseEntity<Long> count(@PathVariable Long postId) {
        return ResponseEntity.ok(service.likeCount(postId));
    }

    /* ---------- Follows ---------- */

    @PostMapping("/users/{followeeId}/follow")
    public ResponseEntity<Void> follow(@PathVariable Long followeeId, @AuthenticationPrincipal Jwt jwt) {
    	Long followerId = authServiceClient.getUserId(jwt.getSubject());
    	service.followUser(followerId, followeeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{followeeId}/follow")
    public ResponseEntity<Void> unfollow(@PathVariable Long followeeId, @AuthenticationPrincipal Jwt jwt) {
        Long followerId = authServiceClient.getUserId(jwt.getSubject());
        service.unfollowUser(followerId, followeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/followers")
    public ResponseEntity<List<Long>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getFollowers(userId)
       );
    }

    @GetMapping("/users/{userId}/following")
    public ResponseEntity<List<Long>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getFollowing(userId)
       );
    }
    
    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<Long> getFollowerCount(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getFollowerCount(userId));
    }

    @GetMapping("/users/{userId}/following/count")
    public ResponseEntity<Long> getFollowingCount(@PathVariable Long userId) {
    	return ResponseEntity.ok(service.getFollowingCount(userId));
    }
}

