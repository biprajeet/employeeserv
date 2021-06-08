package com.paypal.bfs.test.employeeserv.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.ResourceNotFoundException;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EmployeeResourceImpl.class)
class EmployeeResourceImplTest {

	@Autowired
	private EmployeeResourceImpl employeeResourceImpl;
	
	@MockBean
	private EmployeeService employeeService;

	@MockBean
	private EmployeeResourceImplHelper employeeResourceImplHelper;

	
	@Test
	@DisplayName("Test for employee get by Id- Success Case")
	void testEmployeeGetById() {

		Integer id= 1;
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setId(id);
		Employee employee  = new Employee();
		employee.setId(1);
		ResponseEntity<Employee> response = new ResponseEntity<>(employee, HttpStatus.OK);
		Mockito.when(employeeService.getEmployeeById(id)).thenReturn(employeeEntity);
		Mockito.when(employeeResourceImplHelper
				.convertEntityToEmployeeModel(employeeEntity))
				.thenReturn(employee);
		assertEquals(response, employeeResourceImpl.employeeGetById(id));
	
	}
	
	@Test
	@DisplayName("Test for employee get by Id- Failure Case - Employee Not Found")
	void testEmployeeGetByIdFailureCase() {

		Integer id= 1;
		Mockito.when(employeeService.getEmployeeById(id)).thenThrow(new ResourceNotFoundException("Not found"));
		assertThrows(ResourceNotFoundException.class, () -> employeeResourceImpl.employeeGetById(id));
	
	}

	@Test
	@DisplayName("Test for employee create - Sucess Case") 
	void testEmployeeCreate() {
		Employee employee  = new Employee();
		employee.setFirstName("firstName");
		employee.setLastName("lastName");
		EmployeeEntity employeeEntity = new EmployeeEntity();
		ResponseEntity<Employee> response = new ResponseEntity<>(employee, HttpStatus.CREATED);
		Mockito.when(employeeService.createEmployee(employeeEntity)).thenReturn(employeeEntity);
		Mockito.when(employeeResourceImplHelper
				.convertEntityToEmployeeModel(employeeEntity))
				.thenReturn(employee);
		Mockito.when(employeeResourceImplHelper
				.convertEmployeeModelToEntity(employee))
				.thenReturn(employeeEntity);
		assertEquals(response, employeeResourceImpl.employeeCreate(employee));
	}
	
}
