package edu.mum.coffee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;


@Service
@Transactional
public class PersonService {

	RestTemplate template = new RestTemplate();

	public List<Person> getAllPerson() {
		return (List<Person>) template.getForObject("http://localhost:8080/persons", List.class);
	}

	public void addPerson(Person person) {
		template.postForObject("http://localhost:8080/persons", person, Person.class);
	}

	public void editPerson(int id, Person person) {
		template.put("http://localhost:8080/persons/" + String.valueOf(id), person);
	}

	public void deletePerson(int id) {
		template.delete("http://localhost:8080/persons/" + String.valueOf(id));
	}
	
	public Person getPerson(int id) {
		return template.getForObject("http://localhost:8080/persons/" + String.valueOf(id),Person.class);
	}
}