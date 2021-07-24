package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;
import salesforce.utils.Logs;

public class CreateNewCaseWithoutMandatoryField extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Testdata";
		excelSheetName = "NewCase";
		testName = "Create New Case";
		testDescription = "Create New Case Without Mandatory Fields";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "fetchData")
	public void createNewCaseWithoutMandatoryFields(String contactName, String subject, String description, String errMsg) throws InterruptedException {

		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales();
		
		clickOnTab("Cases");
		new SalesPage().clickOnNewCase()
		.selectContractContactName(contactName).inputSubject(subject).inputCaseDescription(description)
		.clickonSaveButton().errValidationOnCaseCreation(errMsg);

	}

}
