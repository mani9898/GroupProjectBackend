package org.example.socialservice.service;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.Like;
import org.example.socialservice.repository.FollowRepository;
import org.example.socialservice.repository.LikeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SocialService {

    private final FollowRepository followRepository;
    private final LikeRepository likeRepository;

    public SocialService(FollowRepository followRepository, LikeRepository likeRepository) {
        this.followRepository = followRepository;
        this.likeRepository = likeRepository;
    }

    public Follow followUser(String personFollowing, String personBeingFollowed) {
        Follow follow = new Follow(personFollowing, personBeingFollowed);
        return followRepository.save(follow);
    }

    public List<Follow> getFollowers(String user) {
        return followRepository.findByUser(user);
    }

    public List<Follow> getFollowing(String follower) {
        return followRepository.findByFollower(follower);
    }

    public Like likePost(Long postId, String username) {
        Like like = new Like(postId, username);
        return likeRepository.save(like);
    }

    public List<Like> getLikesForPost(Long postId) {
        return likeRepository.findByPostId(postId);
    }

    public List<Like> getLikesByUser(String username) {
        return likeRepository.findByUsername(username);
    }
}






