package com.cms.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cms_post_comment")
public class PostComment extends AuditEntity {

	private static final long serialVersionUID = -1288925776728871369L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long commentId;

	private String comment;

	@ManyToOne
	private Post post;

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
		PostComment other = (PostComment) obj;
		return Objects.equals(commentId, other.commentId);
	}

}
