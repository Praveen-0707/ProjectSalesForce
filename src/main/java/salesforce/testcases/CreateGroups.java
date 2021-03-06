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
		testName = "Create Group";
		testDescription = "Create New Group";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void createGroup() {
		
		SalesPage sales = new SalesPage();
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales()
		.clickOnTab("Groups")
		.clickOnNewButton()
		.inputGroupName("RajPraveen")
		.selectAccessType("Public")
		.clickonSaveAndNextButton()
		.clickonUploadImage();
		uploadAttachment("Praveenraj.jpg");
		sales.clickonNextButton()
		.clickonDone()
		.verifyGroupName("RajKamal");
		
	}

}
