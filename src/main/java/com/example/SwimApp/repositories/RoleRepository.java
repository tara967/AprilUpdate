package com.example.SwimApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwimApp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
}
