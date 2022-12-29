package com.ignek.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ignek.ecommerce.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
