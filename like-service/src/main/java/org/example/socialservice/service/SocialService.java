package org.example.socialservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.FollowId;
import org.example.socialservice.entity.Like;
import org.example.socialservice.entity.LikeId;
import org.example.socialservice.repository.FollowRepository;
import org.example.socialservice.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SocialService {

    private final LikeRepository likeRepo;
    private final FollowRepository followRepo;

    public SocialService(LikeRepository likeRepo, FollowRepository followRepo) {
        this.likeRepo = likeRepo;
        this.followRepo = followRepo;
    }

    /* ---------- Likes ---------- */

    public void likePost(Long postId, String username) {
        LikeId id = new LikeId();
        id.setPostId(postId);
        id.setUsername(username);

        if (!likeRepo.existsById(id)) {
            likeRepo.save(new Like(postId, username));
        }
    }

    public void unlikePost(Long postId, String username) {
        LikeId id = new LikeId();
        id.setPostId(postId);
        id.setUsername(username);
        likeRepo.deleteById(id);
    }

    public long likeCount(Long postId) {
        return likeRepo.countByIdPostId(postId);
    }

    /* ---------- Follows ---------- */

    @Transactional
    public void followUser(Long followerId, Long followeeId) {

        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("You cannot follow yourself");
        }

        // Construct composite key
        FollowId followId = new FollowId(followerId, followeeId);

        // Check if already following
        if (followRepo.existsById(followId)) {
            return; // already following, no action
        }

        // Create new Follow entity
        Follow follow = new Follow();
        follow.setId(followId);
        follow.setCreatedAt(LocalDateTime.now());

        followRepo.save(follow);
    }

    /**
     * Unfollow a user
     */
    @Transactional
    public void unfollowUser(Long followerId, Long followeeId) {

        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("You cannot unfollow yourself");
        }

        FollowId followId = new FollowId(followerId, followeeId);

        // Only delete if exists
        if (followRepo.existsById(followId)) {
            followRepo.deleteById(followId);
        }
    }

 // FOLLOWERS (who follows me)
    public List<Long> getFollowers(Long userId) {
        return followRepo.findByIdFolloweeId(userId)
                .stream()
                .map(f -> f.getId().getFollowerId())
                .collect(Collectors.toList());
    }

    // FOLLOWING (who I follow)
    public List<Long> getFollowing(Long userId) {
    	return followRepo.findByIdFollowerId(userId)
    	        .stream()
    	        .map((Follow f) -> f.getId().getFolloweeId())
    	        .collect(Collectors.toList());

    }
    
    public long getFollowerCount(Long userId) {
        return followRepo.countByIdFolloweeId(userId);
    }

    public long getFollowingCount(Long userId) {
        return followRepo.countByIdFollowerId(userId);
    }
}

