package org.example.socialservice.repository;

import org.example.socialservice.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPostId(Long postId);
    List<Like> findByUsername(String username);
    void deleteByPostIdAndUsername(Long postId, String username);
    boolean existsByPostIdAndUsername(Long postId, String username);
}


