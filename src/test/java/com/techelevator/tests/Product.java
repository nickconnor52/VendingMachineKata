package com.techelevator.tests;

import java.math.BigDecimal;

public class Product {

	private BigDecimal price;
	private String itemName;
	
	public Product() {
		setPrice(new BigDecimal(0));
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
