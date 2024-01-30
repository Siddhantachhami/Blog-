package com.siddhant.blog.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhant.blog.payloads.ApiResponse;
import com.siddhant.blog.payloads.UserDTo;
import com.siddhant.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	//POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDTo> createUser( @Valid @RequestBody UserDTo userDto){
		UserDTo  createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		}
	//PUT-update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTo> updateUser(@Valid @RequestBody UserDTo userDto,@PathVariable("userId") Integer uid){
		UserDTo updatedUser=this.userService.updateUser(userDto,uid);
		return ResponseEntity.ok(updatedUser);
}
	//DELETE Delete-user
	@DeleteMapping("/{userId}")
		public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Integer uid){
			this.userService.deleteUser(uid);
			return new ResponseEntity<ApiResponse>(new ApiResponse("User delete successfully",true),HttpStatus.OK); 
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDTo>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTo> getSingleUsers(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
		

	
	
}
