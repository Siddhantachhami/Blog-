package com.siddhant.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhant.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>
{

}
