// java
package org.example.socialservice.service;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.Like;
import org.example.socialservice.exceptions.DuplicateFollowException;
import org.example.socialservice.repository.FollowRepository;
import org.example.socialservice.repository.LikeRepository;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Follow followUser(String personFollowing, String personBeingFollowed) throws Exception {
        // check explicitly with correct parameter order: follower, user
        if (followRepository.existsByFollowerAndUser(personFollowing, personBeingFollowed)) {
            throw new DuplicateFollowException("User " + personFollowing + " already follows " + personBeingFollowed);
        }

        // construct Follow(user, follower) -> (personBeingFollowed, personFollowing)
        Follow follow = new Follow(personBeingFollowed, personFollowing);

        try {
            return followRepository.save(follow);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateFollowException("Duplicate follow attempt for follower: " + personFollowing, ex);
        } catch (Exception e) {
            throw new Exception("Error while following user: " + e.getMessage(), e);
        }
    }


    public List<Follow> getFollowers(String user) throws Exception {
        List<Follow> followers = followRepository.findByUser(user);
        return followers;
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
