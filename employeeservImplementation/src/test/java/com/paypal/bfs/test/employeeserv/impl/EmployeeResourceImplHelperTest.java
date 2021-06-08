package com.paypal.bfs.test.employeeserv.impl;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EmployeeResourceImplHelper.class)
class EmployeeResourceImplHelperTest {
	
	@Autowired
	private EmployeeResourceImplHelper employeeResourceImplHelper;
	
	@MockBean
	private ModelMapper modelMapper;

	@Test
	@DisplayName("EmployeeResourceImplHelper - Convert Model To Entity")
	void testConvertEmployeeModelToEntity() {
			
		Employee model = new Employee();

		Mockito.when(modelMapper.map(model, EmployeeEntity.class)).thenReturn(new EmployeeEntity());
		
		assertTrue(employeeResourceImplHelper.convertEmployeeModelToEntity(model) instanceof EmployeeEntity);
	}

	
	@Test
	@DisplayName("EmployeeResourceImplHelper - Convert Entity to Model")
	void testConvertEntityToEmployeeModel() {
		
		EmployeeEntity entity = new EmployeeEntity();

		Mockito.when(modelMapper.map(entity, Employee.class)).thenReturn(new Employee());
		
		assertTrue(employeeResourceImplHelper.convertEntityToEmployeeModel(entity) instanceof Employee);
		
	}
}
