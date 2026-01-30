package org.example.socialservice.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeId implements Serializable {

	private Long postId;
    private Long userId;
    
    public LikeId(Long postId, Long userId) {
		this.postId = postId;
		this.userId = userId;
	}
    
    public Long getPostId() {
    	return postId;
    }
    
    public void setPostId(Long postId) {
    	this.postId = postId;
    }
    
    public Long getUserId() {
    	return userId;
    }
    
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
}

