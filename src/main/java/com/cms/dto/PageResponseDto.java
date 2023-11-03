package com.cms.dto;

import lombok.Data;

@Data
public class PageResponseDto<T> {

	private T data;

	// PAGE INFORMATION
	private Long totalElement;
	private Integer totalPage;
}
