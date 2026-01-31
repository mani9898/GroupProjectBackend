package org.example.authzservice.dtos;

import org.example.authzservice.entity.UserStatus;

import java.time.Instant;

public class ResponseInternalCheck {

    private String followerUsername;
    private UserStatus status;
    private Instant loggedIn;

    public ResponseInternalCheck(String followerUsername, UserStatus status, Instant loggedIn) {
        this.followerUsername = followerUsername;
        this.status = status;
        this.loggedIn = loggedIn;
    }

    public String getFollowerUsername() {
        return followerUsername;
    }
    public void setFollowerUsername(String followerUsername) {
        this.followerUsername = followerUsername;
    }
    public UserStatus getStatus() {
        return status;
    }
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    public Instant getLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(Instant loggedIn) {
        this.loggedIn = loggedIn;
    }

}
