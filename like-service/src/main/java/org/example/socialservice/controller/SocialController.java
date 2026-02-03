package org.example.socialservice.controller;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.Like;
import org.example.socialservice.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/follow/{personFollowing}/{personBeingFollowed}")
    public ResponseEntity<Follow> followUser(@PathVariable String personFollowing, @PathVariable String personBeingFollowed) {
    	Follow follow = new Follow();
    	if (callerService.isFollower(personFollowing)) {
    		 follow = socialService.followUser(personFollowing, personBeingFollowed);
    	} else {
    		return ResponseEntity.status(403).build();
    	}
        return ResponseEntity.ok(follow);
    }

    // Get followers of username
    @GetMapping("/followers/{username}")
    public List<Follow> getFollowers(@PathVariable("username") String user) {
        return socialService.getFollowers(user);
    }

    // Get who the user is following
    @GetMapping("/following/{username}")
    public List<Follow> getFollowing(@PathVariable("username") String follower) {
        return socialService.getFollowing(follower);
    }

    // Post a like on a user's post
    @PostMapping("/like/{postId}/{username}")
    public Like likePost(@PathVariable Long postId, @PathVariable String username) {
        return socialService.likePost(postId, username);
    }

    // Get likes for post with postId = {postId}
    @GetMapping("/likes/post/{postId}")
    public List<Like> getLikesForPost(@PathVariable Long postId) {
        return socialService.getLikesForPost(postId);
    }

    // Get total likes for user
    @GetMapping("/likes/user/{username}")
    public List<Like> getLikesByUser(@PathVariable String username) {
        return socialService.getLikesByUser(username);
    }
}




