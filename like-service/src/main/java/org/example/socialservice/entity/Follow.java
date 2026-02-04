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
    @Column(name = "followee_username", nullable = false)
    private String user;
    @Column(name = "follower_username", nullable = false)
    private String follower;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public Follow() {
    }
    
    public Follow(String user, String follower) {
    	this.user = user;
    	this.follower = follower;
    	this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
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
                ", user='" + user + '\'' +
                ", follower='" + follower + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}



