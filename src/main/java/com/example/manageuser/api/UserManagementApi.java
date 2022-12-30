package com.example.manageuser.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.manageuser.model.LoginRequest;
import com.example.manageuser.service.UserManagementService;

@RestController
public class UserManagementApi {

	@Autowired
	UserManagementService userService;
		@PostMapping("/login")
		public String login(@RequestBody LoginRequest request) {
			String msg="";
			userService.isValidLogin(request);
			return msg;
		}
		
}
