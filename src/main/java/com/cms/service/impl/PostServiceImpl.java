package com.cms.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cms.dto.PageResponseDto;
import com.cms.dto.PostCommentRequestDto;
import com.cms.dto.PostDto;
import com.cms.dto.PostReactionRequestDto;
import com.cms.dto.PostRequestDto;
import com.cms.entity.Post;
import com.cms.entity.PostComment;
import com.cms.entity.PostReaction;
import com.cms.repository.PostCommentRepository;
import com.cms.repository.PostReactionRepository;
import com.cms.repository.PostRepository;
import com.cms.service.AuthService;
import com.cms.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostCommentRepository postCommentRepository;
	private final PostReactionRepository postReactionRepository;
	private final AuthService authService;

	@Override
	public void addPost(PostRequestDto postrequestDto) {
		var post = new Post();
		post.setTitle(postrequestDto.getTitle());
		post.setContent(postrequestDto.getContent());
		post.setUser(this.authService.getLoggedInUser());
		this.postRepository.save(post);
	}

	@Override
	public void addCommnet(Long postId, PostCommentRequestDto commentRequestDto) {
		final var optionalPost = this.postRepository.findById(1L);

		if (optionalPost.isPresent()) {
			final var post = optionalPost.get();

			final var comment = new PostComment();
			comment.setComment(commentRequestDto.getComment());
			comment.setPost(post);

			this.postCommentRepository.save(comment);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post Not Found");
		}
	}

	@Override
	public PageResponseDto<PostDto> findAllPost(Integer pageNo, Integer pageSize) {

		final var pageResponse = this.postRepository.findAll(PageRequest.of(pageNo, pageSize));

		final var response = new PageResponseDto<PostDto>();
		response.setData(pageResponse
				.stream()
				// converting Post entity values to Post dto
				.map(postFromDatabase -> {
					var dto = new PostDto();
					dto.setId(postFromDatabase.getPostId());
					dto.setPostContent(postFromDatabase.getContent());
					dto.setPostTitle(postFromDatabase.getTitle());
					return dto;
				}).toList());

		response.setTotalElement(pageResponse.getTotalElements());
		response.setTotalPage(pageResponse.getTotalPages());

		return response;
	}

	@Override
	public void addPostReaction(Long postId, PostReactionRequestDto postReactionDto) {
		// STEP 1: Get post from database via postId.

		final var optionalPost = this.postRepository.findById(postId);

		// STEP 2: If post is found then create a new postReaction Entity object and set
		// the values.
		if (optionalPost.isPresent()) {
			final var post = optionalPost.get();

			final var reaction = new PostReaction();
			reaction.setPostReaction(postReactionDto.getReaction());
			reaction.setPost(post);

			this.postReactionRepository.save(reaction);
		} else {
			// STEP 3: if post is not found then throw an exception.
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "post not found");
		}

	}

}
