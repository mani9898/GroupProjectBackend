package org.example.socialservice.dtos;

import java.time.LocalDateTime;

public record PostDto(
	    Long id,
	    Long authorId,
	    String content,
	    String imageUrl,
	    LocalDateTime createdAt
	) {}

