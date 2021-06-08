package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

/**
 * Minimum methods for Employee Service Implementation outlined here
 *
 */
public interface EmployeeService {
	
	EmployeeEntity createEmployee(EmployeeEntity employee);
	EmployeeEntity getEmployeeById(Integer id);
	
}
