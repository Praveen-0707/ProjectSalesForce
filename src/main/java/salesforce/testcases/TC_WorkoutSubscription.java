package salesforce.testcases;

import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.NewDashboardPage;
import salesforce.pages.ServiceConsolePage;
import salesforce.utils.Logs;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_WorkoutSubscription extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		testName = "Assessment Testcase";
		testDescription = "Create New Workout Subscribtion and perform delete and verify";
		testAuthor = "Praveen Raj A";
		testCategory = "Smoke";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void createNewWorkoutSubscription() throws InterruptedException
	{		
		String dashboardName = "Raj_Workout999";
		String descr = "Testin999";
		
		ServiceConsolePage SC = new ServiceConsolePage();

		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Service Console").clickOnApp("Service Console");
		new ServiceConsolePage()
		
		.selectTask("Home")
		.validateGoalPrice("10000")
		.selectTask("Dashboards").clickOnNewDashboard()
		.inputDashboardName(dashboardName).inputDashboardDescr(descr).clickOnCreateButton();
		
		NewDashboardPage framePage = new NewDashboardPage();
		framePage.clickonDoneButton()
		
		.verifyDashboardTitle(dashboardName).clickOnSubscribeButton()
		.setSubscriptionFrequencyAs("Daily")
		.selectTime("10:00 AM").clickonSaveButton()
		.verifyNewDashboardCreation(dashboardName).closeAnOpenedTab(dashboardName);
		clickOnTab("Dashboards");

		SC.clickonPrivateDashboard().searchDashboardName(dashboardName)
		.deleteSubscription(dashboardName);
	
		deletePopUpConfirmation();
		
		SC.verifyDeleteSubscription(dashboardName);

 }
}
