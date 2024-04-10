package base.Parallel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverInstance 
{
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver()
	{
		if(driver.get()==null)
		{
			try {
				driver.set(createDriver());
				
			} catch (Exception e) {
				System.out.println("threadlocal variable: driver is in use and not null");
			}
		}
		
		return driver.get();
	}

	public static WebDriver createDriver() throws IOException {
		WebDriver driver = null;
		Properties prop = new Properties();     
		FileInputStream data = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\resources\\Parallel\\config.properties");   
        prop.load(data); // Load properties file once here
		
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
	
	public static void  cleanupDriver()
	{
		//this is similar to what we did before with driver.quit()
		//Now since driver is a threadlocal variable we need to say driver.get().quit()
		// where the get() method returns the value in the current thread's copy of the threadlocal variable driver
		// Therefore driver.get() == driver which equals new Chrome() or new EgdeDriver(), new FirefoxDriver() etc. 
		driver.get().quit();
		driver.remove();
	}
	
	
}
