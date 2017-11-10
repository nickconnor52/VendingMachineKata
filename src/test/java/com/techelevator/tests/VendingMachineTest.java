package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	
	private final double QUARTER_WEIGHT = 5.7;
	private final double QUARTER_DIAMETER = .955;
	private final double DIME_WEIGHT = 2.27;
	private final double DIME_DIAMETER = .705;
	private final double NICKEL_WEIGHT = 5.0;
	private final double NICKEL_DIAMETER = .835;

	private VendingMachine vendingMachine;
	private Coin coin;
	
	@Before
	public void setup() {
		vendingMachine = new VendingMachine();
	}
	
	@Test
	public void ifACoinIsInsertedThatIsOfQuarterWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		coin = new Coin(5.7, .955);
		vendingMachine.insertCoin(coin);
		Assert.assertEquals(new BigDecimal(.25).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsOfDimeWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		coin = new Coin(2.27, .705);
		vendingMachine.insertCoin(coin);
		Assert.assertEquals(new BigDecimal(.10).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsOfNickelWeightTotalVendingMachineBalanceIsUpdatedAppropriately() {
		coin = new Coin(5.0, .835);
		vendingMachine.insertCoin(coin);
		Assert.assertEquals(new BigDecimal(.05).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
		
	}
	
	@Test
	public void ifACoinIsInsertedThatIsInvalidItIsSentToTheCoinReturn() {
		coin = new Coin(1.6, .65);
		vendingMachine.insertCoin(coin);
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(1.6, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
	}
	
	@Test
	public void ifQuarterIsInsertedDisplayWillReadCurrentBalanceOf25Cents() {
		coin = new Coin(QUARTER_WEIGHT, QUARTER_DIAMETER);
		vendingMachine.insertCoin(coin);
		String display = vendingMachine.displayBalance();
		Assert.assertEquals("$0.25", display);
	}
	
	@Test
	public void ifCurrentBalanceIsZeroDisplayWillReadInsertCoin() {
		String display = vendingMachine.displayBalance();
		Assert.assertEquals("INSERT COIN", display);
	}
	
	@Test
	public void selectingColaProductWithHighEnoughBalanceReturnsTrueForSelection() {
		coin = new Coin(QUARTER_WEIGHT, QUARTER_DIAMETER);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		Product cola = new ColaProduct();
		boolean select = vendingMachine.selectProduct(cola);
		Assert.assertTrue(select);
	}
	
	@Test
	public void selectingProductWithLargeEnoughBalanceDispensesSelection() {
		coin = new Coin(QUARTER_WEIGHT, QUARTER_DIAMETER);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		Product cola = new ColaProduct();
		vendingMachine.dispenseSelection(cola);
		Assert.assertEquals(1, vendingMachine.dispensedProducts.size());
		
	}
	
	@Test
	public void selectingChipsWithAHighBalanceDispensesChips() {
		coin = new Coin(QUARTER_WEIGHT, QUARTER_DIAMETER);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		vendingMachine.insertCoin(coin);
		Product chips = new ChipsProduct();
		vendingMachine.dispenseSelection(chips);
		Assert.assertEquals("Chips", vendingMachine.dispensedProducts.get(0).getItemName());
		
	}
	
	
	

}
