package org.example.authzservice.exceptions;

public class InvalidUserCred extends RuntimeException {
    private String error;
    private String message;

    public InvalidUserCred(String error, String message) {
        super(error);
        this.message = message;
    }
    public String getError() {
        return error;
    }
}
