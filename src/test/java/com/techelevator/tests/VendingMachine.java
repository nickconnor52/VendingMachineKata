package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachine {

	private BigDecimal currentBalance;
	
	public VendingMachine() {
		currentBalance = new BigDecimal(0);
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void insertCoin(double coinWeight, double coinSize) {
		currentBalance = currentBalance.add(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR));
		
	}

	
	

}
