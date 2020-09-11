package com.hrms.testcases;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.testng.Assert;
import org.testng.annotations.DataProvider;


import com.hrms.pages.DashboardPageElements;
import com.hrms.pages.LoginPageElements;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelUtility;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddEmployeeTest extends CommonMethods{

	@Test(dataProvider="newEmployeeDetails", groups="regression")
	
	public void addEmployee(String firstname, String lastname, String username, String password) {
	
	sendText(login.userNametextBox,ConfigsReader.getPropValue("username"));
	sendText(login.passwordTextBox,ConfigsReader.getPropValue("password"));
	click(login.btnLogin);

	jsClick(dash.pimLinkBtn);
	jsClick(dash.addEmpButton);
	
	
	sendText(addEmp.firstNameField,firstname);
	sendText(addEmp.lastNameField,lastname);
	click(addEmp.createLoginDetailsCheckbox);
	
	sendText(addEmp.userName,username);
	sendText(addEmp.userPassword,password);
	sendText(addEmp.confirmPassword,password);
	click(addEmp.saveButton);
	
	
	//waitForVisibility(viewPersonalD.profPicText);
	Assert.assertEquals(viewPersonalD.profPicText.getText(), firstname + " "+lastname);
	
	}
	
	@Test(groups="regression")
	public void addMultipleEmployees() throws InterruptedException {
		sendText(login.userNametextBox, ConfigsReader.getPropValue("username"));
		sendText(login.passwordTextBox, ConfigsReader.getPropValue("password"));
		click(login.btnLogin);

		jsClick(dash.pimLinkBtn);

		List<Map<String, String>> employeeList = ExcelUtility.excelToListMap(Constants.TESTDATA_FILEPATH,
				"AddEmployee");
		SoftAssert soft = new SoftAssert();
		for (Map<String, String> map : employeeList) {
			jsClick(dash.addEmpButton);
			Thread.sleep(2000);
			String firstName = map.get("FirstName");
			String middleName = map.get("MiddleName");
			String lastName = map.get("LastName");

			sendText(addEmp.firstNameField, firstName);
			sendText(addEmp.middleName, middleName);
			sendText(addEmp.lastNameField, lastName);
			jsClick(addEmp.saveButton);
			Assert.assertEquals(viewPersonalD.profPicText.getText(), firstName + " " + middleName + " " + lastName);
		}
		soft.assertAll();
	}
	@DataProvider
	public static Object[][] newEmployeeDetails(){
		Object [][] empdata = {
				{"Omar", "Akan","Ouwwoooo","Akanomer!!!1"},
				{"Hakann", "Duran","Heekarr","Duranhakan@@@@2"},
				{"Fatihh","Ozseker","Fvvtixxxxx","Ozsekerfatih####3"},
//				{"Aslimm","Yolackan","AslimYola","Yolackanaslim$$$44"},
//				{"Keremm","Yilmaz","KeryilY","Yilmazkerem%%%%5"}
	};
	
	return empdata;

}
}
