package com.paypal.bfs.test.employeeserv.exception;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.logging.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * All exceptions will be intercepted by this method and appropriate exception sent to client
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceExistsException.class)
	public ResponseEntity<?> resourceExistsException(ResourceExistsException ex, WebRequest request) {
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), request != null ? request.getDescription(false) : "");
		
		ResponseEntity<ErrorMessage> response = new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
		
		logger.error("Response returned, status : {}", response.getStatusCode());
		
		MDC.clear();
		
		return response;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), request != null ? request.getDescription(false) : "");
		
		ResponseEntity<ErrorMessage> response = new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		
		logger.error("Response returned, status : {}", response.getStatusCode());
		
		MDC.clear();
		
		return response;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), request != null ? request.getDescription(false) : "");
		
		ResponseEntity<ErrorMessage> response = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		
		logger.error("Response returned, status : {}", response.getStatusCode());
		
		MDC.clear();
		
		return response;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), "Validation Failed", ex != null ? ex.getBindingResult().toString() : "");
		
		ResponseEntity<Object> response =  new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		
		logger.error("Response returned, status : {}", response.getStatusCode());
		
		MDC.clear();
		
		return response;
	}
}