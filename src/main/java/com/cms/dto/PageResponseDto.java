package com.cms.dto;

import java.util.Collection;

import lombok.Data;

@Data
public class PageResponseDto<T> {

	private Collection<T> data;

	// PAGE INFORMATION
	private Long totalElement;
	private Integer totalPage;
}
