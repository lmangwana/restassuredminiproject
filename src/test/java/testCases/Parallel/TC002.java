package testCases.Parallel;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Parallel.ExtentManager;
import base.Parallel.Hooks;
import pageObjects.Parallel.HomePage;
import pageObjects.Parallel.ProductDetailsPage;
import pageObjects.Parallel.ShoppingCartPage;

@Listeners(resources.Parallel.Listeners.class)
/**
 * This is our AddRemoveItemBasketTest
 * 1.Add white, medium hummingbird tshirt , quntity 2 to basket
 * 2. Proceed to continue shopping
 * 3. Add sweater, small, quantity 1
 * 4. proceeed to checkout
 * 5. Remove sweater from checkout
 * 6. Verify the total amount is actually $45.24 with an assertion
 * 
 */
public class TC002 extends Hooks
{

	public TC002() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void addRemoveItemBasketTest() throws IOException
	{
		ExtentManager.log("Starting addRemoveItemBasketTest...");
		HomePage home = new HomePage();
		String productName = "Shirt";
		home.selectProductByName(productName.toUpperCase());
		
		ProductDetailsPage product = new ProductDetailsPage();
		ExtentManager.pass("Reached the shop ProductDetailsPage ");
		String size = "Medium";
		product.selectProductSize(size.toUpperCase());
		String colour = "white";
		product.selectProductColor(colour.toUpperCase());
		String adjustmentOption = "increase";
		int quantity = 2;
		product.selectProductQuantity(adjustmentOption.toUpperCase(), quantity);
		product.addToCart();
		product.continueShopping();
		
		product.navigateToHomePage();
		productName = "sweater"; 
		home.selectProductByName(productName.toUpperCase());
		colour = "white"; 
		product.selectProductColor(colour.toUpperCase());
		product.addToCart();
		product.proceedToCheckout();


		ShoppingCartPage shoppingCart = new ShoppingCartPage();
		
		String currentCartTotal = shoppingCart.getCurrentCartTotal();
		
		shoppingCart.removeItems(productName);
		
		String updatedCartTotal = shoppingCart.getUpdatedCartTotall(currentCartTotal);


		try {
			Assert.assertEquals(updatedCartTotal,"$5.24");
			ExtentManager.pass("Total amount matches the expected amount");
			
		} catch (AssertionError e) {
			Assert.fail("Total amount does not match the expected amount, amount found was: "+updatedCartTotal);
			ExtentManager.fail("Total amount does not match the expected amount");
		}

		
	}

}
