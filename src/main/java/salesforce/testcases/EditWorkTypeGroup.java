package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;
import salesforce.utils.Logs;
import salesforce.base.PreAndPost;

public class EditWorkTypeGroup extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "EditWorkTypeGroup";
		testName = "Edit WorkTypeGroup";
		testDescription = "WorkTypeGroup details should be updated";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "fetchData", groups= {"WorkTypeGroups"})
	public void editWorkTypeGroup(String workTypeGroup_Name, String descr) throws InterruptedException {

		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnApp("Work Type Groups");
		new WorkTypeGroupsPage()
		.searchWorkTypeGroup(workTypeGroup_Name);

		WorkTypeGroupsPage WTG = new WorkTypeGroupsPage();
		WTG.editWorkTypeGroup(workTypeGroup_Name)
		.inputWorkTypeGroupDescr(descr).selectGroupType("Capacity").clickOnSaveButton()
		.searchAndClickOnWorkTypeGroup(workTypeGroup_Name).editWorkTypeGroupValidation(descr);
	}

}
