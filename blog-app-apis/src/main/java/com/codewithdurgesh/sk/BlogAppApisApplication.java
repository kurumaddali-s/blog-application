package com.codewithdurgesh.sk;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
(scanBasePackages= {
		"com.durgesh.sk.repositories"
})
public class BlogAppApisApplication implements CommandLineRunner {
	
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
	

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
