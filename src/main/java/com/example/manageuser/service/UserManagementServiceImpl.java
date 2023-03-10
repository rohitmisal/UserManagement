package com.example.manageuser.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.manageuser.model.City;
import com.example.manageuser.model.Country;
import com.example.manageuser.model.LoginRequest;
import com.example.manageuser.model.State;
import com.example.manageuser.model.UnlockAccount;
import com.example.manageuser.model.User;
import com.example.manageuser.repository.CityRepository;
import com.example.manageuser.repository.CountryRepository;
import com.example.manageuser.repository.StateRepository;
import com.example.manageuser.repository.UserRespository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	UserRespository userRepo;

	@Autowired
	CountryRepository countryRepo;

	@Autowired
	StateRepository stateRepo;

	@Autowired
	CityRepository cityRepo;

	@Autowired
	JavaMailSender mailSender;

	@Override
	public boolean isEmailValid(LoginRequest request) {

		String email = request.getEmail();
		System.out.println(email);
		int i = userRepo.isEmailexists(email);
		System.out.println(i);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<Integer, String> getCountries() {
		Map<Integer, String> mapCountry = new HashMap<>();
		List<Country> listCountry = countryRepo.findAll();
		for (Country country : listCountry) {
			mapCountry.put(country.getId(), country.getCountryName());
		}
		return mapCountry;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer, String> mapStates = new HashMap<>();
		List<State> listStates = stateRepo.findStatesByCountryId(countryId);
		for (State state : listStates) {
			mapStates.put(state.getId(), state.getStateName());
		}

		return mapStates;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer, String> mapCities = new HashMap<>();
		List<City> listCities = cityRepo.findCitiesByStateId(stateId);
		for (City city : listCities) {
			mapCities.put(city.getId(), city.getCityName());
		}

		return mapCities;
	}

	@Override
	public String reisterUser(User user) {
		String msg = "";
		String generatedPwd = generatePassword();
		user.setUserPwd(generatedPwd);
		user.setAccountStatus("locked");

		try {
			int i = userRepo.isEmailexists(user.getEmail());
			if (i > 0) {
				msg = "User alredy exist with given email";
			} else {
				if (userRepo.save(user) != null) {
					sendEmailOnRegistration(user);
					msg = "User saved Successfully";
				} else {
					msg = "Unable to save user";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return msg;
	}

	@Override
	public String unlockAccount(UnlockAccount unlockAccount) {

		String msg = "";
		String tempPwd = unlockAccount.getTempPwd();

		User user = userRepo.findByEmail(unlockAccount.getEmail());
		if (user.getUserPwd().equals(tempPwd)) {

			if (unlockAccount.getNewPwd().equals(unlockAccount.getConfirmPwd())) {
				user.setUserPwd(unlockAccount.getNewPwd());
				user.setAccountStatus("unlocked");
				userRepo.save(user);
				msg = "Account unlocked, please proceed with login";
			} else {
				msg = "New password and Confirmed  password are not same please enter correctly";
			}
		} else {
			msg = "Please Eneter Temprary Password Correctly";

		}
		return msg;
	}

	@Override
	public String isValidLogin(LoginRequest loginRequest) {

		String msg = "";
		if (isEmailValid(loginRequest)) {
			System.out.println("Inside  is valid Email");
			User user = userRepo.findByEmail(loginRequest.getEmail());
			System.out.println("Inside  is valid user is :" + user.toString());

			if (user.getUserPwd().equals(loginRequest.getPassword())) {
				if (user.getAccountStatus().equalsIgnoreCase("locked")) {
					msg = "Your Account Is Locked";
				} else {
					msg = "Welcome to portal";
				}
			} else {
				msg = "Please provide valid password";
			}

		} else {
			msg = "Please provide valid Email";
		}

		return msg;
	}

	@Override
	public String forgotPwd(String email) {
		String msg = "";
		int i = userRepo.isEmailexists(email);
		if (i > 0) {
			User user = userRepo.findByEmail(email);
			sendEmailPassword(user);
			msg = "your password has been send to your registered email";
		} else {
			msg = "Please provide Valid Email";
		}
		return msg;
	}

	public String generatePassword() {

		int len = 10;
		// ASCII range ??? alphanumeric (0-9, a-z, A-Z)
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		// each iteration of the loop randomly chooses a character from the given
		// ASCII range and appends it to the `StringBuilder` instance

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}
		System.out.println(sb);
		return sb.toString();

	}

	public boolean sendEmailOnRegistration(User user) {
		String email = user.getEmail();
		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setSubject("Unlock IES Account");
			String textMsg = "Hi " + user.getFirstName() + " " + user.getLastName() + ":"
					+ ", \n Welcome to IES family , your registration is almost complete .\n Please Unlock your account using below details \n Tempopary password : "
					+ user.getUserPwd() + " \n Link to unlock accont " + " http://localhost:8080/unlockaccount "
					+ " \n Thanks, \n IES Team";

			mailMessage.setText(textMsg);
			mailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}

	public boolean sendEmailPassword(User user) {
		String email = user.getEmail();
		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setSubject("Unlock IES Account");
			String textMsg = "Hi " + user.getFirstName() + " " + user.getLastName() + ":"
					+ ", \nPlease Unlock your account using below details \n your password : " + user.getUserPwd()
					+ " \n Thanks, \n IES Team";

			mailMessage.setText(textMsg);
			mailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}

}
