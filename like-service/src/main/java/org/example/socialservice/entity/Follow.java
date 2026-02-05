package org.example.socialservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(
        name = "follows",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"follower_username", "followee_username"})
        })
public class Follow {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "follower_username", nullable = false)
    private String followerUsername;
    @Column(name = "followee_username", nullable = false)
    private String followeeUsername;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public Follow() {
    }
    
    public Follow(String followerUsername, String followeeUsername) {
    	this.followerUsername = followerUsername;
    	this.followeeUsername = followeeUsername;
    	this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFollowerUsername() {
        return followerUsername;
    }

    public void setFollowerUsername(String followerUsername) {
        this.followerUsername = followerUsername;
    }

    public String getFolloweeUsername() {
        return followeeUsername;
    }

    public void setFolloweeUsername(String followeeUsername) {
        this.followeeUsername= followeeUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", followerUsername='" + followerUsername + '\'' +
                ", followeeUsername='" + followeeUsername + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}



