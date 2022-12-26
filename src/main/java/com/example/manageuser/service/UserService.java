package com.example.manageuser.service;

import java.util.List;

import com.example.manageuser.model.City;
import com.example.manageuser.model.Country;
import com.example.manageuser.model.LoginRequest;
import com.example.manageuser.model.State;
import com.example.manageuser.model.UnlockAccount;
import com.example.manageuser.model.User;

public interface UserService {

	String isValidLogin(LoginRequest loginRequest);

	boolean isEmailValid(LoginRequest request);

	List<Country> getListCountries();

	List<State> getListStates(Country country);

	List<City> getListCities(City city);

	String generatePassword();

	String sendEmail(User user);

	String unlockAccount(UnlockAccount unlockAccount);
}
