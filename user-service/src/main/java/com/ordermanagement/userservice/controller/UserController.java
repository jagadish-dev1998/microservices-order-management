package com.ordermanagement.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.userservice.dto.UserRequestDTO;
import com.ordermanagement.userservice.dto.UserResponseDto;
import com.ordermanagement.userservice.entity.User;
import com.ordermanagement.userservice.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/users")
public class UserController {
	
	  private static final Logger log =
	            LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
    
// legacy practice	
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//    	 
//    User saveduser = userService.saveUser(user);
//        return new ResponseEntity<User>(saveduser,HttpStatus.CREATED);
//    }
	
//Modern practice	
	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDTO dto){
		
		UserResponseDto response = userService.createUser(dto);
		return  ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
    
    @GetMapping
    public ResponseEntity<List<User>>  fetchUsers() {
        	List<User> allUsers = userService.getAllUsers() ;
    	return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

