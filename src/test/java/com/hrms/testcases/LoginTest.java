package com.hrms.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hrms.pages.DashboardPageElements;
import com.hrms.pages.LoginPageElements;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

public class LoginTest extends CommonMethods {
	
	@Test(groups = "smoke")
	public void validLogin() {
		
		LoginPageElements login = new LoginPageElements();
		
		
		sendText(login.userNametextBox,ConfigsReader.getPropValue("username"));
		sendText(login.passwordTextBox,ConfigsReader.getPropValue("password"));
		click(login.btnLogin);
	
		
		DashboardPageElements dashboard = new DashboardPageElements();
		AssertJUnit.assertTrue(dashboard.welcomeMsg.isDisplayed());
		
	}
	@Test(groups="regression",dataProvider="invalidCredentials")
	public void invalidLogin(String username, String password, String errorMessage) {
		
		LoginPageElements login = new LoginPageElements();
		sendText(login.userNametextBox,username);
		sendText(login.passwordTextBox,password);
		click(login.btnLogin);
		AssertJUnit.assertEquals(login.spanMessage.getText(), errorMessage);
		
		
		
	}
	
	@DataProvider
	public String [][] invalidCredentials(){
		String [][]data = {
				{"","Hum@nhrm123","Username cannot be empty"},
				{"Admin","","Password cannot be empty"},
				{"Admin","Hum@nhrm12","Invalid credentials"}};
			return data;	
				
		
		
	}
	
	

}
