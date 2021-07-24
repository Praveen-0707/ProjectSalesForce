package salesforce.testcases;

import salesforce.base.PreAndPost;
import salesforce.pages.LoginPage;
import salesforce.pages.ServiceConsolePage;
import salesforce.utils.Logs;
import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_AssessmentCase extends PreAndPost {
	
	public String fileName;
	
	@BeforeTest
	public void setTestDetails() {
//		excelFileName = "Accounts";
//		excelSheetName = "CreateAccounts";
		testName = "Assessment Testcase";
		testDescription = "To perform view, modify, file upload & delete and verify";
		testAuthor = "Praveen Raj A";
		testCategory = "Smoke";
//		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test
	public void assessmentTestcase() throws InterruptedException, IOException
	{		
//		file to copy from src to dest folder
		String srcFolder = "C:\\Users\\HP\\Downloads\\";
		String fileExtension = "pdf";
		String destFolder = "E:\\Documents\\SeleniumBootcamp\\";
		
		// file to upload
		String fileLocation = "C:\\Users\\HP\\Downloads\\sampleuploadfile.txt";
		String uploadFile = "sampleuploadfile";
		String uploadFileExtension = "txt";
		
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Service Console").clickOnApp("Service Console");
		new ServiceConsolePage()
		.selectTask("Files").clickOnRecentFiles().clickonLastModifiedFilesColumn();
		
		ServiceConsolePage servObj = new ServiceConsolePage();
		fileName = servObj.selectLastModifiedFile();
		
		servObj.selectFileOptionAs("Public Link")
		.verifyPublicLinkInputFieldIsDisabled();
				
		servObj.selectLastModifiedFile();
		servObj.selectFileOptionAs("Download");
		
		copyFilefromSourceToDestinationFolder(srcFolder,fileName,fileExtension,destFolder);
		
		servObj.selectLastModifiedFile();
		servObj.selectFileOptionAs("Share")
		.inputShareWithUser("Derrick").verifyErrorMessageInShareScreen()
		.clearExistingRecordAndAddNewShareFileWith("Integration User")
		.inputShareAttachmentMessage("Bootcamp_Nupela_Praveen").clickOnShareAttachment()
		.verifyShareAttachment(fileName, "Integration User").clickOnUploadFiles();
		
		uploadAttachment(fileLocation);
		solidWait(3);
		
		servObj.clickToOpenFile(uploadFile,"View File Details")
		.verifyUploadedFileDetails(uploadFile, uploadFileExtension);
		
		closeAnOpenedTab(uploadFile);
		clickOnTab("Files");
		servObj.clickToOpenFile(uploadFile, "Delete");
		deletePopUpConfirmation();
		servObj.verifyDeleteAttachment(uploadFile);
		
	}
}
