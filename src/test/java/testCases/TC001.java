package testCases;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.Parallel.ExtentManager;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ProductDetailsPage;
import pageObjects.ShoppingCartPage;

@Listeners(resources.Listeners.class)
/**
 * This is our end-to-end test
 */
public class TC001 extends BasePage
{

	/**
	 * @throws IOException
	 */
	public TC001() throws IOException {
		super();
	}
	
	 @BeforeMethod
	    public void setUp() throws IOException
	    {
	        driver = getDriver();
	        driver.get(getUrl());
	    }
	 
	 @Test
	 
	 public void checkoutTest() throws IOException
	 {
		 
		 //Navigate to homepage and select product
		 HomePage home = new HomePage(driver);
		 home.selectProductByName("T-Shirt");
		 
		 //Select product details
		 String productName = "Shirt";
		 home.selectProductByName(productName.toUpperCase());

		 ProductDetailsPage product = new ProductDetailsPage(driver);
		 String size = "Medium";
		 product.selectProductSize(size.toUpperCase());
		
		 product.addToCart();
		 product.proceedToCheckout();
			
			/*
			 * OLD code
			 * ProductDetailsPage productDetails= new ProductDetailsPage(driver);
			 * productDetails.selectProductSize("L");
			 * 
			 * productDetails.selectProductColor("Black");
			 * 
			 * productDetails.selectProductQuantity("increase", 10);
			 * 
			 * productDetails.addToCart(); productDetails.proceedToCheckout();
			 */
		 
		 ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
		 String promoCode = "20OFF";
		 shoppingCart.addPromotionalCode(promoCode);
		 shoppingCart.proceedToCheckout();		 
				 
		 CheckoutPage checkout = new CheckoutPage(driver);
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
		
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //String actualOrderConfirmationText = checkout.getOrderConfirmation();
		 //String expectedOrderConfirmationText = "Your order is confirmed";
		 //String actualOrderConfirmationText = checkout.getOrderConfirmation();
		 //System.out.println(actualOrderConfirmationText);
		 
		 
		 
	 }
	 
	 
	 
	 @AfterMethod
	    public void closeBrowser()
	    {
	        driver.quit();
	    }
	 
	 
	
	

}
