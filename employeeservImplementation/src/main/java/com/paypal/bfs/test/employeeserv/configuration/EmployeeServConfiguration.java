package com.paypal.bfs.test.employeeserv.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Configuration class for service implementation
 *
 */
@Configuration
public class EmployeeServConfiguration {

	/**
	 * Creates a bean of type ModelMapper
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
