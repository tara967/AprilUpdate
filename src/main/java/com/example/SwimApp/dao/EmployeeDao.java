package com.example.SwimApp.dao;

import java.util.List;

import com.example.SwimApp.model.Employee;


public interface EmployeeDao {
	//in DAO data access object, it provides interface to DB/ mechanism
	//void methods here used to pass in cus and employees, doesnt return anything
	void insertEmployee(Employee cus);
	void insertEmployees(List<Employee> employees);
	//List defined below to be used/ called elsewhere
	List<Employee> getAllEmployees();
	Employee getEmployeeById(String empId);
}