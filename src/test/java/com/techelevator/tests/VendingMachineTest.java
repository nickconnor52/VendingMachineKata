package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {
	
	@Test
	public void ifACoinIsInsertedThatIsOfQuarterWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.insertCoin(5.7, .955);
		Assert.assertEquals(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsOfDimeWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.insertCoin(2.27, .705);
		Assert.assertEquals(new BigDecimal(.10).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsOfNickelWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.insertCoin(5.0, .835);
		Assert.assertEquals(new BigDecimal(.05).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsInvalidItIsSentToTheCoinReturn() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.insertCoin(1.6, .65);
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(1.6, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
		
	}

}
