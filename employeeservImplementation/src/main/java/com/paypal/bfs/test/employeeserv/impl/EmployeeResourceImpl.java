package com.paypal.bfs.test.employeeserv.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.common.LogMDCIdentifierEnum;
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

		MDC.put(LogMDCIdentifierEnum.ID.name(), id);

		logger.info("Employee resource retrieval request received with employee id = {}", id);

		Employee employeeFound = employeeResourceImplHelper
				.convertEntityToEmployeeModel(employeeService.getEmployeeById(id));

		ResponseEntity<Employee> response = new ResponseEntity<>(employeeFound, HttpStatus.OK);

		logger.info("Employee resource retrieval request response status = {} for employee id ={}, employee = {}",
				response.getStatusCode(), id, employeeFound);

		MDC.clear();

		return response;
	}

	/**
	 * Implementation of creation of employee resource
	 */
	@Override
	public ResponseEntity<Employee> employeeCreate(Employee employee) {

		MDC.put(LogMDCIdentifierEnum.NAME.name(), employee.getFirstName() + " " + employee.getLastName());

		logger.info("Employee Resource Creation request Received with employee details = {}", employee);

		Employee employeeCreated = employeeResourceImplHelper.convertEntityToEmployeeModel(
				employeeService.createEmployee(employeeResourceImplHelper.convertEmployeeModelToEntity(employee)));

		ResponseEntity<Employee> response = new ResponseEntity<>(employeeCreated, HttpStatus.CREATED);

		logger.info(
				"Employee resource creation request returned with response status = {} for employee id ={}, employee = {}",
				response.getStatusCode(), employeeCreated.getId(), employeeCreated);

		MDC.clear();

		return response;
	}

	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() {
		MDC.put(LogMDCIdentifierEnum.TYPE.name(), "Get all employees");

		logger.info("Employee Resource retrieval request received ");

		List<Employee> employees = employeeService.getAllEmployee().stream()
				.map(employeeResourceImplHelper::convertEntityToEmployeeModel).collect(Collectors.toList());

		ResponseEntity<List<Employee>> response = new ResponseEntity<>(employees, HttpStatus.OK);

		logger.info("Employee details list returned, count = {}", employees.size());

		return response;
	}

}
