package org.example.socialservice.controller;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.Like;
import org.example.socialservice.exceptions.DuplicateFollowException;
import org.example.socialservice.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.example.socialservice.config.CallerService;
import java.util.List;

@RestController
@RequestMapping("/api/social")
public class SocialController {

    private final SocialService socialService;
    
    @Autowired
    private final CallerService callerService;

    public SocialController(SocialService socialService, CallerService callerService) {
        this.socialService = socialService;
        this.callerService = callerService;
    }


    // Follow user
    @PostMapping("/follow/{personBeingFollowed}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Follow> followUser(@PathVariable String personBeingFollowed, @AuthenticationPrincipal Jwt jwt) {
        String follower = jwt.getSubject();

        if(follower.equals(personBeingFollowed)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 for self-follow
        }

        if (!callerService.isFollower(personBeingFollowed)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if user to be followed does not exist
        }

        try {
            Follow follow = socialService.followUser(follower, personBeingFollowed);
            return ResponseEntity.ok(follow);
        } catch (DuplicateFollowException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // explain conflict to client
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Get followers of username
    @GetMapping("/followers/{username}")
    public ResponseEntity<List<Follow>> getFollowers(@PathVariable("username") String user) throws Exception {
    	List<Follow> followers = socialService.getFollowers(user);
        return ResponseEntity.ok(followers);
    }

    // Get who the user is following
    @GetMapping("/following/{username}")
    public ResponseEntity<List<Follow>> getFollowing(@PathVariable("username") String follower) {
    	List<Follow> following = socialService.getFollowing(follower);
        return ResponseEntity.ok(following);
    }

    // Post a like on a user's post
    @PostMapping("/like/{postId}/{username}")
    public ResponseEntity<Like> likePost(@PathVariable Long postId, @PathVariable String username) {
    	Like like = socialService.likePost(postId, username);
        return ResponseEntity.ok(like);
    }
    
    // Unlike a user's post
    @DeleteMapping("/like/{postId}/{username}")
    public ResponseEntity<HttpStatus> unlikePost(@PathVariable Long postId, @PathVariable String username) {
    	socialService.unlikePost(postId, username);
    	return ResponseEntity.noContent().build();
    }

    // Get likes for post with postId = {postId}
    @GetMapping("/likes/post/{postId}")
    public ResponseEntity<List<Like>> getLikesForPost(@PathVariable Long postId) {
    	List<Like> likes = socialService.getLikesForPost(postId);
        return ResponseEntity.ok(likes);
    }

    // Get total likes for user
    @GetMapping("/likes/user/{username}")
    public ResponseEntity<List<Like>> getLikesByUser(@PathVariable String username) {
    	List<Like> userLikes = socialService.getLikesByUser(username);
        return ResponseEntity.ok(userLikes);
    }
}
