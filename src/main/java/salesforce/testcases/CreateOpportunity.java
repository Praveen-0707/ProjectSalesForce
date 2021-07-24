package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.utils.Logs;

public class CreateOpportunity extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
//		excelFileName = "Testdata";
//		excelSheetName = "WTG";
		testName = "Create New Opportunity";
		testDescription = "New Opportunity should be created";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void createOpportunity() throws InterruptedException
	{
		String oppName = "TMT Steel";
		
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales().clickOnViewAllKeyDeals()
		.clickOnOpportunityMenu().selectAllOpportunities()
		.clickOnCreateNewOpportunity()
		.inputOpportunityName(oppName).inputAmount("10000").inputCloseDate("6/10/2021")
		.selectType("New Customer").selectLeadSource("Partner Referral")
		.selectStage("Needs Analysis").selectPrimaryCampaignSource("BootCamp")
		.clickonSaveOpportunity().createOpportunityValidation(oppName);
	}
		
}


