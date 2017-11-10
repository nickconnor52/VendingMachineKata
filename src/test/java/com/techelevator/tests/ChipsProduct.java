package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ChipsProduct extends Product {
	
	public ChipsProduct() {
		setPrice(new BigDecimal(.5).setScale(2, RoundingMode.FLOOR));
		setItemName("Chips");
	}

}
