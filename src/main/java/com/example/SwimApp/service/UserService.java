package com.example.SwimApp.service;

import com.example.SwimApp.model.Role;
import com.example.SwimApp.model.User;

public interface UserService {

	void save(User user);
	User findByUsername(String username);

	Role findRoleByName(String rolename);
}
