package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;
import salesforce.utils.Logs;

public class AttachAndDeleteDocumentFromCampaign extends PreAndPost {
	
	@BeforeTest
	public void setTestDetails() {
//		excelFileName = "Accounts";
//		excelSheetName = "CreateAccounts";
		testName = "Manage Attachments";
		testDescription = "Attach and Delete Document from Campaign";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void uploadNDeleteDocsFromCampaign() throws InterruptedException {

		String fileLocation = "C:\\Users\\pravi\\Desktop\\Bike_Insurance.pdf";
		
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales();
		clickOnTab("Campaigns");
		
		SalesPage sales = new SalesPage();
		
		sales.searchAndClickOnCampaign("BootCamp")
		.clickonUploadAttachment();
		uploadAttachment(fileLocation);
		
		sales.verifyUploadedAttachment("TexFile")
		.clickOnViewAllAttachments()
		.deleteAttachedFile("TexFile").verifyDeleteAttachment("TexFile");
			
	}
}
