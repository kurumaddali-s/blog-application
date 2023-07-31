package com.codewithdurgesh.sk.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {

	public UserDto()
	{
		
	}
	
	public UserDto(int id, String name, String email, String password
			, String about) {    
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	 @NotEmpty
	 @Size(min = 4, message = "Username must be minimum of 4 characters")
	private String name;
	
	 @Email(message = "Provided email address is invalid")
	private String email;
	 
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min 3 and max 10 characters long")
	private String password;
	
	@NotEmpty
	private String about;
	
}
