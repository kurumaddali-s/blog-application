package com.codewithdurgesh.sk.blog.security;

import java.util.Date;
import java.util.function.Function;
import java.util.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

@Component
public class JwtTokenHelper {

	//configuration property specifies the expiration time for the JWT token
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	//this is the secret key signed into the JWT Signature
	private String secret = "jwtTokenKey";
	
	//retrieve username from jwt token
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	//for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//check if token expired
	private Boolean isTokenExpired(String token)
	{
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date(0));
	}
	
	//generate token for user
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	//while creating the token
	//1 define claims from token - issuer, expiration, subject and ID
	//2 sign the jwt suing the HS512 algo and secret key
	//compact JWT to url- safe string
	
	private String doGenerateToken(Map<String, Object> claims, String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	//validate token
	public Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername())
				&& !isTokenExpired(token));
	}
	
	
}
