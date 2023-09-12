package com.example.picpaysimplificado.entities;

import java.math.BigDecimal;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.example.picpaysimplificado.dots.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name =  "/users")
@Table(name =  "/users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	@NotBlank
	private String document;
	
	@Column(unique = true)
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@Positive
	private BigDecimal balance;
	
	private UserType userType;

	
	public User() {
	}
	
	public User(UserDTO data) {
		this.firstName = data.firstName();
		this.lastName = data.lastName();
		this.balance = data.balance();
		this.email = data.email();
		this.password = data.password();
		this.userType = data.userType();
		this.document = data.document();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
}
