package pageObjects.Parallel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Parallel.BasePage;



public class ProductDetailsPage extends BasePage{
	
	private WebDriver driver;
	private By productSizeDropdownLocator = By.xpath("//select[@id='group_1']");
	private By productSizeOptionsLocator = By.xpath("//select[@id='group_1']/option");
	private By productColorOptionsLocator = By.xpath("//ul[@id='group_2']/li");
	private By increaseQuantityButtonLocator = By.xpath("//i[@class='material-icons touchspin-up']");
	private By decreaseQuantityButtonLocator  = By.xpath("//i[@class='material-icons touchspin-down']");
	private By addToCartButtonLocator = By.xpath("//button[@class='btn btn-primary add-to-cart']");
	private By homePageLinkLocator = By.xpath("//span[normalize-space()='Home']");
	private By continueShoppingButtonLocator = By.xpath("//button[normalize-space()='Continue shopping']");
	private By checkoutButtonLocator = By.cssSelector("a[class='btn btn-primary']");
	

	
	public ProductDetailsPage() throws IOException
	{
		super();
	}

    public void selectProductSize(String productSize) throws IOException 
    {
    	this.driver = getDriver();
    	driver.findElement(productSizeDropdownLocator).click();
        List<WebElement> productSizeOptions =  driver.findElements(productSizeOptionsLocator);
        
        
        for(WebElement option: productSizeOptions)
        {
        	String currentProductSize = option.getText();
         	
        	if((productSize.toUpperCase()).contains(currentProductSize))
        	{
        		option.click();
				break;
        	}
        }
        
    }
    
    public void selectProductColor(String productColor) throws IOException {
    	this.driver = getDriver();
    	
        List<WebElement> productColorOptions =  driver.findElements(productColorOptionsLocator);
        
        
        for(WebElement option: productColorOptions)
        {
        	String currentproductColor = option.getText();      	
			
			  if((currentproductColor.toUpperCase()).contains(productColor)) 
			  {
				  option.click(); 
				  
				  break;
				  
			  }
			 
        }
    }

    public void selectProductQuantity(String adjustmentOption, int quantity) throws IOException 
    {
    	this.driver = getDriver();
    	if((adjustmentOption.toUpperCase()).contains("INCREASE"))
    	{
    		for(int i=1; i<quantity; i++) 
    		{
    			driver.findElement(increaseQuantityButtonLocator).click();
    		}
    	}
    	else
    	{
    		for(int i=1; i<quantity; i++) 
    		{
    			driver.findElement(decreaseQuantityButtonLocator).click();
    		}
    	}
    }

    public void addToCart() throws IOException {
    	this.driver = getDriver();
        driver.findElement(addToCartButtonLocator).click();
    }

    public void navigateToHomePage() throws IOException {
    	this.driver = getDriver();
    	driver.findElement(homePageLinkLocator).click();
    }

    public void continueShopping() throws IOException {
    	this.driver = getDriver();
    	driver.findElement(continueShoppingButtonLocator).click();
    }

    public void proceedToCheckout() throws IOException {
    	this.driver = getDriver();
    	
		/*
		 * Necessary for firefox browser flow of website has changed which takes user back a step
		 */
 
		
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  
		  wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonLocator)).
		  click();
		 
    	
    	//driver.findElement(checkoutButtonLocator).click();
    }
}
