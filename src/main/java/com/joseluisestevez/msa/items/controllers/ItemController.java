package com.joseluisestevez.msa.items.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.msa.items.models.Item;
import com.joseluisestevez.msa.items.models.Product;
import com.joseluisestevez.msa.items.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("itemFeignService")
	private ItemService itemService;

	@GetMapping("/list")
	public List<Item> list() {
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "fakeViewWithCircuitBreaker")
	@GetMapping("/detail/{id}/quantity/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}

	public Item fakeViewWithCircuitBreaker(Long id, Integer quantity) {
		Product product = new Product();
		product.setId(id);
		product.setName("Product not avilable");
		product.setPrice(0.0);
		Item item = new Item();
		item.setProduct(product);
		item.setQuantity(quantity);
		return item;
	}
}
