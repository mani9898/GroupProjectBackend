package org.example.socialservice.dtos;

public record ProfileDto(
	    Long userId,
	    String displayName,
	    String avatarUrl
	) {}

