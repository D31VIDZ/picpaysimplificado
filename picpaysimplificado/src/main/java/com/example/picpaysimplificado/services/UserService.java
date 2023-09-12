package com.example.picpaysimplificado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.picpaysimplificado.dots.UserDTO;
import com.example.picpaysimplificado.entities.User;
import com.example.picpaysimplificado.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public List<User> getAllUsers(){
		List<User> users = repository.findAll();
		return users;
	}
	
	public Optional<User> getById(Long id) throws Exception{
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new Exception("Usuario não encontrado.");
		}else {			
			return user;
		}
	}
	
	public User createUser(UserDTO user) {
		User newUser = new User(user);
		repository.save(newUser);
		return newUser;
	}
	
	public User deleteUser(User user) {
		repository.delete(user);
		return user;
	}
}
