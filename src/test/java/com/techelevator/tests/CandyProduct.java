package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CandyProduct extends Product {
	
	public CandyProduct() {
		setPrice(new BigDecimal(.65).setScale(2, RoundingMode.FLOOR));
		setItemName("Candy");
	}

}
