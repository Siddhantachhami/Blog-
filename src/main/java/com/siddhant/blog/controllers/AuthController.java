package com.siddhant.blog.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhant.blog.config.JwtProvider;
import com.siddhant.blog.entities.User;
import com.siddhant.blog.payloads.UserDTo;
import com.siddhant.blog.repositories.UserRepo;
import com.siddhant.blog.request.LoginRequest;
import com.siddhant.blog.response.AuthResponse;
import com.siddhant.blog.services.UserService;
import com.siddhant.blog.services.impl.CustomerUserDetailsService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;



@RestController
@RequestMapping("/auth")
public class AuthController {
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private UserRepo userRepo;
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 @Autowired
	 private CustomerUserDetailsService customerUserDetails;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody UserDTo userDto) throws Exception {
		UserDTo isExist=userRepo.findByEmail(userDto.getEmail());
		if (isExist!=null) {
			throw new Exception("email already used with another account");
		}
		
		User  newUser=new User();
		newUser.setName(userDto.getName());
		newUser.setEmail(userDto.getEmail());
		
	
		newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User savedUser=userRepo.save(newUser);
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		String token=JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token,"Register Success");
		return res;
	}
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		String token=JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token,"Login  Success");
		return res;
		
		
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails=customerUserDetails.loadUserByUsername(email);
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		if(!passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException("password not matches");
		}
			
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}

}

