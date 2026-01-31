package com.ordermanagement.userservice.service;

import java.util.List;


import com.ordermanagement.userservice.dto.UserRequestDTO;
import com.ordermanagement.userservice.dto.UserResponseDto;
import com.ordermanagement.userservice.entity.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUserById(Long id);
	
	void deleteUser(Long id);

	UserResponseDto createUser(UserRequestDTO dto);
}
