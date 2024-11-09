package com.iam.authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iam.authenticator.model.UserAuthDetails;
import com.iam.authenticator.model.Users;
import com.iam.authenticator.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService  {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user= userRepo.findUserByusername(username);
		
		if(user==null)
			throw new UsernameNotFoundException("user not found");
		
		return new UserAuthDetails(user);
		
		
	}

}
