package org.example.socialservice.repository;

import org.example.socialservice.entity.Like;
import org.example.socialservice.entity.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

    // Get all likes for a specific post
    List<Like> findByIdPostId(Long postId);

    // Check if a specific user already liked a post
    boolean existsById(LikeId id);

    // Delete a like by user/post
    void deleteById(LikeId id);
}

