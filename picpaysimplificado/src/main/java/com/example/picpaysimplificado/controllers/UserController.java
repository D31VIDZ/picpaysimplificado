package com.example.picpaysimplificado.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.picpaysimplificado.dots.UserDTO;
import com.example.picpaysimplificado.entities.User;
import com.example.picpaysimplificado.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		List<User> users = service.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> getById(@PathVariable Long id) throws Exception{
		Optional<User> user = service.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO user){
		User newUSer = service.createUser(user);
		return new ResponseEntity<>(newUSer, HttpStatus.OK);
	}
}
