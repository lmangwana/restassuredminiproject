package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * BasePage is a base class that provides common functionality for all Page Object Model (POM) classes.
 * It initialises the WebDriver instance and provides methods to get the WebDriver instance and the URL for the application.
 * 
 * The WebDriver instance used by this class is determined by the config.properties file.
 * The URL for the application is also obtained from the config.properties file.
 * 
 * This class contains methods to get the WebDriver instance and the URL.
 * The WebDriver instance and the URL are used by all POM classes that extend this class.
 *
 * @author LuthoMangwana
 *
 */
public class BasePage 
{
	public static WebDriver driver;
    private Properties prop;
    private FileInputStream data;
    private String url;

    /**
     * Constructor for BasePage.
     * Initialises the Properties instance and loads the config.properties file.
     * @throws IOException if an I/O error occurs when loading the config.properties file.
     */
    public BasePage() throws IOException {
        prop = new Properties();     
        data = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\resources\\config.properties");   
        prop.load(data); // Load properties file once here
    }	
	
   
    
    /**
     * This method initialises and returns a WebDriver instance based on the browser type specified in the config.properties file.
     * The WebDriver instance is maximised and implicitly waits for a specified duration before throwing a NoSuchElementException.
     *
     * @throws IOException if an I/O error occurs when getting the 'browser' property.
     * @return the initialised WebDriver instance.
     */
	public WebDriver getDriver() throws IOException
	{
		String browser = prop.getProperty("browser");
		
		if(browser.equals("chrome"))
		{
			driver = new ChromeDriver();
			
		}
		else if(browser.equals("firefox"))
		{
			driver = new FirefoxDriver();
			
		}
		else
		{
			driver = new EdgeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
		return driver;
		
	}
	
	public String getRandomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String getRandomBirthdate() {
        long minDay = LocalDate.of(1900, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        LocalDate randomBirthdate = LocalDate.ofEpochDay(randomDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return randomBirthdate.format(formatter);
    }
	
	public String getRandomPhoneNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(9);
		return "0"+generatedString;
	}
	
	public String getRandomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	
	
	public void windowSetUp()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
     * Returns the URL for the application.
     * The URL is determined by the 'url' property in the config.properties file.
     * @throws IOException if an I/O error occurs when getting the 'url' property.
     * @return the URL for the application.
     */
	
	public String getUrl() throws IOException
	{
		url = prop.getProperty("url");
		return url;
	}
	
	/**
	 * This method takes a screenshot of the current state of the web application.
	 * The screenshot is saved as a .png file in the target/screenshots directory.
	 * The filename of the screenshot is a timestamp, which ensures that each screenshot has a unique filename.
	 *
	 * @param driver the WebDriver instance used to take the screenshot.
	 * @throws IOException if an I/O error occurs when writing the screenshot to a file.
	 */
	public void takeSnapShot(String name) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") +"\\target\\screenshots\\"
				+ timestamp() + ".png");

		FileUtils.copyFile(srcFile, destFile);
	}

	/**
	 * This method returns a timestamp as a String. 
	 * The timestamp is in the format "yyyy-MM-dd HH-mm-ss", which is a common format for timestamps.
	 * This timestamp can be used to uniquely identify events, such as the taking of a screenshot.
	 *
	 * @return a timestamp as a String.
	 */
	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

}