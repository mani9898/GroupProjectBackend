package org.example.socialservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class Follow {

    @EmbeddedId
    private FollowId id;

    private LocalDateTime createdAt;

    public Follow(Long followerId, Long followeeId) {
        this.id = new FollowId();
        this.id.setFollowerId(followerId);
        this.id.setFolloweeId(followeeId);
        this.createdAt = LocalDateTime.now();
    }

    public Follow() {}
    
    public FollowId getId() {
    	return id;
    }
    
    public void setId(FollowId id) {
    	this.id = id;
    }
    
    public LocalDateTime getCreatedAt() {
    	return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
    	this.createdAt = createdAt;
    }
}



