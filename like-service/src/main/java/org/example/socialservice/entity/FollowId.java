package org.example.socialservice.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FollowId implements Serializable {

    private String followerUsername;
    private String followeeUsername;

    public FollowId() {
    }
    
    public FollowId(String followerUsername, String followeeUsername) {
    	this.followerUsername = followerUsername;
    	this.followeeUsername = followeeUsername;
    }
    
    public String getFollowerUsername() {
    	return followerUsername;
    }
    
    public void setFollowerUsername(String followerUsername) {
    	this.followerUsername = followerUsername;
    }
    
    public String getFolloweeUsername() {
    	return followeeUsername;
    }
    
    public void setFolloweeUsername(String followeeUsername) {
    	this.followeeUsername = followeeUsername;
    }
}


