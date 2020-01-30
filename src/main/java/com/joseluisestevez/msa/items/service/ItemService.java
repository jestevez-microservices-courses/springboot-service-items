package com.joseluisestevez.msa.items.service;

import java.util.List;

import com.joseluisestevez.msa.commons.products.dto.ProductDto;
import com.joseluisestevez.msa.items.models.Item;

public interface ItemService {
	List<Item> findAll();

	Item findById(Long id, Integer quantity);

	ProductDto save(ProductDto product);

	ProductDto update(ProductDto product, Long id);

	void deleteById(Long id);
}
