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
@CrossOrigin(origins = "*")
public class SocialController {

    private final SocialService socialService;
    
    @Autowired
    private final CallerService callerService;

    public SocialController(SocialService socialService, CallerService callerService) {
        this.socialService = socialService;
        this.callerService = callerService;
    }


    // Follow user
    @PostMapping("/follow/{followeeUsername}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Follow> followUser(@PathVariable String followeeUsername, @AuthenticationPrincipal Jwt jwt) {
        String followerUsername = jwt.getSubject();

        if(followerUsername.equals(followeeUsername)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 for self-follow
        }

        if (!callerService.isFollower(followeeUsername)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if user to be followed does not exist
        }

        try {
            Follow follow = socialService.followUser(followerUsername, followeeUsername);
            return ResponseEntity.ok(follow);
        } catch (DuplicateFollowException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // explain conflict to client
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	 
    // Unfollow user
    @DeleteMapping("/follow/{followeeUsername}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> unfollowUser(@PathVariable String followeeUsername, @AuthenticationPrincipal Jwt jwt) {
        String followerUsername = jwt.getSubject();

        if(followerUsername.equals(followeeUsername)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 for self-unfollow
        }

        if (!callerService.isFollower(followeeUsername)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if user to be unfollowed does not exist
        }

        try {
        	socialService.unfollowUser(followerUsername, followeeUsername);
            return ResponseEntity.noContent().build();
        } catch (DuplicateFollowException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // explain conflict to client
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Get followers of username
    @GetMapping("/followers/{followeeUsername}")
    public ResponseEntity<List<String>> getFollowers(@PathVariable("followeeUsername") String followeeUsername) throws Exception {
    	List<String> followers = socialService.getFollowers(followeeUsername);
        return ResponseEntity.ok(followers);
    }

    // Get who the user is following
    @GetMapping("/following/{followerUsername}")
    public ResponseEntity<List<String>> getFollowing(@PathVariable("followerUsername") String followerUsername) {
    	List<String> following = socialService.getFollowing(followerUsername);
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
    
    // Get number of likes for post with postId = {postId}
    @GetMapping("/likes/post/{postId}/count")
    public ResponseEntity<Integer> getLikesCountForPost(@PathVariable Long postId) {
    	List<Like> likes = socialService.getLikesForPost(postId);
    	int numLikes = likes.size();
        return ResponseEntity.ok(numLikes);
    }

    // Get total likes for user
    @GetMapping("/likes/user/{username}")
    public ResponseEntity<List<Like>> getLikesByUser(@PathVariable String username) {
    	List<Like> userLikes = socialService.getLikesByUser(username);
        return ResponseEntity.ok(userLikes);
    }
    
    // Get total number of likes for user
    @GetMapping("/likes/user/{username}/count")
    public ResponseEntity<Integer> getLikesCountByUser(@PathVariable String username) {
    	List<Like> userLikes = socialService.getLikesByUser(username);
    	int numUserLikes = userLikes.size();
        return ResponseEntity.ok(numUserLikes);
    }
}
