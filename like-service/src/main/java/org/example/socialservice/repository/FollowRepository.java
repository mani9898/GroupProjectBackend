package org.example.socialservice.repository;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    // Get all followers of a user
    List<Follow> findByIdFolloweeId(Long followeeId);

    // Get all users a user is following
    List<Follow> findByIdFollowerId(Long followerId);

    // Check if a follow relationship exists
    boolean existsById(FollowId id);

    // Delete a follow relationship
    void deleteById(FollowId id);
}

