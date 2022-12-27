package com.ignek.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ignek.ecommerce.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
