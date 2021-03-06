package com.joseluisestevez.msa.items.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joseluisestevez.msa.commons.products.dto.ProductDto;
import com.joseluisestevez.msa.items.clients.ProductClientRest;
import com.joseluisestevez.msa.items.models.Item;
import com.joseluisestevez.msa.items.service.ItemService;

@Service("itemFeignService")
public class ItemFeignServiceImpl implements ItemService {

	@Autowired
	private ProductClientRest productClientRest;

	@Override
	public List<Item> findAll() {
		return productClientRest.list().stream()
				.map(product -> new Item(product, 1))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(productClientRest.view(id), quantity);
	}

	@Override
	public ProductDto save(ProductDto product) {
		return productClientRest.create(product);
	}

	@Override
	public ProductDto update(ProductDto product, Long id) {
		return productClientRest.edit(product, id);
	}
	@Override
	public void deleteById(Long id) {
		productClientRest.delete(id);
	}

}
