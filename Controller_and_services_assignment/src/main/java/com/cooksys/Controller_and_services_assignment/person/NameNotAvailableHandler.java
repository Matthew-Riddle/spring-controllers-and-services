package com.cooksys.Controller_and_services_assignment.person;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NameNotAvailableHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ NameNotAvailableException.class })
	public ResponseEntity<Object> handleNameNotAvailableException(NameNotAvailableException ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
	}
}
