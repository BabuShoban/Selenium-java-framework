package com.automation.utilities;

public class AuthenticationToken {

	public String username;
	private String password;
	private String SecurityQuestion;

	public AuthenticationToken(String username,String password,String SecurityQuestion) {
		this.username = username;
		this.password = password;
		this.SecurityQuestion = SecurityQuestion;
	}


	public String getusername() {
		return this.username;
	}

	public String getpassword() {
		return password;
	}

	public String getSecurityQuestion() {
		return SecurityQuestion;
	}

	public String setusername(String username) {
		return this.username = username;
	}

	public String setpassword(String password) {
		return this.password = password;
	}

	public String setSecurityQuestion(String SecurityQuestion) {
		return this.SecurityQuestion = SecurityQuestion;
	}


}
