package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

	private BigDecimal currentBalance;
	private List<Coin> coinReturn;
	
	public VendingMachine() {
		currentBalance = new BigDecimal(0);
		coinReturn = new ArrayList<Coin>();
	}
	
	public List<Coin> getCoinReturn() {
		return coinReturn;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void insertCoin(double coinWeight, double coinDiameter) {
		if(coinWeight == 5.7 && coinDiameter == .955) {
			currentBalance = currentBalance.add(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR));
		} else if (coinWeight == 2.27 && coinDiameter == .705){
			currentBalance = currentBalance.add(new BigDecimal(.1).setScale(2, RoundingMode.FLOOR));
		} else if (coinWeight == 5.0 && coinDiameter == .835){
			currentBalance = currentBalance.add(new BigDecimal(.05).setScale(2, RoundingMode.FLOOR));
		} else {
			coinReturn.add(new Coin(coinWeight, coinDiameter));
		}
		
	}


	
	

}
