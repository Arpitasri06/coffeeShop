package edu.mum.coffee.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Order;

@Service
@Transactional
public class OrderService {

	RestTemplate template = new RestTemplate();

	public List<Order> getAllOrder() {
		return (List<Order>) template.getForObject("http://localhost:8080/orders", List.class);
	}

	public void addProduct(Order ord) {
		template.postForObject("http://localhost:8080/orders", ord, Order.class);
	}

	public void editProduct(int id, Order ord) {
		template.put("http://localhost:8080/orders/" + String.valueOf(id), ord);
	}

	public void deleteProduct(int id) {
		template.delete("http://localhost:8080/orders/" + String.valueOf(id));
	}
}