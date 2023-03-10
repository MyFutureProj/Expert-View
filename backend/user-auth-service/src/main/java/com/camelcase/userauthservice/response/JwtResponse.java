package com.camelcase.userauthservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
	private String username;
	private String email;
	private String tokenType;
	private String accessToken;
}
