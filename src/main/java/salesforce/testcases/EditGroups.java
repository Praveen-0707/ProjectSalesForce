package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.utils.Logs;

public class EditGroups extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Campaign";
		excelSheetName = "Leads";
		testName = "Edit Group";
		testDescription = "Edit the group description";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void editGroup() {
		
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales()
		.clickOnTab("Groups")
		.searchFor("RajPraveen")
		.clickOnEditGroup("RajPraveen")
		.inputGroupDescr("Changes made")
		.selectFontName("Verdana")
		.selectFontSize("18")
		.selectAccessType("Private")
		.clickonSaveButton()
		.verifyGroupNameAndType("RajPraveen", "Private");
		
	}

}
