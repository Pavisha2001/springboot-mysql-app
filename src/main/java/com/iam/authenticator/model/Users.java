package com.iam.authenticator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "user")
public class Users {
	@Id
	private int userId;
	private String username;
	private String password;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
