package edu.mum.coffee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Product;


@Service
@Transactional
public class ProductService {

	RestTemplate template = new RestTemplate();

	public List<Product> getAllProduct() {
		return (List<Product>) template.getForObject("http://localhost:8080/products", List.class);
	}

	public void addProduct(Product prod) {
		template.postForObject("http://localhost:8080/products", prod, Product.class);
	}

	public void editProduct(int id, Product prod) {
		template.put("http://localhost:8080/products/" + String.valueOf(id), prod);
	}

	public void deleteProduct(int id) {
		template.delete("http://localhost:8080/products/" + String.valueOf(id));
	}
}