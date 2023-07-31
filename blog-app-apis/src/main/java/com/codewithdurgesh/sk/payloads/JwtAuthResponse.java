package com.codewithdurgesh.sk.payloads;

import java.util.Objects;

public class JwtAuthResponse {

	private String token;

	public JwtAuthResponse() {
		
	}

	@Override
	public String toString() {
		return "JwtAuthResponse [token=" + token + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JwtAuthResponse other = (JwtAuthResponse) obj;
		return Objects.equals(token, other.token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
