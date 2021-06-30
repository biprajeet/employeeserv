package com.paypal.bfs.test.employeeserv.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

	/**
	 * Retrieves the {@link Employee} resource by id.
	 *
	 * @param id employee id.
	 * @return {@link Employee} resource.
	 */
	@GetMapping("/v1/bfs/employees/{id}")
	ResponseEntity<Employee> employeeGetById(@Valid @PathVariable("id") Integer id);

	/**
	 * Retrieves the all {@link Employee} resources
	 * 
	 * @return List<{@link Employee}> resources
	 */
	@GetMapping("/v1/bfs/employees")
	ResponseEntity<List<Employee>> getAllEmployees();

	/**
	 * Creates the {@link Employee} passed as input
	 * 
	 * @param employee
	 * @return {@link Employee} resource
	 */
	@PostMapping("/v1/bfs/employees")
	ResponseEntity<Employee> employeeCreate(@Valid @RequestBody Employee employee);

}
