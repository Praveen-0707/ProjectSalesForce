package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;
import salesforce.utils.Logs;

public class SortingAccounts extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		testName = "Sort Accounts";
		testDescription = "Accounts should be sorted by Name in ascending order";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(groups= {"Accounts"})
	public void sortAccounts() throws InterruptedException
	{
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll().searchApp("Account").clickOnApp("Account");
		
		new AccountsPage().sortAccountsByName();
	}
}
