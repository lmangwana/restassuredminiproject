package pageObjects.Parallel;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.*;

import base.Parallel.BasePage;

/**
 * HomePage class represents the home page of the website.
 * It contains methods to interact with the home page elements.
 * 
 * @author LuthoMangwana
 */
public class HomePage extends BasePage
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
	 * @throws IOException 
	 */
	public HomePage() throws IOException
	{
		super();
	}
	
	/**
	 * Method to select a product from the list of popular products.
	 * It iterates over the list of products and clicks on the product that matches the productName.
	 *
	 * @param productName Name of the product to select
	 * @throws IOException 
	 */
	public void selectProductByName(String productName) throws IOException
	{
		this.driver = getDriver();
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
