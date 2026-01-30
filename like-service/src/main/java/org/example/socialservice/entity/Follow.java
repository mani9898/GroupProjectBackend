package org.example.socialservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "follows", uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "followee_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {

    @EmbeddedId
    private FollowId id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    public Follow() {
    }
    
    public Follow(FollowId id) {
    	this.id = id;
    	this.createdAt = LocalDateTime.now();
    }
    
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


