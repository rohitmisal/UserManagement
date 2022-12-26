package com.example.manageuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.manageuser.model.User;

public interface UserRespository extends JpaRepository<User, Integer>{

}
