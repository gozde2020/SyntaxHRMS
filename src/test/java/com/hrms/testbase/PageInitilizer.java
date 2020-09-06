package com.hrms.testbase;

import com.hrms.pages.AddEmployeePageElements;
import com.hrms.pages.DashboardPageElements;
import com.hrms.pages.LoginPageElements;

public class PageInitilizer extends BaseClass {
	public static LoginPageElements login;
	protected static DashboardPageElements dash;
	protected static AddEmployeePageElements addEmp;
	
	public static void initializePageObjects() {
		
	
	login =new LoginPageElements();
	dash =new DashboardPageElements();
	addEmp = new AddEmployeePageElements();
	
	
	}
	
}
