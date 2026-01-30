package org.example.authzservice.controller;


import org.example.authservice.dtos.ResponseRegisterErrorDto;
import org.example.authservice.exceptions.InvalidUserCred;
import org.example.authservice.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseRegisterErrorDto> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ResponseRegisterErrorDto dto = new ResponseRegisterErrorDto(ex.getMessage(), ex.getSuggestionNames());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @ExceptionHandler(InvalidUserCred.class)
    public ResponseEntity<String> handleInvalidUserCred(InvalidUserCred ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
