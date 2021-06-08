package com.paypal.bfs.test.employeeserv.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.ResourceNotFoundException;
import com.paypal.bfs.test.employeeserv.respository.EmployeeRepository;

/**
 * Service Implementation for Employee Service
 * 
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = LogManager.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Implementation for create employee
	 */
	@Override
	public EmployeeEntity createEmployee(EmployeeEntity employee) {
		logger.debug("Employee recevied in service layer for persistence = {}", employee.toString()); 
		
		EmployeeEntity employeeEntity = this.employeeRepository.save(employee);
		
		logger.debug("Employee created : {}", employeeEntity.toString());
		
		return employeeEntity;
	}

	/**
	 * Implementation for get employee
	 */
	@Override
	public EmployeeEntity getEmployeeById(Integer id) {
		
		logger.debug("Employee Details retrieve request received with ID : {}", id );
		
		Optional<EmployeeEntity> employeeFromDb = this.employeeRepository.findById(id);

		if (employeeFromDb.isPresent()) {
			
			EmployeeEntity employeeEntity = employeeFromDb.get();
			
			logger.debug("Employee found : {}", employeeEntity.toString());
			
			return employeeEntity;
		}
		else {
			logger.warn("Employee details not found with ID : {}", id);
			
			throw new ResourceNotFoundException("Employee not found with id :  " + id);
		}

	}

}
