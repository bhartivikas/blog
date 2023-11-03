package com.cms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity implements Serializable {

	private static final long serialVersionUID = 4185638649625222996L;

	@CreatedBy
	private String createdBy;
	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedBy
	private String updatedBy;
	@LastModifiedDate
	private LocalDateTime updatedAt;

}
