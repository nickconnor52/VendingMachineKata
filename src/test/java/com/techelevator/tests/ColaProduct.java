package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ColaProduct extends Product {
	
	public ColaProduct() {
		setPrice(new BigDecimal(1).setScale(2, RoundingMode.FLOOR));
		setItemName("Cola");
	}

}
