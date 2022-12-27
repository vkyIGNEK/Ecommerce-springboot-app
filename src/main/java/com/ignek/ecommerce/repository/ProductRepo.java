package com.ignek.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ignek.ecommerce.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
