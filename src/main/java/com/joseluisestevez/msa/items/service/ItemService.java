package com.joseluisestevez.msa.items.service;

import java.util.List;

import com.joseluisestevez.msa.items.models.Item;
import com.joseluisestevez.msa.items.models.Product;

public interface ItemService {
	List<Item> findAll();

	Item findById(Long id, Integer quantity);

	Product save(Product product);

	Product update(Product product, Long id);

	void deleteById(Long id);
}
