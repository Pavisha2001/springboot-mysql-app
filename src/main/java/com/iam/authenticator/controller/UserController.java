package com.iam.authenticator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iam.authenticator.model.Users;
import com.iam.authenticator.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	
	@PostMapping("/registerUser")
	public Users registerUser(@RequestBody Users user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		return userService.saveUser(user);
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user)
	{
		
		return userService.verify(user);
	}

}
