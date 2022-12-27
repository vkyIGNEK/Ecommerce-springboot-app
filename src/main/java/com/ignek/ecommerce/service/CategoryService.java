 package com.ignek.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignek.ecommerce.model.Category;
import com.ignek.ecommerce.repository.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> getAllCategories(){
		return categoryRepo.findAll();
	}
	
	public void addCategory(Category category) {
		categoryRepo.save(category);
	}
	
	public void deleteCategory(int id) {
		categoryRepo.deleteById(id);
	}
	
	public Optional<Category> findCategoryById(int id){
		return categoryRepo.findById(id);
	}
}
