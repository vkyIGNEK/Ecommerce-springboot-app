package com.ignek.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ignek.ecommerce.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	List<Product> findAllByCategory_Id(int id);

}
