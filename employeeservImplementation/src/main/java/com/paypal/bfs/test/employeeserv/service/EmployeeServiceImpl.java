package com.paypal.bfs.test.employeeserv.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.ResourceExistsException;
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

		if (!this.isEmployeeExists(employee)) {

			long timestamp = System.currentTimeMillis();

			employee.setId(null); // if user has sent id in model, setting to null so that it does not overwrite
									// the rows

			EmployeeEntity employeeEntity = this.employeeRepository.save(employee);

			logger.trace("[PERF] Time taken in INSERT entry to database {}", System.currentTimeMillis() - timestamp);

			logger.debug("Employee created : {}", employeeEntity.toString());

			return employeeEntity;

		} else {
			logger.error("Employee details already exists with first name : " + employee.getFirstName() + ", last name : "
					+ employee.getLastName() + ", date of birth : " + employee.getDateOfBirth());

			throw new ResourceExistsException(
					"Employee details already exists with first name : " + employee.getFirstName() + ", last name : "
							+ employee.getLastName() + " , date of birth : " + employee.getDateOfBirth());
		}
	}

	/**
	 * Checks if employee details exist in table and returns a boolean result
	 * 
	 * @param employee
	 * @return boolean
	 */
	private boolean isEmployeeExists(EmployeeEntity employee) {

		logger.debug("Employee details recevied checking if their details already exist = {}", employee.toString());

		long timestamp = System.currentTimeMillis();

		ExampleMatcher modelMatcher = ExampleMatcher.matching().withIgnorePaths("id")
				.withMatcher("firstName", GenericPropertyMatchers.ignoreCase())
				.withMatcher("lastName", GenericPropertyMatchers.ignoreCase())
				.withMatcher("dateOfBirth", GenericPropertyMatchers.ignoreCase());
		Example<EmployeeEntity> example = Example.of(employee, modelMatcher);

		boolean exists = this.employeeRepository.exists(example);

		logger.trace("[PERF] Time taken in SELECT entry to database {}", System.currentTimeMillis() - timestamp);

		logger.debug("Received Employee details received exists = {}", exists);

		return exists;
	}

	/**
	 * Implementation for get employee
	 */
	@Override
	public EmployeeEntity getEmployeeById(Integer id) {

		long timestamp = System.currentTimeMillis();

		logger.debug("Employee Details retrieve request received with ID : {}", id);

		Optional<EmployeeEntity> employeeFromDb = this.employeeRepository.findById(id);

		logger.trace("[PERF] Time taken in SELECT entry to database {}", System.currentTimeMillis() - timestamp);

		if (employeeFromDb.isPresent()) {

			EmployeeEntity employeeEntity = employeeFromDb.get();

			logger.debug("Employee found : {}", employeeEntity.toString());

			return employeeEntity;
		} else {
			logger.error("Employee details not found with ID : {}", id);

			throw new ResourceNotFoundException("Employee not found with id :  " + id);
		}

	}

}
