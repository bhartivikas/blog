package com.cms.service;

import java.util.List;

import com.cms.dto.PostCommentRequestDto;
import com.cms.dto.PostDto;
import com.cms.dto.PostReactionRequestDto;
import com.cms.dto.PostRequestDto;
import com.cms.dto.PageResponseDto;

public interface PostService {

	void addPost(PostRequestDto postRequestDto);

	PageResponseDto<List<PostDto>> findAllPost(Integer pageNo, Integer pageSize);

	void addCommnet(Long postId, PostCommentRequestDto commentRequestDto);

	void addPostReaction(Long postId, PostReactionRequestDto postReactionDto);

}
