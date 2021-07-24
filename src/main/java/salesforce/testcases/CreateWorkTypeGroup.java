package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;
import salesforce.utils.Logs;

public class CreateWorkTypeGroup extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "CreateWorkTypeGroup";
		testName = "Create New WorkTypeGroup Request";
		testDescription = "New WorkTypeGroup should be created";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "fetchData", groups= {"WorkTypeGroups"})
	public void createWorkTypeGroup(String wtgName, String wtgDescr) throws InterruptedException {

		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnApp("Work Type Groups");
		new WorkTypeGroupsPage()
		.clickOnNewWorkTypeGroup().inputWorkTypeGroupName(wtgName).inputWorkTypeGroupDescr(wtgDescr)
		.clickOnSaveButton().createNewWorkTypeGroupValidation(wtgName);
	}

}
