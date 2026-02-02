package org.example.socialservice.controller;

import java.util.List;

import org.example.socialservice.service.SocialService;
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
    public void like(@PathVariable Long postId,
                     @AuthenticationPrincipal Jwt jwt) {
        service.likePost(postId, currentUser(jwt));
    }

    @DeleteMapping("/posts/{postId}/like")
    public void unlike(@PathVariable Long postId,
                       @AuthenticationPrincipal Jwt jwt) {
        service.unlikePost(postId, currentUser(jwt));
    }

    @GetMapping("/posts/{postId}/likes/count")
    public long count(@PathVariable Long postId) {
        return service.likeCount(postId);
    }

    /* ---------- Follows ---------- */

    @PostMapping("/users/{username}/follow")
    public void follow(@PathVariable String username,
                       @AuthenticationPrincipal Jwt jwt) {
        service.follow(currentUser(jwt), username);
    }

    @DeleteMapping("/users/{username}/follow")
    public void unfollow(@PathVariable String username,
                         @AuthenticationPrincipal Jwt jwt) {
        service.unfollow(currentUser(jwt), username);
    }

    @GetMapping("/users/{username}/followers")
    public List<String> followers(@PathVariable String username) {
        return service.followers(username);
    }

    @GetMapping("/users/{username}/following")
    public List<String> following(@PathVariable String username) {
        return service.following(username);
    }
    
    @GetMapping("/users/{userId}/followers/count")
    public long getFollowerCount(@PathVariable Long userId) {
        return service.getFollowerCount(userId);
    }

    @GetMapping("/users/{userId}/following/count")
    public long getFollowingCount(@PathVariable Long userId) {
        return service.getFollowingCount(userId);
    }
}

