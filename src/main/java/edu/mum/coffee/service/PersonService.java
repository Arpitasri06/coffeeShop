package edu.mum.coffee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Person;


@Service
@Transactional
public class PersonService {

	RestTemplate template = new RestTemplate();

	public List<Person> getAllProduct() {
		return (List<Person>) template.getForObject("http://localhost:8080/persons", List.class);
	}

	public void addProduct(Person pers) {
		template.postForObject("http://localhost:8080/persons", pers, Person.class);
	}

	public void editProduct(int id, Person pers) {
		template.put("http://localhost:8080/persons/" + String.valueOf(id), pers);
	}

	public void deleteProduct(int id) {
		template.delete("http://localhost:8080/persons/" + String.valueOf(id));
	}
}