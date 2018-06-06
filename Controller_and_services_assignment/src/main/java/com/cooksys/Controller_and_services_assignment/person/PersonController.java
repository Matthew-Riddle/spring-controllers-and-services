package com.cooksys.Controller_and_services_assignment.person;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("Person")
public class PersonController {

	
	PersonService personService;
	//private ArrayList<Person> people= new ArrayList<Person>(); 
	
	PersonController(PersonService personService){
		this.personService = personService;
	}
	
	@GetMapping
	public List<PersonDTO> getAllPerson() {
		return personService.getAllPerson();
	}
	
	@GetMapping("{id}")
	public PersonDTO getById(@PathVariable Long id) throws NotFound {
		return personService.getById(id);
	}
	
	@GetMapping("{id}/friends")
	public List<PersonDTO> getFriends(@PathVariable Long id) {
		return personService.getFriendDTOs(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PersonDTO addNewPerson(@RequestBody PersonDTO person) throws NameNotAvailableException {
		return personService.addNewPerson(person);
	}
	
	@PutMapping("{id}")
	public PersonDTO modifyPerson(@PathVariable Long id, @RequestBody PersonDTO person) throws NotFoundException {
		return personService.updatePerson(person, id);
	}
	
	@DeleteMapping("{id}")
	public PersonDTO deletePerson(@PathVariable Long id) throws NotFound {
		return personService.deletePerson(id);
	}
	
}
