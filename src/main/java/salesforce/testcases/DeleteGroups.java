package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;
import salesforce.utils.Logs;

public class DeleteGroups extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Campaign";
		excelSheetName = "Leads";
		testName = "Delete Group";
		testDescription = "delete the group";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void deleteGroup() {
		
		SalesPage sales = new SalesPage();
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales()
		.clickOnTab("Groups")
		.searchFor("RajPraveen")
		.deleteGroupByName("RajPraveen");
		deletePopUpConfirmation();
		sales.verifyDeleteGroup("RajPraveen");
		
	}

}
