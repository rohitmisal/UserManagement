package com.example.manageuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.manageuser.model.User;

public interface UserRespository extends JpaRepository<User, Integer>{

	@Query(value="select count(*) from user where email=?",nativeQuery=true)
	int isEmailexists(String email);

	@Query(value="select * from user where email=?",nativeQuery=true)
	User findByEmail(String email);

	
}
