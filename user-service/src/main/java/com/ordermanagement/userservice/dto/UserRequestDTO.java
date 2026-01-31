package com.ordermanagement.userservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequestDTO {
	
	@NotBlank(message = "user name is mandatory")
	@Size(min =3,max=120,message ="user name must be 3-20 charactes")
	private String username;
	
	@NotBlank(message="email is mandatory")
	@Email(message="invalid email format")
	private String email;
	private String password;
	private String role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
