package org.example.socialservice.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LikeId implements Serializable {

    private Long postId;
    private String username;

    public LikeId() {
    }
    
    public LikeId(Long postId, String username) {
    	this.postId = postId;
    	this.username = username;
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
}


