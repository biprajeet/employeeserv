package com.paypal.bfs.test.employeeserv.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.ResourceNotFoundException;
import com.paypal.bfs.test.employeeserv.respository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EmployeeServiceImpl.class)
class EmployeeServiceImplTest {

	Logger logger = LogManager.getLogger(EmployeeServiceImplTest.class);
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Test
	@DisplayName("EmployeeServiceImpl - Create Employee Test")
	void testCreateEmployee() {
		logger.info("Test for Service Layer of Employee Creation");
		
		EmployeeEntity employeeInput = new EmployeeEntity();
		EmployeeEntity employeeOutput = new EmployeeEntity();
		
		Mockito.when(employeeRepository.save(employeeInput)).thenReturn(employeeOutput);
		
		assertEquals(employeeOutput, employeeServiceImpl.createEmployee(employeeInput));
	}
	
	@Test
	@DisplayName("EmployeeServiceImpl - Get Employee Test - Positive test when ID found")
	void testGetEmployee() {
		logger.info("Test for Service Layer of Employee Get by Id - Positive test when ID found");
		
		Integer id = 1;
		
		EmployeeEntity entity = new EmployeeEntity();
		entity.setId(1);
		
		Optional<EmployeeEntity> employeeOutput = Optional.of(entity);
		
		Mockito.when(employeeRepository.findById(id)).thenReturn(employeeOutput);
		
		assertEquals(employeeOutput.get(), employeeServiceImpl.getEmployeeById(id));
	}

	
	@Test
	@DisplayName("EmployeeServiceImpl - Get Employee Test - Negative test when ID not found")
	void testGetEmployeeNotFound() {
		logger.info("Test for Service Layer of Employee Get by Id - Negative test when ID not found");
		
		Integer id = 1;
		
		Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class,  ()->employeeServiceImpl.getEmployeeById(id));
	}
}
