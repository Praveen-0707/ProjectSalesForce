package salesforce.pages;

import salesforce.base.PreAndPost;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class SalesPage extends PreAndPost {
	
	public SalesPage()
	{
		this.driver = getDriver();
		switchToDefaultContent();
	}
	
	public SalesPage clickOnNewButton()
	{
		try {
			click(locateElement("xpath","//a[@title='New' and @role='button']"));
			reportStep("Clicked on New Button", "Pass");
			solidWait(3);
		} catch (Exception e) {
			reportStep("Unable to click on New Button", "Fail");
		}
		return this;
	}
	
	public SalesPage clickOnTab(String value)
	{
		try {
			clickByJS(locateElement("xpath","//a[@title='"+value+"']"));
			reportStep("Clicked on Tab: "+value, "Pass");
			solidWait(3);
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Unable to click on Tab: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage searchFor(String value)
	{
		try {
			WebElement searchCampaign = locateElement("xpath","//input[contains(@placeholder,'Search this list')]");
			typeAndEnter(searchCampaign, value);
			reportStep("Search for Campaign: "+value, "Pass");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to search for Campaign: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage searchAndClickOnCampaign(String value)
	{
		try {
			searchFor(value);
			click(locateElement("xpath","(//a[text()='" + value + "'])[1]"));
			reportStep("Search for Campaign: "+value +" and clicked on it", "Pass");
		} catch (Exception e) {
			reportStep("Unable to search and click on Campaign: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage clickAndViewAllCampaignMembers()
	{
		try {
			click(locateElement("xpath","//span[@title='Campaign Members']/../../../../../following-sibling::div//span[text()='View All']"));
			reportStep("Click on view all campaign members", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on view all campaign members", "Fail");
		}
		return this;
	}
	
	public SalesPage verifyUploadedAttachment(String fileName)
	{
		try
		{
			String anchorTag = "a";
			String tagName = locateElement("xpath","//a[@title='"+fileName+"']").getTagName();
			String linkText = locateElement("xpath","//a[@title='"+fileName+"']").getText();
			if((tagName.equals(anchorTag))&&(linkText.contains(fileName)))
			{
				reportStep("Upload attachment is successful: "+fileName, "Pass");
				solidWait(2);
				click(locateElement("xpath","//a[@title='"+fileName+"']"));
			}
			else
			{
				reportStep("Unable to Upload attachment"+fileName, "Fail");
			}
			solidWait(3);
					
//			Close File Preview
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		}
		catch(Exception e)
		{
			reportStep("Unable to verify uploaded attachment: "+fileName, "Fail");
		}
		return this;
	}
	
	public SalesPage verifyDeleteGroup(String grpName)
	{
		try
		{
			String displayMsgValue = getText(locateElement("xpath","//span[contains(@class,'toastMessage')]"));
			if (displayMsgValue.contains(grpName) && displayMsgValue.contains("was deleted"))
			{
				reportStep("Delete group is successful: "+grpName, "Pass");
			}
			else
			{
				reportStep("Unable to delete group: "+grpName, "Fail");
			}
		}
		catch(Exception e)
		{
			reportStep("Unknown exception occurred while trying to verify delete group: "+grpName, "Fail");
		}
		return this;
	}
	
	public SalesPage clickOnViewAllAttachments()
	{
		try {
			WebElement ele = locateElement("xpath","//span[@class='view-all-label']/span[text()='Attachments']");
			clickByJS(ele);
			reportStep("Clicked on ViewAll Attachment", "Pass");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to click on ViewAll Attachment", "Fail");
		}
		return this;
	}
	
	public SalesPage deleteAttachedFile(String fileName)
	{
		try
		{
			List<WebElement> files = locateElements("xpath","//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr");
			int cnt = files.size();
			for (int i = 1; i <= cnt; i++)
			{
				WebElement listofFiles = locateElement("xpath","//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr["+i+"]/th//a");
				String UploadedFile = listofFiles.getText();
				if (UploadedFile.contains(fileName))
				{
					WebElement ele = locateElement("xpath","//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr["+i+"]/td[5]//a[@role='button']");
					clickByJS(ele);
					solidWait(1);
					click(locateElement("xpath","//div[@role='button' and @title='Delete']/.."));
					reportStep("Clicked on Delete option on file: "+fileName, "Pass");
					deletePopUpConfirmation();
					solidWait(2);
					break;
				}
			}
		}
		catch(Exception e)
		{
			reportStep("Unable to click on Delete option on file: "+fileName, "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage verifyDeleteAttachment(String fileName)
	{
		try
		{
			List<WebElement> tb_rows = locateElements("xpath","//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr");
			int size = tb_rows.size();
			for (int j = 1; j <= size; j++)
			{
				WebElement searchDelFile = locateElement("xpath","//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr["+j+"]/th//a");
				String delFile = searchDelFile.getText();
			
				if (delFile.equals(fileName))
				{
					reportStep("Unable to Delete file: "+fileName, "Fail");
					break;
				}
				else
				{
					if (j == size)
					{
						reportStep("File deleted is successful: "+fileName, "Pass");
					}
				}	
			}	
		}
		catch(Exception e)
		{
			reportStep("Unable to Delete file: "+fileName, "Fail");
		}
		return this;
	}
	
	public SalesPage clickOnViewAllKeyDeals()
	{
		try {
			click(locateElement("xpath","//a/span[text()='View All Key Deals']"));
			solidWait(2);
			reportStep("Clicked on View All Key Deals Link: ", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on View All Key Deals Link: ", "Fail");
		}
		return this;
	}
	
	public SalesPage clickOnOpportunityMenu()
	{
		try {
			click(locateElement("xpath","//a[@title='Opportunities']/following::div"));
			solidWait(3);
			reportStep("Clicked on Opportunity Menu", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Opportunity Menu", "Fail");
		}
		return this;
	}
	 
	public SalesPage clickOnCreateNewOpportunity()
	{
		try {
			click(locateElement("xpath","//div[text()='New']"));
			solidWait(2);
			reportStep("Clicked on Create Opportunity Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Create Opportunity Button", "Fail");
		}
		return this;
	}
	
	public SalesPage selectAllOpportunities()
	{
		try {
			clickByJS(locateElement("xpath","//span[text()='All Opportunities']/ancestor::a[@role='menuitem']"));
			solidWait(2);
			reportStep("Clicked on select all Opportunities", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on select all Opportunities", "Fail");
		}
		return this;
	}
	
	public SalesPage searchLead(String value)
	{
		try {
			WebElement searchLead = locateElement("xpath","//input[contains(@placeholder,'Search this list')]");
			typeAndEnter(searchLead, value);
			reportStep("Search for Lead: "+value, "Pass");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to Search for Lead: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectLead(String value)
	{
		
		try {
			WebElement dd_lead = locateElement("xpath","//input[@title='Search Leads']");		
			solidWait(2);
			int length = value.length();
			if (length < 2)
			{
				clickByActions(locateElement("xpath","//lightning-icon[contains(@class,'inputLookupIcon')]"));
				solidWait(2);
				click(locateElement("xpath","//span[@title='New Lead']"));
				reportStep("Clicked on New Lead", "Pass");
				solidWait(2);
			}
			else
			{
				dd_lead.sendKeys(value);
				dd_lead.sendKeys(Keys.ENTER);
				reportStep("Search for Lead: "+value, "Pass");
				solidWait(2);
			}
		} catch (Exception e) {
			reportStep("Unable to search for Lead: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectFontName(String value)
	{
		try {
			click(locateElement("xpath","//span[text()='Information']/following::input[@name='font']"));
			solidWait(1);
			WebElement ele = locateElement("xpath","//span[text()='Information']/following::input[@name='font']/following::lightning-base-combobox-item//span[@title='"+value+"']");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select value from Information dropdown: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select value from Information dropdown: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectFontSize(String size)
	{
		try {
			click(locateElement("xpath","//span[text()='Information']/following::input[@name='font-size']"));
			solidWait(1);
			WebElement ele = locateElement("xpath","//span[text()='Information']/following::input[@name='font-size']/following::lightning-base-combobox-item//span[@title='"+size+"']");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select value from Information dropdown: "+size, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select value from Information dropdown: "+size, "Fail");
		}
		return this;
	}
	
	public SalesPage selectSalutation(String value)
	{
		try {
			click(locateElement("xpath","(//span[text()='Salutation']/following::div/a[contains(text(),'None')])[1]"));
			solidWait(1);
			WebElement ele = locateElement("xpath","(//div[@class='select-options' and @role='menu']/ul//a[contains(@title,'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select value from Salutation dropdown: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select value from Salutation dropdown: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectAccessType(String value)
	{
		try {
			WebElement ele = locateElement("xpath","//span[text()='Access Type']/following::a[@class='select']");
			scrollToVisibleElement(ele);
			click(ele);
			solidWait(1);
			ele = locateElement("xpath","//span[text()='Access Type']/following::a[@title='"+value+"']");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select value from Salutation dropdown: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select value from Salutation dropdown: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectContractContactName(String value)
	{
		try {
			click(locateElement("xpath","//input[@title='Search Contacts']"));
			solidWait(1);
			WebElement ele = locateElement("xpath","(//input[@title='Search Contacts']/following-sibling::div//div[@class='listContent']/ul/li/a//div[contains(@title,'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select value from ContractContactName dropdown: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select value from ContractContactName dropdown: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputGroupName(String value)
	{
		try {
			type(locateElement("xpath","(//label//span[text()='Name']/following::input)[1]"), value);
			reportStep("Enter Group Name: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter Group Name: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputGroupDescr(String value)
	{
		try {
			type(locateElement("xpath","//span[text()='Description']/following::textarea"), value);
			reportStep("Enter Group Description: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter Group Description: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputFirstName(String value)
	{
		try {
			type(locateElement("xpath","//label/span[text()='First Name']/following::input[contains(@class,'firstName')]"), value);
			reportStep("Enter FirstName: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter FirstName: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputLastName(String value)
	{
		try {
			type(locateElement("xpath","//label/span[text()='Last Name']/following::input[contains(@class,'lastName')]"),value);
			reportStep("Enter Last Name: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter Last Name: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputSubject(String value)
	{
		try {
			type(locateElement("xpath","//span[text()='Subject']/parent::label/following-sibling::input"),value);
			reportStep("Enter Subject: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Subject: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputCaseDescription(String value)
	{
		try {
			type(locateElement("xpath","//span[text()='Description']/parent::label/following-sibling::textarea"),value);
			reportStep("Enter Case Description: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Case Description: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputCompanyName(String value)
	{
		try {
			type(locateElement("xpath","(//label/span[text()='Company']/following::input[contains(@class,'input')])[1]"),value);
			reportStep("Enter Company name: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Company: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputCloseDate(String value)
	{
		try {
			type(locateElement("xpath","//label[text()='Close Date']/following-sibling::div//input[@name ='CloseDate']"),value);
			reportStep("Enter Close date: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Close date: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectType(String value)
	{
		try {
			click(locateElement("xpath","//label[text()='Type']/following-sibling::div//input[@type ='text']"));
			solidWait(1);
			WebElement ele = locateElement("xpath","(//label[text()='Type']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select dropdown value from Type: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select dropdown value from Type: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectLeadSource(String value)
	{
		try {
			click(locateElement("xpath","//label[text()='Lead Source']/following-sibling::div//input[@type ='text']"));
			solidWait(1);
			WebElement ele = locateElement("xpath","(//label[text()='Lead Source']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select dropdown value from LeadSource: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select dropdown value from LeadSource: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectStage(String value)
	{
		try {
			click(locateElement("xpath","//label[text()='Stage']/following-sibling::div//input[@type ='text']"));
			solidWait(1);
			WebElement ele = locateElement("xpath","(//label[text()='Stage']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select dropdown value from Stage: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select dropdown value from Stage: "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage selectPrimaryCampaignSource(String value)
	{
		try {
			click(locateElement("xpath","//label[text()='Primary Campaign Source']/following-sibling::div//input[@type='text']"));
			solidWait(1);
			WebElement ele = locateElement("xpath","(//label[text()='Primary Campaign Source']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select dropdown value from Primary Campaign Source: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select dropdown value from Primary Campaign Source: "+value, "Fail");
		}
		return this;
	}
			
	public SalesPage inputOpportunityName(String value)
	{
		try {
			type(locateElement("xpath","//label[text()='Opportunity Name']/following-sibling::div//input[@name ='Name']"), value);
			reportStep("Enter Opportunity Name:  "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter Opportunity Name:  "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage inputAmount(String value)
	{
		try {
			type(locateElement("xpath","//label[text()='Amount']/following-sibling::div//input[@name ='Amount']"),value);
			reportStep("Enter Amount value:  "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter Amount value:  "+value, "Fail");
		}
		return this;
	}
	
	public SalesPage clickOnAddLead()
	{
		try {
			click(locateElement("xpath","//a[@title='Add Leads']"));
			solidWait(2);
			reportStep("Click on Add Lead","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Add Lead","Fail");
		}
		return this;
	}
	
	public SalesPage clickOnSubmitButton()
	{
		try {
			clickByJS(locateElement("xpath","//span[text()='Submit']"));
			reportStep("Click on Submit button","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Submit button","Fail");
		}
		return this;
	}
	
	public SalesPage clickonSaveButton()
	{
		try {
			click(locateElement("xpath","(//button/span[text()='Save'])[last()]"));
			solidWait(1);
			reportStep("Click on Save button","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Save button","Fail");
		}
		return this;
	}
	
	public SalesPage clickonSaveAndNextButton()
	{
		try {
			click(locateElement("xpath","//span[text()='Save & Next']/.."));
			solidWait(1);
			reportStep("Click on Save and Next button","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Save and Next button","Fail");
		}
		return this;
	}
	
	public SalesPage clickonUploadImage()
	{
		try {
			click(locateElement("xpath","(//span[text()='Next']/..)[1]"));
			solidWait(1);
			reportStep("Click on Next button","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Next button","Fail");
		}
		return this;
	}
	
	public SalesPage clickonSaveOpportunity()
	{
		try {
			click(locateElement("xpath","//button[@name='SaveEdit' and text()='Save']"));
			solidWait(1);
			reportStep("Click on Save Opportunity","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Save Opportunity","Fail");
		}
		return this;
	}
	
	public SalesPage clickonNextButton()
	{
		try {
			clickByJS(locateElement("xpath","//span[text()='Next']"));
			solidWait(1);
			reportStep("Click on Next button","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Next button","Fail");
		}
		return this;
	}
	
	
	public SalesPage clickonDone()
	{
		try {
			click(locateElement("xpath","//span[text()='Done']/.."));
			solidWait(1);
			reportStep("Click on Done button","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Done button","Fail");
		}
		return this;
	}
	
	
	public SalesPage verifyGroupName(String grpName)
	{
		try {
			String groupName = getText(locateElement("xpath","//div[contains(@class,'nameActionsContainer')]//span[@class='uiOutputText']"));
			if (groupName.contains(grpName))
			{
				reportStep("Group name verified: "+grpName,"Pass");
			}
			else
			{
				reportStep("Unable to verify group name: "+grpName,"Fail");
			}

		} catch (WebDriverException e) {
			reportStep("Unknown Exception occured while trying to verify Group Name: "+grpName,"Fail");
		}
		return this;
	}
	
	public SalesPage verifyGroupNameAndType(String grpName, String accessType)
	{
		try {
			click(locateElement("xpath","(//a[text()='"+grpName+"'])[1]"));
			solidWait(3);
			String groupAccessType = getText(locateElement("xpath","//div[contains(@class,'nameActionsContainer')]//span[text()='RajKamal']/following::span[@class='forceChatterOutputGroupCombinedType first']"));
			if (groupAccessType.contains(accessType))
			{
				reportStep("Group name: "+grpName +" with access type: "+accessType + " verified","Pass");
			}
			else
			{
				reportStep("Unable to verify group name and access type: "+grpName + ", "+accessType,"Fail");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown Exception occured while trying to verify Group Name: "+grpName + ", "+accessType,"Fail");
		}
		return this;
	}
	
	public SalesPage deleteGroupByName(String grpName)
	{
		try {
			click(locateElement("xpath","(//a[text()='"+grpName+"'])[1]"));
			solidWait(3);
			click(locateElement("xpath","(//a[@title='Show 6 more actions' and @role='button'])[last()]"));
			solidWait(1);
			click(locateElement("xpath","//a[@title='Delete Group']"));
			reportStep("Group: "+grpName +" deleted successfully","Pass");
		} catch (WebDriverException e) {
			reportStep("Unable to delete Group: "+grpName +"","Fail");
		}
		return this;
	}
	
	public SalesPage clickonUploadAttachment()
	{
		try {
			click(locateElement("xpath","//div[@title='Upload Files']"));
			reportStep("Click on Upload Attachment Link","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Upload Attachment Link","Fail");
		}
		return this;
	}
	
	public SalesPage ClickOnCampaignName(String campName)
	{
		try {
			click(locateElement("xpath","(//a[contains(@title,'" + campName + "')])[1]"));
			reportStep("Click on Campaign Name: "+campName,"Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Campaign Name: "+campName,"Fail");
		}
		return this;
	}
	
	public SalesPage clickOnDeleteLead(String fName, String lName)
	{
		String lead = fName + " " + lName;
		try {
			click(locateElement("xpath","(//a[text()='" +lead+ "']/following::td//a[@role='button'])[1]"));
			click(locateElement("xpath","//div[@role='button' and @title='Delete']/.."));
			reportStep("Click on delete lead option for Lead: "+lead,"Pass");
		} catch (Exception e) {
			reportStep("Unable to click on delete lead option for Lead: "+lead,"Fail");
		}
		return this;
	}
	
	public SalesPage clickOnEditGroup(String groupName)
	{
		try {
			click(locateElement("xpath","(//a[text()='" +groupName+ "']/following::td//a[@role='button'])[1]"));
			click(locateElement("xpath","//div[@role='button' and @title='Edit Group']/.."));
			reportStep("Click on edit option for group: "+groupName,"Pass");
		} catch (Exception e) {
			reportStep("Unable to click on edit option for group: "+groupName,"Fail");
		}
		return this;
	}
	
	public SalesPage errValidationOnCaseCreation(String errMsg)
	{
		try
		{
			String errMsgValue = getText(locateElement("xpath","//ul[@class='errorsList']/li"));
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(errMsg, errMsgValue);
			softAssert.assertAll();
			reportStep("Error message displayed: "+errMsg, "Pass");
		}
		catch (Exception ex)
		{
			reportStep("Error message not displayed: "+errMsg, "Fail");
		}
		return this;
	}
	
	public SalesPage createOpportunityValidation(String oppName)
	{
		try
		{
			String outputValue = getText(locateElement("xpath","//span[contains(text(),'Opportunity')]//a"));
			if (outputValue.contains(oppName))
			{
				WebElement ele = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//div[contains(text(),'Opportunity')]/following-sibling::slot/slot[@slot='primaryField']"));
				String val = ele.getText();
				if (val.contains(oppName))
				reportStep("Opportunity created successfully: "+oppName, "Pass");
			}
			else
			{
				reportStep("Unable to create Opportunity: "+oppName, "Fail");
			}
		}
		catch (Exception ex)
		{
			reportStep("Unknown Exception Occured While validating New Opportunity Title", "FAIL");
		}
		return this;
	}
	
	public SalesPage createLeadValidation(String campName, String fName, String lName)
	{
		String Flag_Validation = null;
		String leadName = fName + " " + lName;
		try
		{
			String outputValue = getText(locateElement("xpath","//div[contains(@class,'toastTitle')]"));
			
			if (outputValue.contains(campName))
			{
				System.out.println(outputValue);
				reportStep("New Lead created successfully: "+leadName, "Pass");
				Flag_Validation = "True";
			}
			else
			{
				reportStep("Unable to create New Lead: "+leadName, "Fail");
			}
			
			if (Flag_Validation == "True")
			{
				clickOnTab("Leads");
				searchLead(leadName);
				String val = getText(locateElement("xpath","(//a[contains(@class,'forceOutputLookup')])[1]"));
				if (val.contains(leadName))
				{
					reportStep("New Lead created is displayed under Leads Tab: "+leadName, "Pass");
				}
			}
		}
		catch (Exception ex)
		{
			reportStep("Unable to create New Lead and validate: "+leadName, "Fail");
		}
		return this;
	}
	
	public SalesPage deleteLeadValidation(String fName, String lName)
	{
		String lead = fName + " " + lName;
		try
		{
			List<WebElement> rows = locateElements("xpath","//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr");
			int size = rows.size();
			for (int i = 1; i <= size; i++)
			{
				String leadNames = getText(locateElement("xpath","//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr[" + i + "]//td[4]//a"));
			
				if (leadNames.equals(lead))
				{
					reportStep("Unable to Delete Lead or Duplicate entry exist: "+lead, "Fail");
					break;
				}
				else
				{
					if (i == size)
					{
						reportStep("Delete Lead was successful: "+lead, "Pass");
					}
				}
			}
		}
		catch (Exception ex)
		{
			reportStep("Unable to perform Delete Lead validation: "+lead, "Fail");
		}
		return this;
	}
	
}
