package org.example.authzservice.dtos;

import java.util.List;

public class ResponseRegisterErrorDto {

    private String errorMessage;
    private List<String> suggestionNames;
    public ResponseRegisterErrorDto(String errorMessage, List<String> suggestionNames) {
        this.errorMessage = errorMessage;
        this.suggestionNames = suggestionNames;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getSuggestionNames() {
        return suggestionNames;
    }

    public void setSuggestionNames(List<String> suggestionNames) {
        this.suggestionNames = suggestionNames;
    }
}
