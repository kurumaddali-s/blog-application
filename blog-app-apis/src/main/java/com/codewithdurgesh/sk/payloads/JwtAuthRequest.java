package com.codewithdurgesh.sk.payloads;

public class JwtAuthRequest {
	
	private String username;
	
	private String password;

	public JwtAuthRequest() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
