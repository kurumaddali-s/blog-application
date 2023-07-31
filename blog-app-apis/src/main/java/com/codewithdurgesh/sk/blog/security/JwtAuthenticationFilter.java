package com.codewithdurgesh.sk.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get Token from req "Authorization" is our key
		
		System.out.println("hello inside filter ");
		
		String requestToken = request.getHeader("Authorization");
		
		//Bearer {token} 12343564345
		System.out.println("requestToken: "+requestToken);
		
		String username = null;
		
		String token = null;
		
		if(requestToken != null && requestToken.startsWith("Bearer"))
		{
			//removing the bearer part
			token = requestToken.substring(7);
			
			try {
				
				username = this.jwtTokenHelper.getUsernameFromToken(token);
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("Unable to get Jwt Token");
			}
			catch(ExpiredJwtException e)
			{
				System.out.println("Jwt Token got expired");
			}
			catch(MalformedJwtException e)
			{
				System.out.println("Invalid Jwt Token");
			}
			
			
		} else {
			
			System.out.println("Jwt token does not begin with bearer or is null");
		}
		
		//once we got the token let's validate
		//if username is null and authentication is also null
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(this.jwtTokenHelper.validateToken(token, userDetails))
			{
				//need to do authentication
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
						(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
						.buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				
				System.out.println("Invalid jwt Token");
			}
		}
		else {
			
			System.out.println("Username is null or authentication context is not null");
		}
		
		//request allowed to pass the filter
		filterChain.doFilter(request, response);
	}

}
