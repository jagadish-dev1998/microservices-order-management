package com.ordermanagement.userservice.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.userservice.entity.User;
import com.ordermanagement.userservice.repository.UserRepository;
import com.ordermanagement.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void deleteUser(Long id) {
		repository.deleteById(id);
	}

}
