package org.example.socialservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
public class Like {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "post_id", unique = true, nullable = false)
	private Long postId;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Like() {}

    public Like(Long postId, String username) {
        this.postId  = postId;
        this.username = username;
        this.createdAt = LocalDateTime.now();
    }

    public Long getPostId() {
    	return postId;
    }
    
    public void setPostId(Long postId) {
    	this.postId = postId;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}



