package com.cms.entity;

import java.util.Objects;

import com.cms.constants.Reaction;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cms_post_reaction")
public class PostReaction extends AuditEntity {

	private static final long serialVersionUID = -1288925776728871369L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long postReactionId;

	@Enumerated(EnumType.STRING)
	private Reaction postReaction;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
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
		PostReaction other = (PostReaction) obj;
		return Objects.equals(postReactionId, other.postReactionId);
	}
	
	

}
