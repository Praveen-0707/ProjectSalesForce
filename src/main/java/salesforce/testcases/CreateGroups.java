package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;
import salesforce.utils.Logs;

public class CreateGroups extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Campaign";
		excelSheetName = "Leads";
		testName = "Create Lead";
		testDescription = "Create New Lead for Campaign";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void createLeadforCampaign() {
		
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales();
		
		clickOnTab("Groups");
		
		new SalesPage()
		.clickOnNewButton()
		.inputGroupName("RajKamal")
		.selectAccessType("Public")
		.clickonSaveAndNextButton();
	}

}
