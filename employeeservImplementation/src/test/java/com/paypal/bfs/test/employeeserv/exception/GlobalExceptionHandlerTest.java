package com.paypal.bfs.test.employeeserv.exception;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

	@Autowired
	private GlobalExceptionHandler globalExceptionHandler;

	@Test
	@DisplayName("Resource not found exception Handling")
	void testResourceNotFoundException() {
		assertEquals(HttpStatus.NOT_FOUND, globalExceptionHandler
				.resourceNotFoundException(new ResourceNotFoundException("Rescource not found"), null).getStatusCode());
	}

	@Test
	@DisplayName("All other exception Handling")
	void testAllGlobleExceptionHandler() {
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				globalExceptionHandler.globleExceptionHandler(new NullPointerException("Null pointer occured"), null).getStatusCode());
	}

	@Test
	@DisplayName("Method Argument Not valid exception")
	void testHandleMethodArgumentNotValid() {
		assertEquals(HttpStatus.BAD_REQUEST,
				globalExceptionHandler.handleMethodArgumentNotValid(null, null, null, null).getStatusCode());
	}
}
