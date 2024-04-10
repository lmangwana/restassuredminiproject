package pageObjects;

import java.util.List;
import org.openqa.selenium.*;

/**
 * HomePage class represents the home page of the website.
 * It contains methods to interact with the home page elements.
 * 
 * @author LuthoMangwana
 */
public class HomePage 
{
	/**
	 * WebDriver instance to interact with the web page
	 */
	private WebDriver driver;
	
	/**
	 * Locator for popular products on the home page
	 */
	private By popularProductsLocator = By.xpath("//div[@class='products']/article");
	
	/**
	 * Constructor for HomePage class.
	 * Initialises the WebDriver instance.
	 *
	 * @param driver WebDriver instance
	 */
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	/**
	 * Method to select a product from the list of popular products.
	 * It iterates over the list of products and clicks on the product that matches the productName.
	 *
	 * @param productName Name of the product to select
	 */
	public void selectProductByName(String productName)
	{
		List<WebElement> productList = driver.findElements(popularProductsLocator);
		
		for(WebElement product : productList){
			
			String currentProductName = product.getText();
			
			if((currentProductName.toUpperCase()).contains(productName))
			{
				product.click();
				break;
			}
		}
	}
}
