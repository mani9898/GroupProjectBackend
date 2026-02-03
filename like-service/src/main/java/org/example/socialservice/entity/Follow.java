package org.example.socialservice.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "follows")
public class Follow {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String user;
    @Column(name = "follower_name", unique = true, nullable = false)
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
    
    public void setLocalDateTime(LocalDateTime createdAt) {
    	this.createdAt = createdAt;
    }
}





