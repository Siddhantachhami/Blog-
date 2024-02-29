package com.siddhant.blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.siddhant.blog.entities.User;
import com.siddhant.blog.payloads.UserDTo;

public interface UserRepo  extends JpaRepository<User,Integer>{
	public UserDTo findByEmail(String email);
	@Query("select u from User u where u.name LIKE %:query% OR u.email LIKE %:query%")
	public List<UserDTo> searchUser(@Param("query")String query);
	
}
