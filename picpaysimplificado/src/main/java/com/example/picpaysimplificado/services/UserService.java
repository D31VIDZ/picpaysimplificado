package com.example.picpaysimplificado.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.picpaysimplificado.dots.UserDTO;
import com.example.picpaysimplificado.entities.User;
import com.example.picpaysimplificado.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public List<UserDTO> getAllUsers(){
		List<User> users = repository.findAll();
		List<UserDTO> userDTOS = users.stream()
								.map(u ->
										new UserDTO(u.getFirstName(),
												u.getLastName(),
												u.getEmail(),
												u.getDocument(),
												u.getPassword(),
												u.getBalance(),
												u.getUserType())).collect(Collectors.toList());
		return userDTOS;
	}
	
	public UserDTO getById(Long id) throws Exception{
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new Exception("Usuario não encontrado.");
		}
		if(user == null){
		throw new Exception("id nulo");
		}
		User user1 = user.get();

		UserDTO userDTO = new UserDTO(user1.getFirstName(),
										user1.getLastName(),
										user1.getEmail(),
										user1.getDocument(),
										user1.getPassword(),
										user1.getBalance(),
										user1.getUserType());

			return userDTO;
	}

	public UserDTO getByDocument(String ducoment) throws Exception{
		Optional<User> user = repository.findUserByDocument(ducoment);
		if(user.isEmpty()) {
			throw new Exception("Usuario não encontrado.");
		}
		User user1 = user.get();

		UserDTO userDTO = new UserDTO(user1.getFirstName(),
				user1.getLastName(),
				user1.getEmail(),
				user1.getDocument(),
				user1.getPassword(),
				user1.getBalance(),
				user1.getUserType());

			return userDTO;
	}
	
	public UserDTO createUser(UserDTO user) {
		User newUser = new User(user);
		this.save(newUser);
		return user;
	}

	public void save(User user){
		repository.save(user);
	}

	public UserDTO updateUser(Long id, UserDTO userDTO) throws Exception {
		UserDTO userDTO1 = this.getById(id);

		User user = new User(userDTO1);
		repository.save(user);

		return userDTO;
	}
	
	public UserDTO deleteUser(Long id) throws Exception {
		UserDTO userDTO = this.getById(id);
		repository.deleteById(id);

		return userDTO;
	}
}