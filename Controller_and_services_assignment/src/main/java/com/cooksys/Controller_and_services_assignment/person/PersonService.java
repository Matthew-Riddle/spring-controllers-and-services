package com.cooksys.Controller_and_services_assignment.person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class PersonService {

	PersonMapper personMapper;
	PersonDTO personDTO;
	
	private Long idCount =  new Long(0);
	
	ArrayList<Person>people = new ArrayList<Person>();
	
	
	PersonService(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}

	public List<PersonDTO> getAllPerson(){
		return  people.stream().map(personMapper::toDto).collect(Collectors.toList()); 
	}
	
	public PersonDTO getById(Long id) throws NotFound{
		
		if(people.contains(people.get(id.intValue()))) {
			return personMapper.toDto(people.get(id.intValue()));
		}
		else {
			throw new NotFound();
			
		}
	}
	
	public List<PersonDTO> getFriendDTOs(Long id) {
		for(Person person : people) {
			if(person.getId() == id) {
				return person.getFriends().stream().map(personMapper::toDto).collect(Collectors.toList());
			}
		}
		return null;
	}
	
	public PersonDTO addNewPerson(PersonDTO person) throws NameNotAvailableException {
		if(person.getId() != null) {
			throw new NameNotAvailableException(person.getFirstName(), person.getLastName());
		}
		checkIfNameAvailable(person.getFirstName(), person.getLastName());
		
		Person newPerson = personMapper.fromDto(person);
		newPerson.setId(idCount++);
		people.add(newPerson);
		return person;
	}
	
	private void checkIfNameAvailable(String firstName, String lastName) throws NameNotAvailableException {
		for(Person person : people) {
			if(person.getFirstName().equals(firstName) || person.getLastName().equals(lastName)) {
				throw new NameNotAvailableException(firstName, lastName);
			}
		}
		
	}

	public PersonDTO updatePerson(PersonDTO person, Long id) {
		Person newPerson = personMapper.fromDto(person);
		
		if(people.contains(newPerson)) {
			
			for(Person guy : people) {
				if(guy.equals(newPerson)) {
					guy.setFirstName(newPerson.getFirstName());
					guy.setLastName(newPerson.getLastName());
					guy.setFriends(newPerson.getFriends());
					
					return person;
				}
			}
		} else {
			try {
				throw new NotFound();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	
	public PersonDTO deletePerson(Long id) throws NotFound {
		PersonDTO personDto	= getById(id);
		Person guy = personMapper.fromDto(personDto);
		
		if(people.contains(guy)) {
			
			for(Person person : people) {
				if(person.getFriends().contains(guy)) {
					List<Person> newFriends = person.getFriends().stream()
							.filter(friend -> !person.getId().equals(friend.getId())).collect(Collectors.toList());
					ArrayList<Person> arrayFriends = new ArrayList<Person>(newFriends);
					person.setFriends(arrayFriends);
				}
			}
			people.remove(guy);
			
			return personDto;
		}
		else {
			throw new NotFound();
		}
	}
}
