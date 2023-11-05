package com.cms.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cms_post")
public class Post extends AuditEntity {

	private static final long serialVersionUID = 2639008286432011634L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long postId;

	private String title;
	private String content;

	@OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<PostComment> comments = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
	private Set<PostReaction> postReactions = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	private User user;

	
	public void addComment(PostComment comment) {
		this.comments.add(comment);
		comment.setPost(this);
	}

	public void removeComment(PostComment comment) {
		comment.setPost(null);
		this.comments.remove(comment);
	}

	public void addPostReaction(PostReaction postReaction) {
		this.postReactions.add(postReaction);
		postReaction.setPost(this);
	}

	public void removePostReaction(PostReaction postReaction) {
		postReaction.setPost(null);
		this.postReactions.remove(postReaction);
	}
	@Override
	public int hashCode() {
		return 2053;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(postId, other.postId);
	}
	

}
