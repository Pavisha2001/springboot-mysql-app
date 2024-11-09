package com.iam.authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.iam.authenticator.model.Users;
import com.iam.authenticator.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	JWTService jWTService;
	
	@Autowired
	AuthenticationManager AuthManager;

	public Users saveUser(Users user) {
		return userRepo.save(user);
	}

	public String verify(Users user) {
		// TODO Auto-generated method stub
		Authentication authentication=AuthManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jWTService.generateToken(user);
		}else
		{
			return "Failed to authenticate user";
		}
	}
	
	

}
