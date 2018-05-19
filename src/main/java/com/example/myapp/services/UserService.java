package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.repositories.UserRepository;
import com.example.myapp.models.User;


@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session, HttpServletResponse response) {
		Optional<User> data = repository.findUserByUsername(user.getUsername());
		if(data.isPresent()) { /* Username ALREADY exists */
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		else {/* Username DOES NOT exist */
			User nUser = createUser(user);
			session.setAttribute("currentUser", nUser); 
			return nUser;
		}
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User credentials, HttpSession session) {
		Optional<User> data = repository.findUserByCredentials(credentials.getUsername(), credentials.getPassword());
		if(data.isPresent()) {
			session.setAttribute("currentUser", data.get());
			return data.get();
		}
		return null;
	}
	
	
	@PostMapping("/api/logout")
	public void logout(@RequestBody HttpSession session, HttpServletResponse response) {
			session.invalidate();
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) throws EntityNotFoundException {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			repository.save(user);
			return user;
		}
		throw new EntityNotFoundException("user not found"); 
	}
}
