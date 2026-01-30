package org.example.socialservice.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowId implements Serializable {

    private Long followerId;
    private Long followeeId;
    
    public FollowId(Long followerId, Long followeeId) {
    	this.followerId = followerId;
    	this.followeeId = followeeId;
    }
    
    public Long getFollowerId() {
    	return followerId;
    }
    
    public void setFollowerId(Long followerId) {
    	this.followerId = followerId;
    }
    
    public Long getFolloweeId() {
    	return followeeId;
    }
    
    public void setFolloweeId(Long followeeId) {
    	this.followeeId = followeeId;
    }
}

