package com.ignek.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ignek.ecommerce.dto.ProductDTO;
import com.ignek.ecommerce.model.Category;
import com.ignek.ecommerce.service.CategoryService;
import com.ignek.ecommerce.service.ProductService;


@Controller
public class AdminController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String getCategoryAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
		
	}
	
	@PostMapping("/admin/categories/add")
	public String postCategoryAdd(@ModelAttribute("category") Category category){
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.deleteCategory(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String UpdateCat(@PathVariable int id, Model model) {
		Optional<Category> cat = categoryService.findCategoryById(id);
		if(cat.isPresent()) {
			model.addAttribute("category", cat.get());
			return  "categoriesAdd";
		}
		return "404";
	}
	
	///Product routes
	
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	@GetMapping("/admin/products/add")
	public String getProductAdd(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		return "productsAdd";
		
	}
	
	

	

}
