package com.ordermanagement.userservice.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.userservice.dto.UserRequestDTO;
import com.ordermanagement.userservice.dto.UserResponseDto;
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

	@Override
	public UserResponseDto createUser(UserRequestDTO dto) {
		
		User user = new User();
		
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		
		User savedUser = repository.save(user);
		
		return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
	}

}
