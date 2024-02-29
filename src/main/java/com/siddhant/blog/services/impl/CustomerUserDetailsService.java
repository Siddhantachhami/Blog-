package com.siddhant.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhant.blog.entities.User;
import com.siddhant.blog.payloads.UserDTo;
import com.siddhant.blog.repositories.UserRepo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomerUserDetailsService implements UserDetailsService{
    @Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDTo user=userRepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("user not found with email +"+username);
		}
		List<GrantedAuthority> authorities=new ArrayList<>();
		return new  org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}

}

