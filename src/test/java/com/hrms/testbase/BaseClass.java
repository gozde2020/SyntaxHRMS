package com.hrms.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.hrms.utils.ConfigsReader;

public class BaseClass {
	static WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setUp()  {

		ConfigsReader.readProperties(
				System.getProperty("user.dir" + "/src/test/resources/configs/configuration.properties"));

		switch (ConfigsReader.getPropValue("browser").toLowerCase()) {

		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir" + "/src/test/resources/drivers/chromedriver"));
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir" + "/src/test/resources/drivers/geckodriver"));
			driver = new ChromeDriver();
			break;
			
		case "edge":
		default:

			throw new RuntimeException("Browser is not supported");

		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		driver.get(ConfigsReader.getPropValue("ApplicationUrl"));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		
		if(driver!=null) {
			driver.quit();
		}
	}
	
	
}