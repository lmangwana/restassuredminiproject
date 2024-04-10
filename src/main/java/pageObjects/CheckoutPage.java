package pageObjects;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CheckoutPage 
{
	private WebDriver driver;
	
	private By firstNameLocator = By.xpath("//input[@name='firstname']");
	private By lastNameLocator = By.xpath("//input[@name='lastname']");
	private By emailLocator = By.xpath("//form[@id='customer-form']//input[@name='email']");
	private By passwordLocator = By.xpath("//form[@id='customer-form']//input[@title='At least 5 characters long']");
	private By birthdateLocator = By.xpath("//input[@placeholder='MM/DD/YYYY']");
	private By termsAndPrivacyPolicyAgreementLocator = By.xpath("//input[@name='psgdpr']");
	private By continueButtonLocator;
	
	
	private By companyLocator = By.xpath("//input[@name='company']");
	private By addressLocator = By.xpath("//input[@name='address1']");
	private By addressComplementLocator = By.xpath("//input[@name='address2']");
	private By cityLocator = By.xpath("//input[@name='city']");
	private By stateLocator = By.xpath("//select[@name='id_state']/option");
	private By zipCodeLocator = By.xpath("//input[@name='postcode']");
	private By countryDropDownLocator = By.xpath("//select[@name='id_country']");
	private By unitedStatesOptionLocator = By.xpath("//select[@name='id_country']/option[@value='21']");
	private By phoneLocator = By.xpath("//input[@name='phone']");
	private By useSameAddressCheckBoxLocator = By.xpath("//input[@name='use_same_address']");


	private By deliveryMessageLocator = By.xpath("//textarea[@id='delivery_message']");
	
	private By payByCheckLocator = By.cssSelector("#payment-option-1");
	private By payByBankwireLocator = By.cssSelector("#payment-option-2");
	private By termsOfServiceAgreementLocator = By.xpath("//input[contains(@id,'conditions_to_approve')]");
	private By orderWithObligationtoPayLocator = By.xpath("//button[normalize-space()='Order with an obligation to pay']");
	
	private By orderConfirmationLocator = By.xpath("//i[@class='material-icons rtl-no-flip done']");
	
	public CheckoutPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	//Method generates the locator for the “Continue” button based on the attribute and value you pass in.
	private By clickContinueButton(String attribute, String value) 
	{
        By continueButtonLocator = By.xpath("//button[@" + attribute + "='" + value + "']");
        return continueButtonLocator;
    }
	
	public void enterPersonalInformation(int gender,String userFirstName, String userLastName, String userEmail, String userPassword, String userBirthdate) {
		
		if(gender == 0)
		{
			//gender = "MALE";
			driver.findElement(By.xpath("//input[@name='id_gender'][@value='1']")).click();
		}
		else
		{
			//gender = "FEMALE";
			driver.findElement(By.xpath("//input[@name='id_gender'][@value='2']")).click();
		}
		
		driver.findElement(firstNameLocator).sendKeys(userFirstName);
		driver.findElement(lastNameLocator).sendKeys(userLastName);
		driver.findElement(emailLocator).sendKeys(userEmail);
		driver.findElement(passwordLocator).sendKeys(userPassword);
		driver.findElement(birthdateLocator).sendKeys(userBirthdate);
		driver.findElement(termsAndPrivacyPolicyAgreementLocator).click();
		driver.findElement(clickContinueButton("data-link-action", "register-new-customer")).click();
		
		
    }

    
	
	public void enterAddresses(String userCompany, String userAddress, String userAddressComplement, String userCity, String userZipCode, String userState, String userPhoneNumber) 
	{
	    // Method body left intentionally blank
		
		driver.findElement(companyLocator).sendKeys(userCompany);
		driver.findElement(addressLocator).sendKeys(userAddress);
		driver.findElement(addressComplementLocator).sendKeys(userAddressComplement);
		driver.findElement(cityLocator).sendKeys(userCity);
		
		List<WebElement> stateOptions =  driver.findElements(stateLocator);
        for(WebElement option: stateOptions)
        {
        	String currentState = (option.getText()).toUpperCase();      	
			
			  if(currentState.contains(userState.toUpperCase())) 
			  {
				  option.click(); 
				  
				  break;
				  
			  }
			 
        }

        driver.findElement(zipCodeLocator).sendKeys(userZipCode);
        driver.findElement(countryDropDownLocator).click();
        driver.findElement(unitedStatesOptionLocator).click();
        driver.findElement(phoneLocator).sendKeys(userPhoneNumber);
        //driver.findElement(useSameAddressCheckBoxLocator).click();
        
        
        driver.findElement(clickContinueButton("name", "confirm-addresses")).click();
		 
	}

    
    public void enterShippingMethod(String userDeliveryMessage) {
        
    	driver.findElement(deliveryMessageLocator).sendKeys(userDeliveryMessage);
    	driver.findElement(clickContinueButton("name", "confirmDeliveryOption")).click();
    }

    public void enterPaymentMethod(String userPaymentMethod) {
    	
    	if(userPaymentMethod.contains("CHECK"))
    	{
    		driver.findElement(payByCheckLocator).click();
    	}
    	else
    	{
    		driver.findElement(payByBankwireLocator).click();
    	}
    	
    	driver.findElement(termsOfServiceAgreementLocator).click();
    	 try {
 			Thread.sleep(2000);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	driver.findElement(orderWithObligationtoPayLocator).click();
    	
    	 try {
 			Thread.sleep(2000);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	
    	
    }
    
    public String getOrderConfirmation()
    {
    	String orderConfirmationText = driver.findElement(orderConfirmationLocator).getText();
    	return orderConfirmationText;
    }
  
}
