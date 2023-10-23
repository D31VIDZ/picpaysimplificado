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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
	
	public User(UserDTO data) {
		this.firstName = data.firstName();
		this.lastName = data.lastName();
		this.balance = data.balance();
		this.email = data.email();
		this.password = data.password();
		this.userType = data.userType();
		this.document = data.document();
	}
}
