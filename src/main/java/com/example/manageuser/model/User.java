package com.example.manageuser.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private Long phNo;
	private String date;
	private String gender;
	private String country;
	private String state;
	private String citys;
	private String userPwd;
	private String accountStatus;
	
	
}
