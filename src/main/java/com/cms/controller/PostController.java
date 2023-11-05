package com.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.CommentDto;
import com.cms.dto.PageResponseDto;
import com.cms.dto.PostCommentRequestDto;
import com.cms.dto.PostDto;
import com.cms.dto.PostReactionRequestDto;
import com.cms.dto.PostRequestDto;
import com.cms.dto.ReactionDto;
import com.cms.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping
	public PageResponseDto<PostDto> getAllPost(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "50") Integer pageSize) {
		// Post should be related to the specific user or author
		return this.postService.findAllPost(pageNo, pageSize);
	}

	@GetMapping("/comments/{postId}")
	public PageResponseDto<CommentDto> getAllComments(
			@PathVariable Long postId,
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "50") Integer pageSize) {
		// Post comment should be related to the specific post id
		return null;
	}

	@GetMapping("/reaction/{postId}")
	public PageResponseDto<ReactionDto> getAllReactions(
			@PathVariable Long postId,
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "50") Integer pageSize) {
		// Post reaction should be related to the specific post id
		return null;
	}

	@PostMapping
	public void savePost(@RequestBody PostRequestDto postRequestDto) {
		// only logged in user can add a post into the database
		this.postService.addPost(postRequestDto);
	}

	@PostMapping("/comments/{postId}")
	public void addComment(@PathVariable Long postId,
			@RequestBody PostCommentRequestDto commentRequestDto) {
		// only logged in user can comment a post in database
		this.postService.addCommnet(postId, commentRequestDto);
	}

	@PostMapping("/reaction/{postId}")
	public void addReaction(@PathVariable Long postId,
			@RequestBody PostReactionRequestDto postReactionDto) {
		// There should be one and only one reaction for a post by a given user
		this.postService.addPostReaction(postId, postReactionDto);
	}

}
