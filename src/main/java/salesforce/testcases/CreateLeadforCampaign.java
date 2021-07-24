package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;
import salesforce.utils.Logs;

public class CreateLeadforCampaign extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Campaign";
		excelSheetName = "Leads";
		testName = "Create Lead";
		testDescription = "Create New Lead for Campaign";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "fetchData")
	public void createLeadforCampaign(String campName, String fName, String lName, String compName) throws InterruptedException {

		String createLead = "";	// creating New Lead
		
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales();
		
		clickOnTab("Campaigns");
		new SalesPage().searchCampaign(campName).ClickOnCampaignName(campName)
		.clickOnAddLead().selectLead(createLead)
		.selectSalutation("Mr").inputFirstName(fName).inputLastName(lName).inputCompanyName(compName)
		.clickonSaveButton().clickonNextButton().clickOnSubmitButton()
		.createLeadValidation(campName, fName, lName);
	}

}
