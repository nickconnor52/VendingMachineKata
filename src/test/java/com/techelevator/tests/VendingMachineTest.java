package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {
	
	@Test
	public void ifACoinIsInsertedThatIsOfQuarterWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		VendingMachine vendingMachine = new VendingMachine();
		Coin coin = new Coin();
		coin.setWeight(5.7);
		coin.setDiameter(.955);
		vendingMachine.insertCoin(coin);
		Assert.assertEquals(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsOfDimeWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		VendingMachine vendingMachine = new VendingMachine();
		Coin coin = new Coin();
		coin.setWeight(2.27);
		coin.setDiameter(.705);
		vendingMachine.insertCoin(coin);
		Assert.assertEquals(new BigDecimal(.10).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsOfNickelWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		VendingMachine vendingMachine = new VendingMachine();
		Coin coin = new Coin();
		coin.setWeight(5.0);
		coin.setDiameter(.835);
		vendingMachine.insertCoin(coin);
		Assert.assertEquals(new BigDecimal(.05).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsInvalidItIsSentToTheCoinReturn() {
		VendingMachine vendingMachine = new VendingMachine();
		Coin coin = new Coin();
		coin.setWeight(1.6);
		coin.setDiameter(.65);
		vendingMachine.insertCoin(coin);
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(1.6, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
	}
	

}
