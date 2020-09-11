package com.hrms.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrms.testbase.BaseClass;
import com.hrms.testbase.PageInitilizer;

public class CommonMethods extends PageInitilizer{
	
	/*
	 * 
	 * methods that sends text to any 
	 * @param element
	 * @param text
	 * 
	 */
	public static void sendText(WebElement element,String text ) {
		
		
		element.clear();
		element.sendKeys(text);
	}

	
	/*
	 *  Method return Object of JavaScript Executor type
	 * 
	 * @return js object
	 * 
	 * 
	 * 
	 */
	
	public static JavascriptExecutor getJSExecutor() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
	}
	/*
	 * methods performs click using JavasCRIP EXECUTOR
	 * 
	 * @param pixel
	 * 
	 */
	
	public static void jsClick(WebElement element) {
		getJSExecutor().executeScript("arguments[0].click();", element);
	}
	
	/*
	 * methods scroll up  using JavasCRIP EXECUTOR
	 * 
	 * @param pixel
	 * 
	 */
	public static void scrollUp (int pixel) {
		getJSExecutor().executeScript("window.scrollBy(0,-"+pixel+")");
		
	}
	/*
	 * methods scroll down  using JavasCRIP EXECUTOR
	 * 
	 * @param pixel
	 * 
	 */
	
	public static void scrollDown (int pixel) {
		getJSExecutor().executeScript("window.scrollBy(0,"+pixel+")");
		
	}
	
	/*
	 * methods object of explicit wait 
	 * 
	 * @param pixel
	 * 
	 */
	
	public static WebDriverWait getWaitObject() {
		
		return new  WebDriverWait(driver,Constants.EXPLICIT_WAIT_TIME);
		
	}
	
	public static void waitForClickability (WebElement element) {
		getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public static void click(WebElement element) {
		waitForClickability(element);
		
		element.click();
	}
	
	public static void waitForVisibility (WebElement element) {
			getWaitObject().until(ExpectedConditions.visibilityOf(element));
		}
		
		

	/**
	 * Method that will take a screenshot and store with name in specified location with .png extension
	 * @param fileName
	 */
	public static void takeScreenshot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(Constants.SCREENSHOT_FILEPATH + fileName +getTimeStamp()+ ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getTimeStamp() {
		
		Date date = new Date();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		return sdf.format(date);
		
	}
}

