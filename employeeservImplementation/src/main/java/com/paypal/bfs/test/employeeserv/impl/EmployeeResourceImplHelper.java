package com.paypal.bfs.test.employeeserv.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

/**
 * Class responsible for helping conversion of Model to Entity and vice versa 
 *
 */
@Component
public class EmployeeResourceImplHelper {
	
	Logger logger = LogManager.getLogger(EmployeeResourceImplHelper.class);
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Converts model to entity
	 * @param employeeModel
	 * @return EmployeeEntity
	 */
	public EmployeeEntity convertEmployeeModelToEntity(Employee employeeModel) {
		logger.info("Conversion Request Employee to EmployeeEntity, Employee = {}", employeeModel.toString());
		EmployeeEntity employeeEntity = modelMapper.map(employeeModel, EmployeeEntity.class);
		logger.info("Conversion to EmployeeEntity = {}", employeeEntity.toString());
		return employeeEntity;
	}

	/**
	 * Converts entity to model
	 * @param employeeEntity
	 * @return Employee
	 */
	public Employee convertEntityToEmployeeModel(EmployeeEntity employeeEntity) {
		logger.info("Conversion Request EmployeeEntity to Employee, EmployeeEntity = {}", employeeEntity.toString());
		Employee employeeModel = modelMapper.map(employeeEntity, Employee.class);
		logger.info("Conversion to Employee = {}", employeeModel.toString());
		return employeeModel;
	}
}
