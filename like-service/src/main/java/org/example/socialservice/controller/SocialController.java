package org.example.socialservice.controller;

import java.util.List;

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

    public SocialController(SocialService service) {
        this.service = service;
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

    @PostMapping("/users/{username}/follow")
    public ResponseEntity<Void> follow(@PathVariable String username,
                       @AuthenticationPrincipal Jwt jwt) {
        service.follow(currentUser(jwt), username);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{username}/follow")
    public ResponseEntity<Void> unfollow(@PathVariable String username,
                         @AuthenticationPrincipal Jwt jwt) {
        service.unfollow(currentUser(jwt), username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{username}/followers")
    public ResponseEntity<List<String>> followers(@PathVariable String username) {
        return ResponseEntity.ok(service.followers(username));
    }

    @GetMapping("/users/{username}/following")
    public ResponseEntity<List<String>> following(@PathVariable String username) {
    	return ResponseEntity.ok(service.following(username));
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

