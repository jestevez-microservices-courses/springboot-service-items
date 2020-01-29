package com.joseluisestevez.msa.items.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.msa.items.models.Item;
import com.joseluisestevez.msa.items.models.Product;
import com.joseluisestevez.msa.items.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ItemController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("itemFeignService")
	private ItemService itemService;

	@Value("${msa.configuration.text}")
	private String text;

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

	@GetMapping("/get-config")
	public ResponseEntity<Map<String, Object>> getConfig(
			@Value("${server.port}") Long port) {

		LOGGER.info("text=[{}], port=[{}]", text, port);

		Map<String, Object> map = new HashMap<>();
		map.put("text", text);
		map.put("port", port);
		if (env.getActiveProfiles().length > 0
				&& "dev".equals(env.getActiveProfiles()[0])) {
			map.put("author.name",
					env.getProperty("msa.configuration.author.name"));
			map.put("author.email",
					env.getProperty("msa.configuration.author.email"));
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
