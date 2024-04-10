package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ProductDetailsPage {
	
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
	

	
	public ProductDetailsPage(WebDriver driver)
	{
		this.driver = driver;
	}

    public void selectProductSize(String productSize) 
    {
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
    
    public void selectProductColor(String productColor) {
       
    	
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

    public void selectProductQuantity(String adjustmentOption, int quantity) 
    {
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

    public void addToCart() {
        driver.findElement(addToCartButtonLocator).click();
    }

    public void navigateToHomePage() {
    	driver.findElement(homePageLinkLocator).click();
    }

    public void continueShopping() {
    	driver.findElement(continueShoppingButtonLocator).click();
    }

    public void proceedToCheckout() {
    	
		/*
		 * Necessary for firefox browser flow of website has changed which takes user back a step
		 */
 
		
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  
		  wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonLocator)).
		  click();
		 
    	
    	//driver.findElement(checkoutButtonLocator).click();
    }
}
