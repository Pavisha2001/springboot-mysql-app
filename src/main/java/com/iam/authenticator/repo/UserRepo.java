package com.iam.authenticator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iam.authenticator.model.Users;

@Repository
public interface UserRepo extends JpaRepository <Users,Integer>{

	Users findUserByusername(String username);
	
	

}
