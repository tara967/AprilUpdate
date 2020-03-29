package com.example.SwimApp.service;

public interface SecurityService {

	String findLoggedInUsername();
	
	void autoLogin(String username,String password);
	
}
