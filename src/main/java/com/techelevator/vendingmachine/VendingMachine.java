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
	private List<Coin> insertedCoins;
	private boolean soldOutDisplayCheck;
	
	
	public VendingMachine() {
		currentBalance = new BigDecimal(0).setScale(2, RoundingMode.FLOOR);
		coinReturn = new ArrayList<Coin>();
		dispensedProducts = new ArrayList<Product>();
		display = new String();
		insertedCoins = new ArrayList<Coin>();
		soldOutDisplayCheck = false;
	}
	public List<Coin> getInsertedCoins(){
		return insertedCoins;
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
			insertedCoins.add(new Coin(QUARTER_WEIGHT, QUARTER_DIAMETER));
			currentBalance = currentBalance.add(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR));
		} else if (coin.getWeight() == 2.27 && coin.getDiameter() == .705){
			insertedCoins.add(new Coin(DIME_WEIGHT, DIME_DIAMETER));
			currentBalance = currentBalance.add(new BigDecimal(.1).setScale(2, RoundingMode.FLOOR));
		} else if (coin.getWeight() == 5.0 && coin.getDiameter() == .835){
			insertedCoins.add(new Coin(NICKEL_WEIGHT, NICKEL_DIAMETER));
			currentBalance = currentBalance.add(new BigDecimal(.05).setScale(2, RoundingMode.FLOOR));
		} else {
			coinReturn.add(coin);
		}
		
	}

	public String displayBalance() {
		if(currentBalance.equals(new BigDecimal(0).setScale(2, RoundingMode.FLOOR))) {
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
	
	public boolean productInStock(Product product) {
		return product.getQuantity() > 0;
	}

	public void dispenseSelection(Product product) {
		if(!productInStock(product)) {
			if(soldOutDisplayCheck) {
				display = displayBalance();
				soldOutDisplayCheck = false;
			} else {
				display = "SOLD OUT";
				soldOutDisplayCheck = true;
			}
		} else if(selectProduct(product)) {
			dispensedProducts.add(product);
			product.decrementQuantity();
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
	
	public void pressCoinReturn() {
		for(int i = 0; i < insertedCoins.size(); i++ ) {
			coinReturn.add(insertedCoins.get(i));
		}
		insertedCoins.clear();
		display = "INSERT COIN";
	
	}
	
	public boolean exactChangeNeeded(Product product) {
		BigDecimal runningTotal = new BigDecimal(0).setScale(2, RoundingMode.FLOOR);
		List<Coin> quarterList = new ArrayList<Coin>();
		List<Coin> dimeList = new ArrayList<Coin>();
		for(int i = 0; i < insertedCoins.size(); i++) {
			if(insertedCoins.get(i).getWeight() == QUARTER_WEIGHT) {
				quarterList.add(new Coin(QUARTER_WEIGHT, QUARTER_DIAMETER));
			}
			if(insertedCoins.get(i).getWeight() == DIME_WEIGHT) {
				dimeList.add(new Coin(DIME_WEIGHT, DIME_WEIGHT));
			}
		}
		int qIndex = quarterList.size();
		int dIndex = dimeList.size();
		while(runningTotal.compareTo(product.getPrice()) < 0 && !(qIndex == 0 || dIndex == 0)  ) {
			if(qIndex != 0 && product.getPrice().compareTo(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR)) >= 0){
				runningTotal.add(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR));
				qIndex--;
			} else if(dIndex != 0 && product.getPrice().compareTo(new BigDecimal(.1).setScale(2, RoundingMode.FLOOR)) >= 0) {
				runningTotal.add(new BigDecimal(.1).setScale(2, RoundingMode.FLOOR));
				dIndex--;
			}

		}
		
		return !runningTotal.equals(product.getPrice());
	}
	
	




	
	

}
