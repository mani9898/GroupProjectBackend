package org.example.socialservice.dtos;

import org.example.socialservice.responseinternalcheck.ResponseInternalCheck;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service", url = "http://localhost:9000")
public interface AuthServiceClient {

    // Example: get user info by username
    @GetMapping("/api/internal/backEndToBack/follower")
    ResponseInternalCheck getMediaUser(@RequestParam("followerUsername") String username);

    // Optional: get user ID directly if you add an endpoint in Auth Service
    @GetMapping("/api/internal/backEndToBack/userId")
    Long getUserId(@RequestParam("username") String username);
}

