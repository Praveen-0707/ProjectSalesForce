package salesforce.testcases;

import salesforce.base.PreAndPost;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;
import salesforce.utils.Logs;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateAccount extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Accounts";
		excelSheetName = "CreateAccounts";
		testName = "Create Account";
		testDescription = "Creates new account in salesforce application";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "fetchData", groups= {"Accounts"})
	public void createNewAccount(String accName) throws InterruptedException
	{
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Account").clickOnApp("Account");
		
		new AccountsPage()
		.clickOnCreateNewAccount().inputAccountName(accName)
//		.selectOwnership("Public")
		.clickOnSaveButton().createAccountValidation(accName);
	}
}
