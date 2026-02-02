package org.example.socialservice.repository;

import org.example.socialservice.entity.Like;
import org.example.socialservice.entity.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {
	
    long countByIdPostId(Long postId);
}


