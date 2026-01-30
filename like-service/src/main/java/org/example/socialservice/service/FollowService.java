package org.example.socialservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import org.example.socialservice.repository.FollowRepository;
import org.example.socialservice.entity.FollowId;
import org.example.socialservice.entity.Follow;
@Service
@RequiredArgsConstructor
public class FollowService {

    private FollowRepository followRepo;

    // Follow a user
    public void followUser(Long followerId, Long followeeId) {
        if (followerId.equals(followeeId)) return; // prevent self-follow
        FollowId id = new FollowId(followerId, followeeId);
        if (!followRepo.existsById(id)) {
            Follow follow = new Follow(id);
            followRepo.save(follow);
        }
    }

    // Unfollow a user
    public void unfollowUser(Long followerId, Long followeeId) {
        FollowId id = new FollowId(followerId, followeeId);
        followRepo.deleteById(id);
    }

    public List<Long> getFollowers(Long userId) {
        return followRepo.findByIdFolloweeId(userId)
                .stream()
                .map((Follow follow) -> follow.getFollowId().getFollowerId())
                .collect(Collectors.toList());
    }
    // Get all users a user is following
    public List<Long> getFollowing(Long userId) {
        return followRepo.findByIdFollowerId(userId)
                .stream()
                .map((Follow follow) -> follow.getFollowId().getFollowerId())
                .collect(Collectors.toList());
    }

}

