package com.example.SimpleLoginApplication.LoginApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SimpleLoginApplication.LoginApplication.dao.UserRepository;
import com.example.SimpleLoginApplication.LoginApplication.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User fetchByEmailId(String email) {
		return userRepository.findByEmail(email);
	}

	public User fetchUserByEmailIdAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);

	}

	@Transactional
	public User findByToken(String token) {
		return userRepository.findByToken(token);
	}

	public void save(User user, String token) {
		User userToken = new User();
		userToken.setToken(token);
		userRepository.save(userToken);
	}
}
