package com.paypal.bfs.test.employeeserv.impl;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EmployeeResourceImplHelper.class)
class EmployeeResourceImplHelperTest {

	@Test
	@DisplayName("EmployeeResourceImplHelper - Convert Model To Entity")
	void testConvertEmployeeModelToEntity() {
		Employee model = new Employee();
		model.setFirstName("First Name");
		model.setLastName("First Name");
		model.setDateOfBirth(new Date(2323223232L));
		//model.setAddress(address);
	}

	
	@Test
	@DisplayName("EmployeeResourceImplHelper - Convert Entity to Model")
	void testConvertEntityToEmployeeModel() {
		
	}
}
