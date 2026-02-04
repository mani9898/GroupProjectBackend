package org.example.socialservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "security-authorization-server",
        url = "http://localhost:9000"
)
public interface AuthzClient {

    @PostMapping("/api/internal/backend/follower")
    ResponseEntity<Void> checkUserFollower(
            @RequestParam("followerUsername") String followerUsername
    );
}
