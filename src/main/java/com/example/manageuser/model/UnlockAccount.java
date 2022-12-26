package com.example.manageuser.model;

import lombok.Data;

@Data
public class UnlockAccount {

	private Integer id;
	private String eamail;
	private String temPwd;
	private String newPwd;
	private String confirmPwd;
	
}
