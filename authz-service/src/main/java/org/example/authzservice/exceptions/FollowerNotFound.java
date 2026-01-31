package org.example.authzservice.exceptions;

public class FollowerNotFound extends RuntimeException {
    public FollowerNotFound(String message) {
        super(message);
    }
}
