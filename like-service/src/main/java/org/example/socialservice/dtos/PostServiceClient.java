package org.example.socialservice.dtos;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
	    name = "post-service",
	    url = "${services.post.base-url}"
	)
	public interface PostServiceClient {

	    @GetMapping("/posts/{id}")
	    PostDto getPost(@PathVariable Long id);

	    @GetMapping("/posts")
	    List<PostDto> getPostsByAuthor(@RequestParam Long authorId);
	}

