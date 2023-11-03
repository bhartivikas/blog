package com.cms.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.PostCommentRequestDto;
import com.cms.dto.PostDto;
import com.cms.dto.PostReactionRequestDto;
import com.cms.dto.PostRequestDto;
import com.cms.dto.PageResponseDto;
import com.cms.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping
	public PageResponseDto<List<PostDto>> getAllPost(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "50") Integer pageSize) {
		return this.postService.findAllPost(pageNo, pageSize);
	}

	@PostMapping
	public void savePost(@RequestBody PostRequestDto postRequestDto) {
		this.savePost(postRequestDto);
	}

	@PostMapping("/comments/{postId}")
	public void addComment(@PathVariable Long postId,
			@RequestBody PostCommentRequestDto commentRequestDto) {
		this.postService.addCommnet(postId, commentRequestDto);
	}

	@PostMapping("/reaction/{postId}")
	public void addComment(@PathVariable Long postId,
			@RequestBody PostReactionRequestDto postReactionDto) {
		this.postService.addPostReaction(postId, postReactionDto);
	}

}
