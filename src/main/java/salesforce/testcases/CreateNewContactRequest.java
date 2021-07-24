package salesforce.testcases;

import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;
import salesforce.pages.ServiceConsolePage;
import salesforce.utils.Logs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateNewContactRequest extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
//		excelFileName = "Accounts";
//		excelSheetName = "CreateAccounts";
		testName = "Create New Contact Request";
		testDescription = "New contact request should be created";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void createNewContactRequest()
	{
		String contactFirstname = "Sandy";
		String contactLastname = "Reel";
		String contactname = contactFirstname + contactLastname;
		
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("contact request").clickOnApp("contact request");
		new ServiceConsolePage()
		.clickOnNewButton().clickOnNewContact("New Contact");
		new SalesPage()
		.selectSalutation("Mr.").inputFirstName(contactFirstname)
		.inputLastName(contactLastname).clickonSaveButton();
		new ServiceConsolePage()
		.verifyContactName(contactname).clickonSaveButton()
		.getContactRequestNumber();
	}
}
