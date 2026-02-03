package org.example.socialservice.repository;

import org.example.socialservice.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUser(String user);        // who follows this user
    List<Follow> findByFollower(String follower); // who this user follows

    boolean existsByFollowerAndUser(String personFollowing, String personBeingFollowed);

}




