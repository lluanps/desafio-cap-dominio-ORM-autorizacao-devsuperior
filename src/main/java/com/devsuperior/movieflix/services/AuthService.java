package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entitites.User;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public User authentificated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return repository.findByEmail(username);
		} catch (Exception e) {
			throw new UnauthorizedUserException("Usuário não encotrado");
		}
	}
	
}
