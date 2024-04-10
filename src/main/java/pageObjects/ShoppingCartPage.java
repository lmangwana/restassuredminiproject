package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {
	private WebDriver driver;
	private By promoCodeLinkLocator = By.xpath("//a[normalize-space()='Have a promo code?']");
	private By promoCodeInputLocator = By.xpath("//input[@placeholder='Promo code']");
	private By addPromoCodeButtonLocator = By.xpath("//span[normalize-space()='Add']");
	private By proceedToCheckoutButtonLocator = By.xpath("//a[normalize-space()='Proceed to checkout']");
	//private By deleteItemIconLocator = By.xpath("//li[1]//div[1]//div[3]//div[1]//div[3]//div[1]//a[1]//i[1]");
	
	private By deleteItemIconLocator = By.xpath(".//a[@class='remove-from-cart']");
	private By continueShoppingLinkLocator = By.xpath("//body/main/section[@id='wrapper']/div[@class='container']/div[@id='content-wrapper']/section[@id='main']/div[@class='cart-grid row']/div[@class='cart-grid-body col-xs-12 col-lg-8']/a[1]");
	
	
	private By cartItemsLocator = By.xpath("//ul[@class='cart-items']/li");
	
	private By cartTotalLocator = By.xpath("//div[@class='cart-summary-line cart-total']/span[@class='value']");
	public ShoppingCartPage(WebDriver driver)
	{
		this.driver = driver;
	}

    public void addPromotionalCode(String promoCode) {
    	
    	driver.findElement(promoCodeLinkLocator).click();
    	driver.findElement(promoCodeInputLocator).sendKeys(promoCode);
    	driver.findElement(addPromoCodeButtonLocator).click();
    	
    	
        
    }

    public void updateItemQuantity() {
        // Implementation goes here
    }

    public void removeItems(String productName) 
    {
    	List<WebElement> cartItems = driver.findElements(cartItemsLocator);
    	
    	for(WebElement items: cartItems)
    	{
    		String currentItem = items.getText(); 		
    		if((currentItem).contains(productName))
    		{
    			items.findElement(deleteItemIconLocator).click();
    		}
    	}
        
    }

    public void proceedToCheckout() {
        driver.findElement(proceedToCheckoutButtonLocator).click();
    }

    public void navigateToProductDetails() {
        // Implementation goes here
    }

    public String getCurrentCartTotal() {
    	
    	String cartTotal = driver.findElement(cartTotalLocator).getText();
    	
    	return cartTotal;
        
    }
    
    public String getUpdatedCartTotall(String oldCartTotal) {

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    	wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(cartTotalLocator, oldCartTotal)));

    	String updatedCartTotal = driver.findElement(cartTotalLocator).getText();

    	return updatedCartTotal;

    }
    
    
}
