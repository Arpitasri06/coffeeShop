package edu.mum.coffee.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;

@Service
@Transactional
public class OrderService {

	RestTemplate template = new RestTemplate();

	public List<Order> findAll() {
		return (List<Order>) template.getForObject("http://localhost:8080/orders", List.class);
	}

	public void addOrder(Order order) {
		template.postForObject("http://localhost:8080/orders", order, Order.class);
	}

	public void editOrder(int id, Order order) {
		template.put("http://localhost:8080/orders/" + String.valueOf(id), order);
	}

	public void deleteOrder(int id) {
		template.delete("http://localhost:8080/orders/" + String.valueOf(id));
	}
	
	public Order getOrder(int id) {
		return template.getForObject("http://localhost:8080/orders/" + String.valueOf(id),Order.class);
	}
	
}