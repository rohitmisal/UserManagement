package com.example.manageuser.service;

import java.util.Map;

import com.example.manageuser.model.LoginRequest;
import com.example.manageuser.model.UnlockAccount;
import com.example.manageuser.model.User;

public interface UserManagementService {

	public boolean isEmailValid(LoginRequest request);

	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStates(Integer countryId);

	public Map<Integer, String> getCities(Integer stateId);

	public String reisterUser(User user);

	public String unlockAccount(UnlockAccount unlockAccount);

	public String isValidLogin(LoginRequest loginRequest);

	public String forgotPwd(String email);

}
