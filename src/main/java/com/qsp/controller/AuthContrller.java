package com.qsp.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.qsp.listiner.AddClientListener;
import com.qsp.requestdto.AuthRequst;
import com.qsp.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthContrller {

	private final AuthenticationManager authentictionManager;
	private final JWTUtil jwtutil;
	@PostMapping("/authenticate")
	public String gnerateToken(@RequestBody AuthRequst authrequest) {

		authentictionManager.authenticate(
				new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
		return jwtutil.generateToken(authrequest.getUsername());
	}
}
