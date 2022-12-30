package com.example.manageuser.model;

import lombok.Data;

@Data
public class UnlockAccount {

	private Integer id;
	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;
	
}
