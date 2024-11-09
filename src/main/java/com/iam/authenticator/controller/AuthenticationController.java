package com.iam.authenticator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	
	@GetMapping("/healthCheck")
	public String healthCheck() {
		return "Healthcheckdone";
	}

}
