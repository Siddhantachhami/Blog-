package com.siddhant.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhant.blog.entities.User;
import com.siddhant.blog.exceptions.ResourceNotFoundException;
import com.siddhant.blog.payloads.UserDTo;
import com.siddhant.blog.repositories.UserRepo;
import com.siddhant.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserDTo createUser(UserDTo userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
		
	}

	@Override
	public UserDTo updateUser(UserDTo userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.userRepo.save(user);
		UserDTo useDto1=this.userToDto(updatedUser);
		return useDto1;
	}

	@Override
	public UserDTo getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDTo> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<UserDTo> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);

	}
	public User dtoToUser(UserDTo userDto) {
		User user=this.modelMapper.map(userDto, User.class);
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
		
	}
	
	public UserDTo userToDto(User user) {
		UserDTo userDto=this.modelMapper.map(user, UserDTo.class); 
		return userDto;
		
		
	}

}
