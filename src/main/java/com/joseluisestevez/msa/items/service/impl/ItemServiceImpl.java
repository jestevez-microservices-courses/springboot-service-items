package com.joseluisestevez.msa.items.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.joseluisestevez.msa.items.models.Item;
import com.joseluisestevez.msa.items.models.Product;
import com.joseluisestevez.msa.items.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate restClient;

	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays.asList(restClient
				.getForObject("http://service-products/list", Product[].class));
		return products.stream().map(product -> new Item(product, 1))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		Map<String, String> pathVars = new HashMap<>();
		pathVars.put("id", id.toString());
		Product product = restClient.getForObject(
				"http://service-products/view/{id}", Product.class, pathVars);
		return new Item(product, quantity);
	}

}
