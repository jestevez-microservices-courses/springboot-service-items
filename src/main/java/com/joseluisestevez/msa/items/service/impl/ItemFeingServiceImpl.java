package com.joseluisestevez.msa.items.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.joseluisestevez.msa.items.models.Item;
import com.joseluisestevez.msa.items.service.ItemService;
import com.joseluisestevez.msa.items.clients.ProductClientRest;

@Primary
@Service("itemFeingService")
public class ItemFeingServiceImpl implements ItemService {
	
	@Autowired
	private ProductClientRest productClientRest;

	@Override
	public List<Item> findAll() {
		return productClientRest.list().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(productClientRest.view(id), quantity );
	}

}
