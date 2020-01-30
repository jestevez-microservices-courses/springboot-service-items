package com.joseluisestevez.msa.items.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.joseluisestevez.msa.commons.products.dto.ProductDto;
import com.joseluisestevez.msa.items.models.Item;
import com.joseluisestevez.msa.items.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate restClient;

	@Override
	public List<Item> findAll() {
		List<ProductDto> products = Arrays.asList(restClient.getForObject(
				"http://service-products/list", ProductDto[].class));
		return products.stream().map(product -> new Item(product, 1))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		Map<String, String> pathVars = new HashMap<>();
		pathVars.put("id", id.toString());
		ProductDto product = restClient.getForObject(
				"http://service-products/view/{id}", ProductDto.class,
				pathVars);
		return new Item(product, quantity);
	}

	@Override
	public ProductDto save(ProductDto product) {
		HttpEntity<ProductDto> body = new HttpEntity<>(product);
		ResponseEntity<ProductDto> responseEntity = restClient.exchange(
				"http://service-products/create", HttpMethod.POST, body,
				ProductDto.class);
		return responseEntity.getBody();
	}

	@Override
	public ProductDto update(ProductDto product, Long id) {
		HttpEntity<ProductDto> body = new HttpEntity<>(product);
		Map<String, String> pathVars = new HashMap<>();
		pathVars.put("id", id.toString());

		ResponseEntity<ProductDto> responseEntity = restClient.exchange(
				"http://service-products/edit/{id}", HttpMethod.PUT, body,
				ProductDto.class, pathVars);
		return responseEntity.getBody();
	}

	@Override
	public void deleteById(Long id) {
		Map<String, String> pathVars = new HashMap<>();
		pathVars.put("id", id.toString());
		restClient.delete("http://service-products/delete/{id}", pathVars);
	}

}
