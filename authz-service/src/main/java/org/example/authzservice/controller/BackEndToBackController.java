package org.example.authzservice.controller;

import org.example.authzservice.dtos.ResponseInternalCheck;
import org.example.authzservice.exceptions.UserAlreadyExistsException;
import org.example.authzservice.service.SocialMediaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/backEndToBack")
public class BackEndToBackController {

    @Autowired
    SocialMediaUserService socialMediaUserService;

    @GetMapping("/follower")
    public ResponseEntity<ResponseInternalCheck> checkUserFollower(@RequestParam("followerUsername") String followerUsername) throws InvalidMediaTypeException {
        ResponseInternalCheck body = socialMediaUserService.getMediaUser(followerUsername);
        return ResponseEntity.ok(body);
    }
}
