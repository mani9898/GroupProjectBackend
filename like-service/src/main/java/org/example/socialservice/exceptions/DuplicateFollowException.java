package org.example.socialservice.exceptions;

// java

public class DuplicateFollowException extends RuntimeException {
    public DuplicateFollowException(String message) {
        super(message);
    }
    public DuplicateFollowException(String message, Throwable cause) {
        super(message, cause);
    }
}
