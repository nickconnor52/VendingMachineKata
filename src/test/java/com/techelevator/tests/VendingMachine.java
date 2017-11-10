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

	public void insertCoin(Coin coin) {
		if(coin.getWeight() == 5.7 && coin.getDiameter() == .955) {
			currentBalance = currentBalance.add(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR));
		} else if (coin.getWeight() == 2.27 && coin.getDiameter() == .705){
			currentBalance = currentBalance.add(new BigDecimal(.1).setScale(2, RoundingMode.FLOOR));
		} else if (coin.getWeight() == 5.0 && coin.getDiameter() == .835){
			currentBalance = currentBalance.add(new BigDecimal(.05).setScale(2, RoundingMode.FLOOR));
		} else {
			coinReturn.add(coin);
		}
		
	}

	public String displayBalance() {
		if(currentBalance.equals(new BigDecimal(0))) {
			return "INSERT COIN";
		}
		return "$" + currentBalance;
	}

	public String display() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean selectProduct(Product product) {
		if(currentBalance.compareTo(product.getPrice()) >= 0) {
			return true;
		}
		return false;
	}




	
	

}
