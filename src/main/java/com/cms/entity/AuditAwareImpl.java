package com.cms.entity;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component(value = "auditorAware") // Creating a Bean of AuditorAware name ="auditorAware"
public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("ADMIN");// Now Spring Security is not present
		// So, returning a hard coded name
	}

}
