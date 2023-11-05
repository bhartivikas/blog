package com.cms.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cms.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsService userService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {

		// Get Authorization header from the Http Request
		final String authHeader = request.getHeader("Authorization");

		// check if authHeadr is empty or it does not starts with "Bearer "
		if (StringUtils.isBlank(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {

			// If Authorization Header is not present, pass the request to next filter
			// In next filter It will search for the SecurityContext object
			filterChain.doFilter(request, response);
			return;// code will return from here and below code is not going to get executed
		}

		// extract JWT and username
		final var jwt = authHeader.substring(7);
		final var username = this.jwtService.extractUsername(jwt);

		if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

			// load the UserDetails from the userEmail
			final var userDetails = this.userService.loadUserByUsername(username);

			// Please make sure that TOKEN is VALID then and only then We are creating
			// SecurityContext Object and saving it in the SecurityContextHolder
			// If TOKEN is not valid then SecurityContext Object is not created.
			if (this.jwtService.isTokenValid(jwt, userDetails)) {

				// creating a WebAuthenticationDetailsSource object
				WebAuthenticationDetailsSource details = new WebAuthenticationDetailsSource();
				details.buildDetails(request);

				// creating a UsernamePasswordAuthenticationToken object
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				token.setDetails(details);

				// creating an empty SecurityContext object
				SecurityContext context = SecurityContextHolder.createEmptyContext();
				context.setAuthentication(token);

				// set the security context
				SecurityContextHolder.setContext(context);
			}

		}

		// VVI : we need to pass the request to other filter
		filterChain.doFilter(request, response);
	}

}