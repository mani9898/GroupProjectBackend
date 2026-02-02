package org.example.socialservice.dtos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
	    name = "profile-service",
	    url = "${services.profile.base-url}"
	)
	public interface ProfileServiceClient {

	    @GetMapping("/profiles/{userId}")
	    ProfileDto getProfile(@PathVariable Long userId);
	}

