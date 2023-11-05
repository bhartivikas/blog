package com.cms.entity;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component(value = "auditorAware") // Creating a Bean of AuditorAware name ="auditorAware"
public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		final var loggedInUsername = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal()
				.toString();
		return Optional.of(loggedInUsername);
	}

}
