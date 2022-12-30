package com.example.manageuser.helper;

import java.security.SecureRandom;

import com.example.manageuser.model.User;

public class UserManageServiceHelper {

	public String generatePassword() {

		int len=10;
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        System.out.println(sb);
        return sb.toString();
    
		
	}
	
	public boolean sendEmail(User user) {
		
		return false;
	}

	public void isTempPwdValid(String tempPwd) {
		
		
		
	}
	
}
