package com.cms.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
}