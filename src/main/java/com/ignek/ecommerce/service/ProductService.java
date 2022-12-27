package com.ignek.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignek.ecommerce.model.Product;
import com.ignek.ecommerce.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}

	
}
