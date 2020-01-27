package com.joseluisestevez.msa.items.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.msa.items.models.Item;
import com.joseluisestevez.msa.items.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("itemFeingService")
	private ItemService itemService;
	
	@GetMapping("/list")
	public List<Item> list() {
		return itemService.findAll();
	}
	
	@GetMapping("/detail/{id}/quantity/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}
}
