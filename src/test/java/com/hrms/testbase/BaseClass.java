package com.hrms.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;

public class BaseClass {
	public static WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setUp()  {

		ConfigsReader.readProperties(
				System.getProperty("user.dir" + "/src/test/resources/configs/configuration.properties"));

		switch (ConfigsReader.getPropValue("browser").toLowerCase()) {

		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir" + Constants.CHROMEDRIVER_PATH));
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir" + Constants.GECKODRIVER_PATH));
			driver = new FirefoxDriver();
			break;
			
		case "edge":
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir" + Constants.EDGEDRIVER_PATH));
			driver = new EdgeDriver();
			break;
		default:

			throw new RuntimeException("Browser is not supported");

		}//we use switch coz it works faster, switch can checks only equality,cleaner and easier to read when lots of choices present
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME,TimeUnit.SECONDS);
		driver.get(ConfigsReader.getPropValue("ApplicationUrl"));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		
		if(driver!=null) {
			driver.quit();
		}
	}
	
	
}