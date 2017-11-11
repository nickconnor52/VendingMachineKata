package com.techelevator.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.coin.Coin;
import com.techelevator.products.CandyProduct;
import com.techelevator.products.ChipsProduct;
import com.techelevator.products.ColaProduct;
import com.techelevator.products.Product;
import com.techelevator.vendingmachine.VendingMachine;

public class VendingMachineTest {
	
	private final double QUARTER_WEIGHT = 5.7;
	private final double QUARTER_DIAMETER = .955;
	private final double DIME_WEIGHT = 2.27;
	private final double DIME_DIAMETER = .705;
	private final double NICKEL_WEIGHT = 5.0;
	private final double NICKEL_DIAMETER = .835;

	private VendingMachine vendingMachine;
	private Coin coin;
	private Coin quarter;
	private Coin nickel;
	private Coin dime;
	
	@Before
	public void setup() {
		vendingMachine = new VendingMachine();
		quarter = new Coin(QUARTER_WEIGHT, QUARTER_DIAMETER);
		nickel = new Coin(NICKEL_WEIGHT, NICKEL_DIAMETER);
		dime = new Coin(DIME_WEIGHT, DIME_DIAMETER);
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
		vendingMachine.insertCoin(quarter);
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
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product cola = new ColaProduct();
		boolean select = vendingMachine.selectProduct(cola);
		Assert.assertTrue(select);
	}
	
	@Test
	public void selectingProductWithLargeEnoughBalanceDispensesSelection() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product cola = new ColaProduct();
		vendingMachine.dispenseSelection(cola);
		Assert.assertEquals(1, vendingMachine.getDispensedProducts().size());
		
	}
	
	@Test
	public void selectingChipsWithAHighBalanceDispensesChips() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product chips = new ChipsProduct();
		vendingMachine.dispenseSelection(chips);
		Assert.assertEquals("Chips", vendingMachine.getDispensedProducts().get(0).getItemName());
		
	}
	
	@Test
	public void selectingCandyObjectWithInsufficientFundsDisplaysPriceAndItemPrice() {
		vendingMachine.insertCoin(quarter);
		Product candy = new CandyProduct();
		vendingMachine.dispenseSelection(candy);
		Assert.assertEquals("PRICE - $0.65", vendingMachine.getDisplay());
		
	}
	
	@Test
	public void ifAnItemIsDispensedTheValueOfTheItemIsDecrementedFromTheCurrentBalance() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product cola = new ColaProduct();
		vendingMachine.dispenseSelection(cola);
		Assert.assertEquals(new BigDecimal(0).setScale(2, RoundingMode.FLOOR), vendingMachine.getCurrentBalance());
	}
	
	@Test
	public void anItemIsDispensedWithExtraBalanceInsertedAndChangeIsAddedToCoinReturn() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(nickel);
		Product cola = new ColaProduct();
		vendingMachine.dispenseSelection(cola);
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(NICKEL_WEIGHT, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
	}
	
	@Test
	public void anItemIsDispensedWithAnExtra10CentsWillReturnADime() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(dime);
		Product cola = new ColaProduct();
		vendingMachine.dispenseSelection(cola);
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(DIME_WEIGHT, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
		
	}
	
	@Test
	public void anItemIsDispensedWithAnExtra15CentsWillReturnADimeAndNickel() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(nickel);
		Product cola = new ColaProduct();
		vendingMachine.dispenseSelection(cola);
		Assert.assertEquals(2, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(DIME_WEIGHT, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
		Assert.assertEquals(NICKEL_WEIGHT, vendingMachine.getCoinReturn().get(1).getWeight(), .01);
	}
	
	@Test
	public void anItemIsDispensedWithAnExtra25CentsWillReturnAQuarter() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product cola = new ColaProduct();
		vendingMachine.dispenseSelection(cola);
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(QUARTER_WEIGHT, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
		
	}
	
	@Test
	public void returnCoinButtonReturnsAllTheInputtedChangeFromMachine() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.pressCoinReturn();
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(QUARTER_WEIGHT, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
	}
	
	@Test
	public void returnCoinButtonReturnsAllTheInputtedDimesFromMachine() {
		vendingMachine.insertCoin(dime);
		vendingMachine.pressCoinReturn();
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(DIME_WEIGHT, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
	}
	
	@Test
	public void returnCoinButtonReturnsAllTheInputtedNickelsFromMachine() {
		vendingMachine.insertCoin(nickel);
		vendingMachine.pressCoinReturn();
		Assert.assertEquals(1, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(NICKEL_WEIGHT, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
	}
	
	@Test
	public void returnCoinButtonReturnsAllTheInputtedNickelsAndQuartersFromMachine() {
		vendingMachine.insertCoin(nickel);
		vendingMachine.insertCoin(nickel);
		vendingMachine.insertCoin(quarter);
		vendingMachine.pressCoinReturn();
		Assert.assertEquals(3, vendingMachine.getCoinReturn().size());
		Assert.assertEquals(NICKEL_WEIGHT, vendingMachine.getCoinReturn().get(0).getWeight(), .01);
	}
	
	@Test
	public void returnCoinButtonUpdatesDisplayToSayINSERTCOIN() {
		vendingMachine.insertCoin(nickel);
		vendingMachine.pressCoinReturn();
		Assert.assertEquals("INSERT COIN", vendingMachine.getDisplay());
	}
	
	@Test
	public void dispensingAProductThatIsOutOfStockTheMachineDisplaysSOLDOUTAndDoesNotDispense() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product chips = new ChipsProduct();
		vendingMachine.dispenseSelection(chips);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.dispenseSelection(chips);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.dispenseSelection(chips);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.dispenseSelection(chips);
		Assert.assertEquals(3, vendingMachine.getDispensedProducts().size());
		Assert.assertEquals("SOLD OUT", vendingMachine.getDisplay());
	}
	
	@Test
	public void selectingItemThatIsSoldOutAndThenCheckingDisplayAgainWillShowBalanceRemaining() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product chips = new ChipsProduct();
		vendingMachine.dispenseSelection(chips);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.dispenseSelection(chips);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.dispenseSelection(chips);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.dispenseSelection(chips);
		vendingMachine.dispenseSelection(chips);
		Assert.assertEquals("$0.50", vendingMachine.getDisplay());
	}
	
	@Test
	public void selectingItemThatIsSoldOutAndThenCheckingDisplayAgainWillShowINSERTCOINIfBalanceIsZero() {
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product chips = new ChipsProduct();
		vendingMachine.dispenseSelection(chips);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.dispenseSelection(chips);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.dispenseSelection(chips);
		vendingMachine.dispenseSelection(chips);
		vendingMachine.dispenseSelection(chips);
		Assert.assertEquals("INSERT COIN", vendingMachine.getDisplay());
	}
	
	@Test
	public void exactChangeNeededLogicWorksForCandyWith3QuartersInserted() {
		// Adding quarters to machine to give it a coin collection, not representative of current user inputting money
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		Product candy = new CandyProduct();
		vendingMachine.dispenseSelection(candy);
		Assert.assertTrue(vendingMachine.exactChangeNeeded(candy));
		
	}
	
	
	
	

}
