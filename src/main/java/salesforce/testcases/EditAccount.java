package salesforce.testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;
import salesforce.utils.Logs;

public class EditAccount extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Accounts";
		excelSheetName = "EditAccounts";
		testName = "Edit Account";
		testDescription = "Edit account in salesforce application";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "fetchData", groups= {"Accounts"})
	public void editAccount(String accName, String phNum) throws InterruptedException
	{
		WebElement ele;
		
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll().searchApp("Account").clickOnApp("Account");
		
		AccountsPage accPg = new AccountsPage();
		accPg.searchAccount(accName).editAccount(accName);
		accPg.inputPhoneNumber(phNum).selectType("Technology Partner").selectIndustry("Healthcare");
		
		ele = driver.findElementByXPath("//label[text()='Billing Street']/following-sibling::div/textarea");
		scrollToVisibleElement(ele);
		
		accPg.selectCustomerPriority("Low").selectSLA("Silver")
		.selectUpsellOpportunity("No").selectActive("No").clickOnSaveButton().editAccountValidation(accName, phNum);
				
	}
}
