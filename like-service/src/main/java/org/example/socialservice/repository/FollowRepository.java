package org.example.socialservice.repository;

import org.example.socialservice.entity.Follow;
import org.example.socialservice.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    @Query("""
        SELECT f.id.followeeUsername
        FROM Follow f
        WHERE f.id.followerUsername = :username
    """)
    List<String> findFollowing(String username);

    @Query("""
        SELECT f.id.followerUsername
        FROM Follow f
        WHERE f.id.followeeUsername = :username
    """)
    List<String> findFollowers(String username);
    
    long countByFollowerId(Long followerId);   // How many users this user is following

    long countByFolloweeId(Long followeeId);   // How many followers this user has
}


