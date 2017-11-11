package com.techelevator.vendingmachine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.coin.Coin;
import com.techelevator.products.Product;

public class VendingMachine {
	
	private final double QUARTER_WEIGHT = 5.7;
	private final double QUARTER_DIAMETER = .955;
	private final double DIME_WEIGHT = 2.27;
	private final double DIME_DIAMETER = .705;
	private final double NICKEL_WEIGHT = 5.0;
	private final double NICKEL_DIAMETER = .835;

	private BigDecimal currentBalance;
	private List<Coin> coinReturn;
	private List<Product> dispensedProducts;
	private String display;
	
	
	public VendingMachine() {
		currentBalance = new BigDecimal(0);
		coinReturn = new ArrayList<Coin>();
		dispensedProducts = new ArrayList<Product>();
		display = new String();
	}
	
	public List<Product> getDispensedProducts() {
		return dispensedProducts;
	}

	public String getDisplay() {
		return display;
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

	public boolean selectProduct(Product product) {
		if(currentBalance.compareTo(product.getPrice()) >= 0) {
			return true;
		}
		return false;
	}

	public void dispenseSelection(Product product) {
		if(selectProduct(product)) {
			dispensedProducts.add(product);
			currentBalance = currentBalance.subtract(product.getPrice());
			generateCoinReturn();
		} else {
			display = "PRICE - $" + product.getPrice();
		}
		
	}

	private void generateCoinReturn() {
		coinReturn.clear();
		while(currentBalance.compareTo(new BigDecimal(0).setScale(2, RoundingMode.FLOOR)) > 0) {
			if(currentBalance.compareTo(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR)) >= 0) {
				currentBalance = currentBalance.subtract(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR));
				coinReturn.add(new Coin(QUARTER_WEIGHT, QUARTER_DIAMETER));
			} else if(currentBalance.compareTo(new BigDecimal(.1).setScale(2, RoundingMode.FLOOR)) >= 0) {
				currentBalance = currentBalance.subtract(new BigDecimal(.1).setScale(2, RoundingMode.FLOOR));
				coinReturn.add(new Coin(DIME_WEIGHT, DIME_DIAMETER));
			} else if(currentBalance.compareTo(new BigDecimal(.05).setScale(2, RoundingMode.FLOOR)) >= 0) {
				currentBalance = currentBalance.subtract(new BigDecimal(.05).setScale(2, RoundingMode.FLOOR));
				coinReturn.add(new Coin(NICKEL_WEIGHT, NICKEL_DIAMETER));
			}
		}
		
	}
	
	




	
	

}
