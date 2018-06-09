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

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.ProductService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@GetMapping
	public String get(Model model) 
	{
		model.addAttribute("orders", service.findAll());
		return "orders/index";
	}
	
	@GetMapping("/add")
	public String add(@ModelAttribute("order") Order order) {
		return "orders/add";
	}
	
	@PostMapping({"/add"})
	public String add(@Valid Order order, BindingResult result) {
		if(result.hasErrors()) {
			return "orders/add";
		}
		service.addOrder(order);
		return "redirect:/orders/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, @ModelAttribute("order") Order order, Model model) {
		model.addAttribute("product", service.getOrder(id));
		return "orders/edit";
	}
	
	
	@PostMapping("/edit/{id}")
	public String edit(@PathVariable int id, @Valid Order order, BindingResult result) {
		if(result.hasErrors()) {
			order.setId(id);
			return "orders/edit";
		}
		
		service.editOrder(id, order);		
		return "redirect:/orders/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		service.deleteOrder(id);
		return "redirect:/orders/";
	}
}
