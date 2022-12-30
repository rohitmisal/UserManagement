package com.example.manageuser.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.manageuser.model.LoginRequest;
import com.example.manageuser.model.UnlockAccount;
import com.example.manageuser.model.User;
import com.example.manageuser.service.UserManagementService;

@RestController
public class UserManagementApi {

	@Autowired
	UserManagementService userService;

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {

		return userService.isValidLogin(request);
	}

	@PostMapping("/registeruser")
	public String reisterUser(@RequestBody User user) {

		return userService.reisterUser(user);
	}

	@GetMapping("/countries")
	public Map<Integer, String> getAllCountries() {

		return userService.getCountries();
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getAllState(@PathVariable Integer countryId) {

		return userService.getStates(countryId);
	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getAllCitities(@PathVariable Integer stateId) {

		return userService.getCities(stateId);
	}

	@PostMapping("/unlockaccount")
	public String unlockAccount(@RequestBody UnlockAccount unlockAccount) {

		return userService.unlockAccount(unlockAccount);
	}

	@PostMapping("/forgotpwd/{email}")
	public String unlockAccount(@PathVariable String email) {

		return userService.forgotPwd(email);
	}
}
