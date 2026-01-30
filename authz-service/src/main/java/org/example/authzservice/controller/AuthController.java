package org.example.authzservice.controller;


import jakarta.validation.Valid;
import org.example.authzservice.dtos.LoginDto;
import org.example.authzservice.dtos.RegisterRequest;
import org.example.authzservice.entity.MediaUser;
import org.example.authzservice.exceptions.InvalidUserCred;
import org.example.authzservice.exceptions.UserAlreadyExistsException;
import org.example.authzservice.service.SocialMediaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class AuthController {


    @Autowired
    SocialMediaUserService socialMediaUserService;

    @PostMapping("/register")
    public ResponseEntity<MediaUser> registerUser(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        MediaUser mediaUser = new MediaUser();
        mediaUser.setUsername(registerRequest.getUsername());
        mediaUser.setEmail(registerRequest.getEmail());
        mediaUser.setPassword(registerRequest.getPassword());
        mediaUser = socialMediaUserService.register(mediaUser);
        mediaUser.setPassword(null);

        return new ResponseEntity<>(mediaUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Map<String, String> loginUser(@Valid @RequestBody LoginDto loginDto) throws InvalidUserCred {
        return socialMediaUserService.login(loginDto.getUsername(), loginDto.getPassword());
    }




}

