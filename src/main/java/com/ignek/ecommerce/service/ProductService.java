package com.ignek.ecommerce.service;

import java.util.List;
import java.util.Optional;

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
	public void addProduct(Product product) {
		productRepo.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
	
	public Optional<Product> findProductById(Long id){
		return productRepo.findById(id);
	}
	
	public List<Product> getAllProductsByCategoryId(int id){
		return productRepo.findAllByCategory_Id(id);
	}

	
}
