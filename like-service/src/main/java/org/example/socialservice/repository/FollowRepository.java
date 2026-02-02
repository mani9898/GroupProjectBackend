package org.example.socialservice.repository;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

	List<Follow> findByIdFollowerId(Long followerId);

    List<Follow> findByIdFolloweeId(Long followeeId);
    
    long countByIdFollowerId(Long followerId);   // How many users this user is following

    long countByIdFolloweeId(Long followeeId);   // How many followers this user has
}



