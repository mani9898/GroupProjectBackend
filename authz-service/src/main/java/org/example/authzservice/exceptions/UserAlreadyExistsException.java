package org.example.authzservice.exceptions;

import java.util.List;

public class UserAlreadyExistsException extends RuntimeException {
    private final List<String> suggestionNames;

    public UserAlreadyExistsException(String message, List<String> suggestionNames) {
        super(message);
        this.suggestionNames = suggestionNames;
    }

    public List<String> getSuggestionNames() {
        return suggestionNames;
    }
}
