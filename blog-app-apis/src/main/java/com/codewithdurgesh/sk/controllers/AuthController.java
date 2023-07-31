package com.codewithdurgesh.sk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.sk.blog.security.JwtTokenHelper;
import com.codewithdurgesh.sk.payloads.JwtAuthRequest;
import com.codewithdurgesh.sk.payloads.JwtAuthResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

//	@CrossOrigin(origins = "http://localhost:9090", exposedHeaders = "token")
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		try {
			
            authenticationManager.authenticate(
            		new UsernamePasswordAuthenticationToken(
            				request.getUsername(), 
            				request.getPassword()
            				)
            		);
        }

        catch(Exception e) {

            throw new Exception("Invalid username or password"+e);
        }
		//this.authenticate(request.getUserName(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();

		response.setToken(token);

		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

}
