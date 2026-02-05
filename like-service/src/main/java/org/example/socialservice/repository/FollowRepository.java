package org.example.socialservice.repository;

import org.example.socialservice.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFolloweeUsername(String followeeUsername);        // who follows this user
    List<Follow> findByFollowerUsername(String followerUsername); // who this user follows

	void deleteByFollowerUsernameAndFolloweeUsername(String followerUsername, String followeeUsername);
	boolean existsByFollowerUsernameAndFolloweeUsername(String followerUsername, String followeeUsername);

}



