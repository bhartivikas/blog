package com.cms.service;

import com.cms.dto.PageResponseDto;
import com.cms.dto.PostCommentRequestDto;
import com.cms.dto.PostDto;
import com.cms.dto.PostReactionRequestDto;
import com.cms.dto.PostRequestDto;

public interface PostService {

	void addPost(PostRequestDto postRequestDto);

	PageResponseDto<PostDto> findAllPost(Integer pageNo, Integer pageSize);

	void addCommnet(Long postId, PostCommentRequestDto commentRequestDto);

	void addPostReaction(Long postId, PostReactionRequestDto postReactionDto);

}
