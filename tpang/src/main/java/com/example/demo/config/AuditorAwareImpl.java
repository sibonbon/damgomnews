package com.example.demo.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || !auth.isAuthenticated() 
						||auth.getPrincipal().toString().compareTo("anonymousUser")==0) {
			return Optional.of("anonymousUser"); }
		return Optional.of(
				((User) auth.getPrincipal()).getUsername());
	}

}
