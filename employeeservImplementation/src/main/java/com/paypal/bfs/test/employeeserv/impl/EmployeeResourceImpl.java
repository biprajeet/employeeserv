package com.paypal.bfs.test.employeeserv.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.common.EmployeeServConstants;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	Logger logger = LogManager.getLogger(EmployeeResourceImpl.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeResourceImplHelper employeeResourceImplHelper;

	/**
	 * Implementation of getting employee by Id
	 */
	@Override
	public ResponseEntity<Employee> employeeGetById(Integer id) {

		MDC.put(EmployeeServConstants.ID, id);

		logger.info("Employee resource retrieval request received with employee id = {}", id);

		Employee employeeFound = employeeResourceImplHelper
				.convertEntityToEmployeeModel(employeeService.getEmployeeById(id));

		ResponseEntity<Employee> response = new ResponseEntity<>(employeeFound, HttpStatus.OK);

		logger.info("Employee resource retrieval request response status = {} for employee id ={}, employee = {}",
				response.getStatusCode(), id, employeeFound.toString());

		MDC.clear();

		return response;
	}

	/**
	 * Implementation of creation of employee resource
	 */
	@Override
	public ResponseEntity<Employee> employeeCreate(Employee employee) {

		MDC.put(EmployeeServConstants.NAME, employee.getFirstName() + " " + employee.getLastName());

		logger.info("Employee Resource Creation request Received with employee details = {}", employee.toString());

		Employee employeeCreated = employeeResourceImplHelper.convertEntityToEmployeeModel(
				employeeService.createEmployee(employeeResourceImplHelper.convertEmployeeModelToEntity(employee)));

		ResponseEntity<Employee> response = new ResponseEntity<>(employeeCreated, HttpStatus.CREATED);

		logger.info(
				"Employee resource creation request returned with response status = {} for employee id ={}, employee = {}",
				response.getStatusCode(), employeeCreated.getId(), employeeCreated.toString());

		MDC.clear();

		return response;
	}

}
