package edu.mum.coffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;

@Controller
@RequestMapping("/people")
public class PersonController {


	@Autowired
	private PersonService service;
	
	
	
	@GetMapping
	public String get(Model model) 
	{
		model.addAttribute("products", service.getAllPerson());
		return "persons/index";
	}
	
	@GetMapping("/add")
	public String add(@ModelAttribute("person") Person person) {
		return "persons/add";
	}
	
	@PostMapping({"/add"})
	public String add(@Valid Person person, BindingResult result) {
		if(result.hasErrors()) {
			return "persons/add";
		}
		service.addPerson(person);
		return "redirect:/persons/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, @ModelAttribute("person") Person person, Model model) {
		model.addAttribute("product", service.getPerson(id));
		return "persons/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String edit(@PathVariable int id, @Valid Person person, BindingResult result) {
		if(result.hasErrors()) {
			person.setId(id);
			return "persons/edit";
		}
		
		service.editPerson(id, person);		
		return "redirect:/persons/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		service.deletePerson(id);
		return "redirect:/persons/";
	}
}