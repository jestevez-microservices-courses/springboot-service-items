package com.joseluisestevez.msa.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.joseluisestevez.msa.items.models.Product;

@FeignClient(name="service-products", url="localhost:8001")
public interface ProductClientRest {

	@GetMapping("/list")
	List<Product> list();

	@GetMapping("/view/{id}")
	Product view(@PathVariable Long id);

}
