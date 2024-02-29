package com.siddhant.blog.services;

import java.util.List;

import com.siddhant.blog.payloads.UserDTo;



public interface UserService {

	public UserDTo registerUser(UserDTo userDto);;
	 public UserDTo findUserByEmail(String email);
	 UserDTo updateUser(UserDTo user, Integer userId);
	 UserDTo getUserById(Integer userId);
	 List<UserDTo> getAllUsers();
	 void deleteUser(Integer userId);
	 public List<UserDTo> searchUser(String query);
	public UserDTo findUserByJwt(String jwt);

}
