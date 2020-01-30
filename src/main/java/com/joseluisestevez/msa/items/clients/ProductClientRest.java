package com.joseluisestevez.msa.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.joseluisestevez.msa.items.models.Product;

@FeignClient(name = "service-products")
public interface ProductClientRest {

	@GetMapping("/list")
	List<Product> list();

	@GetMapping("/view/{id}")
	Product view(@PathVariable Long id);

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	Product create(@RequestBody Product product);

	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	Product edit(@RequestBody Product product, @PathVariable Long id);

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id);
}
