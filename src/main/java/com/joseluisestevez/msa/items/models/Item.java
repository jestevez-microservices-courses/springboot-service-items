package com.joseluisestevez.msa.items.models;

import com.joseluisestevez.msa.commons.products.dto.ProductDto;

public class Item {

	private ProductDto product;
	private Integer quantity;

	public Item() {
		super();
	}

	public Item(ProductDto product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return product.getPrice() * quantity.doubleValue();
	}
}
