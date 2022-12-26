package com.example.manageuser.model;

import java.util.List;

import lombok.Data;

@Data
public class User {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private Long phNo;
	private String date;
	private String gender;
	List<Country> countries;
	List<State> states;
	List<City> cities;

}
