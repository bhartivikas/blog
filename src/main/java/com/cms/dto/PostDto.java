package com.cms.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	private Long id;
	private String postTitle;
	private String postContent;

	// Comments
	private Integer totalComments;
	List<PostCommentResponseDto> comments = new ArrayList<>();

	// Reaction
	private Integer totalReactions;
	List<PostReactionResponseDto> reactions = new ArrayList<>();

	public void updateTotalCommentCount() {
		this.totalComments = this.comments.size();

	}

	public void updateTotalReactionCount() {
		this.totalReactions = this.reactions.size();

	}
}
