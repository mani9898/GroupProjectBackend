package org.example.socialservice.responseinternalcheck;

import java.time.Instant;

public class ResponseInternalCheck {
    private String followerUsername;
    private String status;
    private Instant loggedIn;

    // constructor
    public ResponseInternalCheck(String followerUsername, String status, Instant loggedIn) {
        this.followerUsername = followerUsername;
        this.status = status;
        this.loggedIn = loggedIn;
    }

    // getters & setters
    public String getFollowerUsername() { return followerUsername; }
    public void setFollowerUsername(String followerUsername) { this.followerUsername = followerUsername; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getLoggedIn() { return loggedIn; }
    public void setLoggedIn(Instant loggedIn) { this.loggedIn = loggedIn; }
}

