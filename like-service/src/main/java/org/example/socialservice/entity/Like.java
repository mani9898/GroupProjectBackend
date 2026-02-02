package org.example.socialservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
public class Like {

    @EmbeddedId
    private LikeId id;

    private LocalDateTime createdAt;

    public Like(Long postId, String username) {
        this.id = new LikeId();
        this.id.setPostId(postId);
        this.id.setUsername(username);
        this.createdAt = LocalDateTime.now();
    }

    public Like() {}
    
    public LikeId getLikeId() {
    	return id;
    }
    
    public void setLikeId(LikeId id) {
    	this.id = id;
    }
    
    public LocalDateTime getCreatedAt() {
    	return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
    	this.createdAt = createdAt;
    }
}


