package com.softserve.form;

import javax.validation.constraints.Size;

public class ResetPassword {
	
	private String key;
	@Size(min = 4, max = 20, message = "Password size from 4 to 20 chars")
	private String password;
	private String confirmPassword;
	
	public ResetPassword() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
