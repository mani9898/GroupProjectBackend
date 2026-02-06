// java
package org.example.socialservice.service;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.Like;
import org.example.socialservice.exceptions.DuplicateFollowException;
import org.example.socialservice.repository.FollowRepository;
import org.example.socialservice.repository.LikeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocialService {

    private final FollowRepository followRepository;
    private final LikeRepository likeRepository;

    public SocialService(FollowRepository followRepository, LikeRepository likeRepository) {
        this.followRepository = followRepository;
        this.likeRepository = likeRepository;
    }

    public Follow followUser(String followerUsername, String followeeUsername) throws Exception {
        // check explicitly with correct parameter order: follower, user
        if (followRepository.existsByFollowerUsernameAndFolloweeUsername(followerUsername, followeeUsername)) {
            throw new DuplicateFollowException("User " + followerUsername + " already follows " + followeeUsername);
        }

        // construct Follow(user, follower) -> (personBeingFollowed, personFollowing)
        Follow follow = new Follow(followerUsername, followeeUsername);

        try {
            return followRepository.save(follow);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateFollowException("Duplicate follow attempt for follower: " + followerUsername, ex);
        } catch (Exception e) {
            throw new Exception("Error while following user: " + e.getMessage(), e);
        }
    }


    public List<String> getFollowers(String followeeUsername) throws Exception {
    	return followRepository.findByFolloweeUsername(followeeUsername)
                .stream()
                .map(Follow::getFollowerUsername)
                .toList();
    }

    public List<String> getFollowing(String followerUsername) {
    	return followRepository.findByFollowerUsername(followerUsername)
                .stream()
                .map(Follow::getFolloweeUsername)
                .toList();
    }

    public Like likePost(Long postId, String username) {
        Like like = new Like(postId, username);
        return likeRepository.save(like);
    }

    @Transactional
    public void unlikePost(Long postId, String username) {
        likeRepository.deleteByPostIdAndUsername(postId, username);
    }

    public List<Like> getLikesForPost(Long postId) {
        return likeRepository.findByPostId(postId);
    }

    public List<Like> getLikesByUser(String username) {
        return likeRepository.findByUsername(username);
    }

    @Transactional
	public void unfollowUser(String followerUsername, String followeeUsername) throws Exception {
		// check explicitly with correct parameter order: follower, user
        if (!followRepository.existsByFollowerUsernameAndFolloweeUsername(followerUsername, followeeUsername)) {
            throw new DuplicateFollowException("User " + followerUsername + " does not follow " + followeeUsername);
        }
        
        try {
        	followRepository.deleteByFollowerUsernameAndFolloweeUsername(followerUsername, followeeUsername);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateFollowException("Duplicate unfollow attempt for follower: " + followerUsername, ex);
        } catch (Exception e) {
            throw new Exception("Error while unfollowing user: " + e.getMessage(), e);
        }
	}
}
