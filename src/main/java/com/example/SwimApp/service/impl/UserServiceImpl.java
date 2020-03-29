package com.example.SwimApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SwimApp.model.Role;
import com.example.SwimApp.model.User;
import com.example.SwimApp.repositories.RoleRepository;
import com.example.SwimApp.repositories.UserRepository;

import com.example.SwimApp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		
	}

	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	@Override
	public Role findRoleByName(String rolename) {
		return roleRepository.findByName(rolename);
	}
	
}
