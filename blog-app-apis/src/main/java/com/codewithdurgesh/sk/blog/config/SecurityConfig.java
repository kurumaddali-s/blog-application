package com.codewithdurgesh.sk.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.codewithdurgesh.sk.blog.security.CustomUserDetailService;
import com.codewithdurgesh.sk.blog.security.JwtAuthenticationEntryPoint;
import com.codewithdurgesh.sk.blog.security.JwtAuthenticationFilter;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//@EnableWebSecurity
@Configuration
//@EnableMethodSecurity
@EnableWebMvc
@EnableAutoConfiguration(exclude = { 
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class 
    })
public class SecurityConfig {
	
//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Lazy
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Lazy
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {    

        return authenticationConfiguration.getAuthenticationManager();

    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
		
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/users/", "/api/v1/auth/login")
				.permitAll()
				.requestMatchers("/v3/api-docs")
				.permitAll()
				.anyRequest()
				.authenticated());
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		      
        http.addFilterBefore(jwtAuthenticationFilter,
        		UsernamePasswordAuthenticationFilter.class);
        DefaultSecurityFilterChain defaultSFC = http.build();
        
        return defaultSFC;
    }
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticatiionProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	

}

