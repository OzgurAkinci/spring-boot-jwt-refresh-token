package com.app.spring.security.jwt.payload.response;

import com.app.spring.security.jwt.dto.UserDTO;
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private UserDTO user;

	public JwtResponse(String accessToken, String refreshToken, UserDTO user) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.user = user;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
