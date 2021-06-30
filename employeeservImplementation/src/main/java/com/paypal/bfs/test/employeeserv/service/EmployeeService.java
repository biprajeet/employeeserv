package com.paypal.bfs.test.employeeserv.service;

import java.util.List;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

/**
 * Minimum methods for Employee Service Implementation outlined here
 *
 */
public interface EmployeeService {
	
	EmployeeEntity createEmployee(EmployeeEntity employee);
	EmployeeEntity getEmployeeById(Integer id);
	List<EmployeeEntity> getAllEmployee();
	
}
