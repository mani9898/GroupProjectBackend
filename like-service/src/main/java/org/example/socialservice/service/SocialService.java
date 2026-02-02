package org.example.socialservice.service;

import java.util.List;

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

    public void follow(String me, String target) {
        if (me.equals(target)) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }

        FollowId id = new FollowId();
        id.setFollowerUsername(me);
        id.setFolloweeUsername(target);

        if (!followRepo.existsById(id)) {
            followRepo.save(new Follow(me, target));
        }
    }

    public void unfollow(String me, String target) {
        FollowId id = new FollowId();
        id.setFollowerUsername(me);
        id.setFolloweeUsername(target);
        followRepo.deleteById(id);
    }

    public List<String> followers(String username) {
        return followRepo.findFollowers(username);
    }

    public List<String> following(String username) {
        return followRepo.findFollowing(username);
    }
    
    public long getFollowerCount(Long userId) {
        return followRepo.countByFolloweeId(userId);
    }

    public long getFollowingCount(Long userId) {
        return followRepo.countByFollowerId(userId);
    }
}

