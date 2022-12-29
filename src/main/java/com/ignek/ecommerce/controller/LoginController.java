package com.ignek.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ignek.ecommerce.global.GlobalData;
import com.ignek.ecommerce.model.Role;
import com.ignek.ecommerce.model.User;
import com.ignek.ecommerce.repository.RoleRepo;
import com.ignek.ecommerce.repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}

	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepo.findById(2).get());
		user.setRoles(roles);
		userRepo.save(user);
		request.login(user.getEmail(), password);
		return "redirect:/";
	}

}
