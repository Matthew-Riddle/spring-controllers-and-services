package com.cooksys.Controller_and_services_assignment.person;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

	PersonDTO toDto(Person person);
	
	Person fromDto(PersonDTO personDto);
	
}
