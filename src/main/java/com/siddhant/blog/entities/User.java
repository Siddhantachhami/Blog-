package com.siddhant.blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	  private String name;
	 private String email;
	   private String password;
	   private String about;
	   
	

	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
    joinColumns=@JoinColumn(name="user",referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="role",referencedColumnName="id"))
	private Set<Role> roles=new HashSet<>();
	

	
}
