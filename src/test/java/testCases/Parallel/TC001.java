package testCases.Parallel;
import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Parallel.ExtentManager;
import base.Parallel.Hooks;
import pageObjects.Parallel.CheckoutPage;
import pageObjects.Parallel.HomePage;
import pageObjects.Parallel.ProductDetailsPage;
import pageObjects.Parallel.ShoppingCartPage;

@Listeners(resources.Parallel.Listeners.class)
/**
 * This is our end-to-end test
 */
public class TC001 extends Hooks
{

	/**
	 * @throws IOException
	 */
	public TC001() throws IOException {
		super();
	}
	
	 @Test
	 
	 public void checkoutTest() throws IOException
	 {
		 ExtentManager.log("Starting checkoutTest...");
		 //Navigate to homepage and select product
		 HomePage home = new HomePage();
		 home.selectProductByName("T-Shirt");
		 
		 //Select product details
		 String productName = "Shirt";
		 home.selectProductByName(productName.toUpperCase());

		 ProductDetailsPage product = new ProductDetailsPage();
		 ExtentManager.pass("Reached the shop ProductDetailsPage ");
		 String size = "Medium";
		 product.selectProductSize(size.toUpperCase());
		
		 product.addToCart();
		 product.proceedToCheckout();
		 
		 ShoppingCartPage shoppingCart = new ShoppingCartPage();
		 String promoCode = "20OFF";
		 shoppingCart.addPromotionalCode(promoCode);
		 shoppingCart.proceedToCheckout();		 
				 
		 CheckoutPage checkout = new CheckoutPage();
		 int randomGender = (int)((Math.random())*2);
		 String firstName = getRandomString();
		 String lastName = getRandomString(); 
		 String email = getRandomString()+"@mail.com";
		 String password = getRandomAlphaNumeric();
		 String birthdate = getRandomBirthdate();

		 checkout.enterPersonalInformation(randomGender,firstName,lastName,email,password, birthdate);
		 
		 checkout.enterAddresses(getRandomString(), getRandomPhoneNumber().substring(8) +" " +getRandomString(), getRandomString(), getRandomString(), getRandomPhoneNumber().substring(1, 6), "Idaho", getRandomPhoneNumber());

		 checkout.enterShippingMethod("Enter code 1245 at gate and leave package at front door");

		 String paymentMethod = "check";
		 checkout.enterPaymentMethod(paymentMethod.toUpperCase());
		 ExtentManager.pass("Successfully placed the order");
		
		  
			/*
			 * String actualOrderConfirmationText = checkout.getOrderConfirmation(); String
			 * expectedOrderConfirmationText = "Your order is confirmed";
			 * Assert.assertEquals(actualOrderConfirmationText,
			 * expectedOrderConfirmationText)
			 */
		 
		 
		 
	 }
	 

	 
	
	

}
