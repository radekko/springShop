package com.shop.model.entity.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
	@NotBlank
	@Size(min = 2, max = 20,message = "Size must be beetwen 2 and 20 signs")
	private String username;
	@NotBlank
	@Size(min = 2, max = 20,message = "Size must be beetwen 2 and 20 signs")
	private String password;
	@NotBlank(message = "Email must not be empty")
	@Email(message = "Incorrect email")
	private String email;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
