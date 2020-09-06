package com.hrms.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrms.testbase.BaseClass;

public class CommonMethods extends BaseClass{
	
	/*
	 * 
	 * methods that sends text to any 
	 * 
	 * 
	 */
	public static void sendText(WebElement element,String text ) {
		
		element.clear();
		element.sendKeys(text);
	}

	
	/*
	 * 
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
		getJSExecutor().executeScript("argument[0].click();", element);
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
}
