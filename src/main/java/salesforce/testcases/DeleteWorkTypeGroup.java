package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;
import salesforce.utils.Logs;
import salesforce.base.PreAndPost;

public class DeleteWorkTypeGroup extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "DeleteWorkTypeGroup";
		testName = "Delete WorkTypeGroup";
		testDescription = "WorkTypeGroup should be deleted";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "fetchData", groups= {"WorkTypeGroups"})
	public void deleteWorkTypeGroup(String workTypeGroup_Name) throws InterruptedException {

		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnApp("Work Type Groups");
		new WorkTypeGroupsPage()
		.searchWorkTypeGroup(workTypeGroup_Name);
		
		WorkTypeGroupsPage works = new WorkTypeGroupsPage();
		works.deleteWorkTypeGroup(workTypeGroup_Name);
		
		deletePopUpConfirmation();
		
		works.searchWorkTypeGroup("");

		solidWait(3);
		works.deleteWorkTypeGroupValidation(workTypeGroup_Name);
	}
}
