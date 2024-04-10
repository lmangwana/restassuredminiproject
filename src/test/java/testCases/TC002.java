package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.Parallel.ExtentManager;
import pageObjects.HomePage;
import pageObjects.ProductDetailsPage;
import pageObjects.ShoppingCartPage;

@Listeners(resources.Listeners.class)
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
public class TC002 extends BasePage
{

	public TC002() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setUp() throws IOException
	{
		driver = getDriver();
		driver.get(getUrl());
	}
	
	@Test
	public void addRemoveItemBasketTest() throws IOException
	{
		HomePage home = new HomePage(driver);
		String productName = "Shirt";
		home.selectProductByName(productName.toUpperCase());
		
		ProductDetailsPage product = new ProductDetailsPage(driver);
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


		ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
		
		String currentCartTotal = shoppingCart.getCurrentCartTotal();
		
		shoppingCart.removeItems(productName);
		
		String updatedCartTotal = shoppingCart.getUpdatedCartTotall(currentCartTotal);
		Assert.assertEquals(updatedCartTotal,"$5.24");


		
	}

	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
	}


}
