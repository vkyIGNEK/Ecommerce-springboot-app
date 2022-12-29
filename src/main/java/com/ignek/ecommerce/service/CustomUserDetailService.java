package com.ignek.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ignek.ecommerce.model.CustomUserDetails;
import com.ignek.ecommerce.model.User;
import com.ignek.ecommerce.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> user = userRepo.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found !"));
		return user.map(CustomUserDetails::new).get();
	}
	
	

}
