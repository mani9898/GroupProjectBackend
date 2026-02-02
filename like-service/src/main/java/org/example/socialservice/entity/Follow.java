package org.example.socialservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class Follow {

    @EmbeddedId
    private FollowId id;

    private LocalDateTime createdAt;

    public Follow(String follower, String followee) {
        this.id = new FollowId();
        this.id.setFollowerUsername(follower);
        this.id.setFolloweeUsername(followee);
        this.createdAt = LocalDateTime.now();
    }

    public Follow() {}
    
    public FollowId getFollowId() {
    	return id;
    }
    
    public void setFollowId(FollowId id) {
    	this.id = id;
    }
    
    public LocalDateTime getCreatedAt() {
    	return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
    	this.createdAt = createdAt;
    }
}



