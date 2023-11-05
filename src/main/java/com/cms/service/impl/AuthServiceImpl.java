package com.cms.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cms.dto.AuthResponse;
import com.cms.dto.LoginRequestDto;
import com.cms.dto.SignupRequestDto;
import com.cms.entity.User;
import com.cms.repository.UserRepository;
import com.cms.service.AuthService;
import com.cms.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final AuthenticationManager authManager;
	private final PasswordEncoder passwordEncoder;

	@Override
	public AuthResponse login(LoginRequestDto loginRequestDto) {

		// Generate UsenamePasswordAuthentication token
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				loginRequestDto.getUsername(),
				loginRequestDto.getPassword());

		// Use the above created token and pass it to the AuthenticationManager
		this.authManager.authenticate(token);

		// If below lines gets executed it means Request is Successfully authenticated
		final var user = this.userRepository.findByUsername(loginRequestDto.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		final var jwt = this.jwtService.generateToken(user);

		final var response = new AuthResponse();
		response.setToken(jwt);
		return response;
	}

	@Override
	public AuthResponse signup(SignupRequestDto signupRequestDto) {

		// We are checking here whether the provided username already exist in database
		// or not. If found then throw exception and let user submit the request with
		// some other username.
		this.userRepository
				.findByUsername(signupRequestDto.getUsername())
				.ifPresent(user -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is taken");
				});

		final var user = new User();
		user.setUsername(signupRequestDto.getUsername());
		user.setFirstName(signupRequestDto.getFirstName());
		user.setLastName(signupRequestDto.getLastName());
		user.setMobile(signupRequestDto.getMobile());
		user.setEmail(signupRequestDto.getEmail());

		// Do not store SIMPLE password
		// Always store encoded password
		user.setPassword(this.passwordEncoder.encode(signupRequestDto.getPassword()));

		// Save signup request data
		this.userRepository.save(user);

		final var jwt = this.jwtService.generateToken(user);

		AuthResponse response = new AuthResponse();
		response.setToken(jwt);
		return response;
	}

	@Override
	public User getLoggedInUser() {
		final var loggedInUsername = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal()
				.toString();

		return this.userRepository.findByUsername(loggedInUsername)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized"));
	}

}