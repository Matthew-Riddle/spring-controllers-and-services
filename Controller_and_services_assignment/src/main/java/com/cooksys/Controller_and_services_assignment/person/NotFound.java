package com.cooksys.Controller_and_services_assignment.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7326452057071222678L;

	NotFound(){}
	
	@Override
	public String getMessage() {
		return "Resource not found.";
	}
}
