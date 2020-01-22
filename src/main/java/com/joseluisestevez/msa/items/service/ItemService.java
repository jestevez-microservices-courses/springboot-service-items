package com.joseluisestevez.msa.items.service;

import java.util.List;

import com.joseluisestevez.msa.items.models.Item;

public interface ItemService {
	List<Item> findAll();
	
	Item findById(Long id, Integer quantity);
}
