package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {
	
	@Test
	public void ifACoinIsInsertedThatIsOfCertainSizeTotalVendingMachineBalanceIsUpdatedAppropriately() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.insertCoin(5.7, .955);
		Assert.assertEquals(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}

}
