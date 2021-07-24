package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;

public class SalesforceLogin extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		testName = "Log into Salesforce Application";
		testDescription = "Login should be successful";
		testAuthor = "Praveen Raj A";
		testCategory = "Smoke";
		excelFileName = "Login";
		excelSheetName = "Login";
	}
	
	@Test(dataProvider = "fetchData")
	public void login(String userName, String pass)
	{
		new LoginPage(prop)
		.enterUsername()
		.enterPassword()
		.clickLogin();
	}
}
