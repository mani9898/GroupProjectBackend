package org.example.socialservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "likes", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @EmbeddedId
    private LikeId id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    public Like() {
    }
    
    public Like(LikeId id) {
    	this.id = id;
    	this.createdAt = LocalDateTime.now();
    }
    
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

