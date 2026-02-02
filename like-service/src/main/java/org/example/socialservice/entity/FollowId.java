package org.example.socialservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FollowId implements Serializable {

	@Column(name = "follower_id")
    private Long followerId;

    @Column(name = "followee_id")
    private Long followeeId;
    
    public FollowId() {
    }
    
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


