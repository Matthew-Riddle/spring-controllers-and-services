package com.cooksys.Controller_and_services_assignment.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NameNotAvailableException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5870029027873068899L;
	private String firstName;
	private String lastName;
	
	public NameNotAvailableException(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String getMessage() {
		return firstName + " " + lastName + "is already in use!";
	}
	
}
