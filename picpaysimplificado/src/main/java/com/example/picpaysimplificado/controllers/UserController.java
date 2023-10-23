package com.example.picpaysimplificado.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.picpaysimplificado.dots.UserDTO;
import com.example.picpaysimplificado.entities.User;
import com.example.picpaysimplificado.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll(){
		List<UserDTO> usersDtos = service.getAllUsers();
		return ResponseEntity.ok(usersDtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable Long id) throws Exception{
		UserDTO user = service.getById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/document/{document}")
	public ResponseEntity<UserDTO> getBydocument(@PathVariable String document) throws Exception{
		UserDTO user = service.getByDocument(document);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO){
		UserDTO newUSerDto = service.createUser(userDTO);
		return new ResponseEntity<>(newUSerDto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@RequestBody @Valid UserDTO userDTO) throws Exception {
		UserDTO userDTO1 = service.updateUser(id, userDTO);

		return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) throws Exception {
		UserDTO userDTO = service.deleteUser(id);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
}