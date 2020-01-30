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

import com.joseluisestevez.msa.commons.products.dto.ProductDto;

@FeignClient(name = "service-products")
public interface ProductClientRest {

	@GetMapping("/list")
	List<ProductDto> list();

	@GetMapping("/view/{id}")
	ProductDto view(@PathVariable Long id);

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	ProductDto create(@RequestBody ProductDto product);

	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	ProductDto edit(@RequestBody ProductDto product, @PathVariable Long id);

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id);
}
