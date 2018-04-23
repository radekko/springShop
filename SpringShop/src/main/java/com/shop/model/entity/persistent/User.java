package com.shop.model.entity.persistent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="SHOPUSER")
public class User {

	public User() {}
	
	public User(@NotNull @Size(min = 2, max = 20) String username, @NotNull @Size(min = 2, max = 20) String password,
			@NotNull @Email String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
	@NotNull
	@Size(min = 2, max = 20,message = "Size must be beetwen 2 and 20 signs")
	private String username;
	@NotNull(message = "Password must not be empty")
	@Size(min = 2, max = 20,message = "Size must be beetwen 2 and 20 signs")
	private String password;
	@NotEmpty(message = "Email must not be empty")
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
