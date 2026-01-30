package org.example.socialservice.service;

import org.example.socialservice.entity.Like;
import org.example.socialservice.entity.LikeId;
import org.example.socialservice.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private LikeRepository likeRepo;

    // Like a post
    public void likePost(Long userId, Long postId) {
        LikeId id = new LikeId(postId, userId);
        if (!likeRepo.existsById(id)) {
            Like like = new Like(id);
            likeRepo.save(like);
        }
    }

    // Unlike a post
    public void unlikePost(Long userId, Long postId) {
        LikeId id = new LikeId(postId, userId);
        likeRepo.deleteById(id);
    }

    // Get all users who liked a post
    public List<Long> getUsersWhoLikedPost(Long postId) {
        return likeRepo.findByIdPostId(postId)
                .stream()
                .map((Like like) -> like.getLikeId().getUserId())
                .collect(Collectors.toList());
    }

}

