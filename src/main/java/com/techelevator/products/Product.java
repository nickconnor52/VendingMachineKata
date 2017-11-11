package com.techelevator.products;

import java.math.BigDecimal;

public class Product {

	private BigDecimal price;
	private String itemName;
	public int quantity;
	
	public Product() {
		setPrice(new BigDecimal(0));
		quantity = 3;
	}

	public int getQuantity() {
		return quantity;
	}

	public void decrementQuantity() {
		quantity--;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
