package com.siddhant.blog.services;

import java.util.List;

import com.siddhant.blog.payloads.UserDTo;

public interface UserService {
	 UserDTo createUser(UserDTo user);
	 UserDTo updateUser(UserDTo user, Integer userId);
	 UserDTo getUserById(Integer userId);
	 List<UserDTo> getAllUsers();
	 void deleteUser(Integer userId);

}
