package salesforce.pages;

import salesforce.base.PreAndPost;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ServiceConsolePage extends PreAndPost {
	
	public ServiceConsolePage()
	{
		this.driver = getDriver();
		switchToDefaultContent();
	}
	
	public NewDashboardPage clickOnNewDashboard()
	{
		try {
			click(locateElement("xpath", "//div[text()='New Dashboard']"));
			reportStep("click on new Dashboard", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on new Dashboard", "Fail");
		}
		return new NewDashboardPage();
	}
	
	public ServiceConsolePage clickOnTab(String value)
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
	
	public ServiceConsolePage clickOnNewButton()
	{
		try {
			click(locateElement("xpath","//div[text()='New']"));
			reportStep("click on new Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on new Button", "Fail");
		}
		return new ServiceConsolePage();
	}
		
	public ServiceConsolePage setSubscriptionFrequencyAs(String value)
	{
		try {
			click(locateElement("xpath","//legend[text()='Frequency']/following::div//span[text()='"+value+"']"));
			reportStep("Set Subscription Frequency as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to set Subscription Frequency as: "+value, "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickOnRecentFiles()
	{
		try {
			click(locateElement("xpath","//a[@title='Recent']"));
			reportStep("click on Recent files", "Pass");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to click on Recent files", "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage selectTask(String value) throws InterruptedException
	{
		HomePage homepage = new HomePage();
		try
		{
			homepage.selectTaskFromNavigationControl(value);
			reportStep("select task: "+value, "Pass");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			reportStep("Unknown error occured: Method - selectTask recalled inside Catch block "+value, "Fail");
			homepage.selectTaskFromNavigationControl(value);
		}
		return this;
	}
	
	public ServiceConsolePage clickonLastModifiedFilesColumn() throws InterruptedException
	{
		try {
			WebElement col = locateElement("xpath","//span[@title='Last Modified Date']/parent::a");
			click(col);
			reportStep("click on Last Modified Column", "Pass");
			solidWait(2);
			String sort = getText(locateElement("xpath","//span[@title='Last Modified Date']/parent::a/following-sibling::span"));
			if (sort.contains("Sorted Ascending"))
			{
				click(col);
				solidWait(2);
			}
		} catch (Exception e) {
			reportStep("Unable to sort the records", "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage selectTime(String value)
	{
		try {
			WebElement dd_time = locateElement("xpath","//select[@id='time']");
			selectDropDownUsingVisibleText(dd_time, value);
			reportStep("Select Time: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select Time: "+value, "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickonSaveButton()
	{
		try {
			click(locateElement("xpath","//button[@title='Save']"));
			reportStep("Click on Save Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Save Button", "Fail");
		}
		return this;
	}
	
	public String getContactRequestNumber()
	{
		String ConReqNum = null;
		solidWait(3);
		try {
			ConReqNum = getText(locateElement("xpath","//span[text()='Contact Request Number']/parent::div/following-sibling::div//span[@class='uiOutputText']"));
			if (!ConReqNum.isEmpty() == true)
			{
				reportStep("Contact Request Number: "+ConReqNum, "Pass");
			}
		} catch (Exception e) {
			reportStep("Unable to get Contact Request Number", "Fail");
		}
		return ConReqNum;
	}
	
	public String selectLastModifiedFile()
	{
		String downloadedFileName = null;
		try {
			downloadedFileName = getText(locateElement("xpath","//table/tbody/tr[1]/th//div//span[@data-aura-class='uiOutputText']"));
			click(locateElement("xpath","//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']"));
			reportStep("Click on recent added record", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on recent added record", "Fail");
		}
		return downloadedFileName;
	}
	
	public ServiceConsolePage selectFileOptionAs(String value)
	{
		try {
			click(locateElement("xpath","//div[@role='button' and @title='"+value+"']/.."));
			reportStep("File option selected as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select file option as: "+value, "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickToOpenFile(String fileName, String fileOption)
	{
		try
		{
			List<WebElement> listofFiles = locateElements("xpath","//table/tbody/tr");
			for (int i=1; i<listofFiles.size(); i++)
			{
				String getFileName = getText(locateElement("xpath","//table/tbody/tr["+i+"]/th//a"));
				if (getFileName.contains(fileName))
				{
					click(locateElement("xpath","//table/tbody/tr["+i+"]/th//a/following::td[3]//a[@role='button']"));
					selectFileOptionAs(fileOption);
					reportStep("click on file "+fileName+ " with option as: "+fileOption, "Pass");
					solidWait(3);
					break;
				}
			}
		}
		catch(Exception e)
		{
			reportStep("Unable to click on file "+fileName+ " with option as: "+fileOption, "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage verifyNewDashboardCreation(String value)
	{
		try {
			String outputValue = getText(locateElement("xpath","//span[contains(text(),'subscription')]"));
			if (outputValue.contains("You started a dashboard subscription"))
			{
				reportStep("New Dashboard successfully created: "+value, "Pass");
			}
		} catch (Exception e) {
			reportStep("Unable to validate New Dashboard: "+value, "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage verifyUploadedFileDetails(String fileName, String fileExtension)
	{
		try
		{
			WebElement TitleElement = locateElement("xpath","//div[text()='File']/following-sibling::div");
			String TitleVerification = TitleElement.getAttribute("title");	
			String getFileExtension = getText(locateElement("xpath","//span[@title='File Extension']/following-sibling::div//span[@class='uiOutputText']"));
			if ((TitleVerification.contains(fileName)) && getFileExtension.equals(fileExtension))
			{
				reportStep("Uploaded file successfully validated: "+fileName, "Pass");
			}
		}
		catch(Exception e)
		{
			reportStep("Unable to validate the uploaded file: "+fileName, "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage verifyPublicLinkInputFieldIsDisabled()
	{
		try {
			click(locateElement("xpath","//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']"));
			WebElement clkLink = locateElement("xpath","//input[@name='publicLinkURL']");
			if (isDisabled(clkLink) == true)
			{
				click(locateElement("xpath","//button[@title='Close this window']"));
				reportStep("Public Link is disabled for inputs","Pass");
				solidWait(2);
			}
		} catch (Exception e) {
			reportStep("Unable to validate Public Link","Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickonPrivateDashboard()
	{
		try {
			click(locateElement("xpath","(//a[@title='Private Dashboards'])[1]"));
			reportStep("Clicked on Private Dashboard","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Private Dashboard","Fail");
		}
		return this;
	}
	
	public ServiceConsolePage inputShareWithUser(String userName)
	{
		try {
			WebElement ele = locateElement("xpath","//span[text()='Share With']/following::input[@role='combobox']");
			solidWait(1);
			clickByActions(ele);
			sendkeysUsingActions(ele, Keys.ARROW_DOWN);
			solidWait(2);
			click(locateElement("xpath","//span[text()='Share With']/following::input[@role='combobox']/following::div[@class='listContent']/ul//a//div[contains(@title,'"+userName+"')]"));
			reportStep("Enter Share with User value: "+ userName,"Pass");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to enter Share with User value: "+ userName,"Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickOnNewContact(String contact)
	{
		try {
			WebElement ele = locateElement("xpath","//input[@title='Search Contacts']");
			solidWait(1);
			clickByActions(ele);
			sendkeysUsingActions(ele, Keys.ARROW_DOWN);
			solidWait(2);
			click(locateElement("xpath","//input[@title='Search Contacts']/following-sibling::div//span[text()='"+contact+"']"));
			reportStep("Clicked on New Contact","Pass");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to click on New Contact","Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clearExistingRecordAndAddNewShareFileWith(String userName)
	{
		try {
			click(locateElement("xpath","(//span[@class='pillText']/following::a)[1]"));
			reportStep("Clicked on Share File With","Pass");
			inputShareWithUser(userName);
		} catch (Exception e) {
			reportStep("Unable to click on Share File With","Fail");
		}
		return this;
	}		
	
	public ServiceConsolePage verifyContactName(String contactName)
	{
		try {
			String contact = getText(locateElement("xpath","//span[@class='pillText']"));
			if (contact.contains(contactName))
			{
				reportStep("Contact Created successfully: "+contactName,"Pass");
			}
		} catch (Exception e) {
			reportStep("Unable to create Contact: "+contactName,"Fail");
		}
		return this;
	}
	
	public ServiceConsolePage inputShareAttachmentMessage(String value)
	{
		try {
			type(locateElement("xpath","//textArea[@class='textArea textarea']"), value);
			reportStep("Enter share attachment message: "+value,"Pass");
		} catch (Exception e) {
			reportStep("Unable to enter share attachment message: "+value,"Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickOnShareAttachment()
	{
		try {
			click(locateElement("xpath","//span[text()='Share']/parent::button"));
			reportStep("Clicked on Share Attachment","Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Share Attachment","Fail");
		}
		return this;
	}
	
	public ServiceConsolePage verifyErrorMessageInShareScreen()
	{
		try {
			String errtext = getText(locateElement("xpath","//ul[contains(@class,'has-error')]/li"));
			if (errtext.contains("Can't share file with the file owner"))
			{
				reportStep("Error message in share screen validated","Pass");
			}
		} catch (Exception e) {
			reportStep("Unable to validate Error message in share screen","Fail");
		}
		return this;
	}
	
	public ServiceConsolePage searchDashboardName(String value)
	{
		try {
			typeAndEnter(locateElement("xpath","//input[contains(@class,'search-text')]"), value);
			reportStep("Search Dashboard Name: "+value,"Pass");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to Search Dashboard Name: "+value,"Fail");
		}
		return this;
	}
	
	public ServiceConsolePage deleteSubscription(String dashboardName)
	{
		try {
			click(locateElement("xpath","(//a[@title='"+dashboardName+"'])[1]//following::td[6]//button"));
			click(locateElement("xpath","//span[text()='Delete']/.."));
			reportStep("Clicked on Delete subscription: "+dashboardName,"Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Delete subscription: "+dashboardName,"Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickOnUploadFiles()
	{
		try {
			click(locateElement("xpath","//a[@title='Upload Files']"));
			reportStep("Clicked on Upload Files: ","Pass");
			solidWait(3);
		} catch (Exception e) {
			reportStep("Unable to click on Upload Files: ","Fail");
		}
		return this;
	}
		
	public ServiceConsolePage verifyShareAttachment(String fileName, String userName)
	{
		try
		{
			String displayMsgValue = getText(locateElement("xpath","//span[contains(@class,'toastMessage')]"));
			String msgToValidate = "You shared "+ fileName + " with "+ userName;
			if (displayMsgValue.contains(msgToValidate))
			{
				reportStep("File successfully shared: "+fileName,"Pass");
			}
		}
		catch(Exception e)
		{
			reportStep("Unable to validate the shared attachment: "+fileName,"Fail");
		}
		return this;
	}
	
	public ServiceConsolePage verifyDeleteAttachment(String fileName)
	{
		try
		{
			String displayMsgValue = getText(locateElement("xpath","//span[contains(@class,'toastMessage')]"));
			String msgToValidate1 = "Content Document ";
			String msgToValidate2 = "was deleted";
			
			if (displayMsgValue.contains(msgToValidate1) && displayMsgValue.contains(fileName) && displayMsgValue.contains(msgToValidate2))
			{
				reportStep("File successfully deleted: "+fileName,"Pass");
			}
		}
		catch(Exception e)
		{
			reportStep("Unable to validate file deletion: "+fileName,"Fail");
		}
		return this;
	}
	
	public ServiceConsolePage verifyDeleteSubscription(String dashboardName)
	{
		boolean flag_sort = false, flag_deleteValidation = false;
		String sortMsg;
		try
		{
			String displayMsgValue = getText(locateElement("xpath","//span[contains(@class,'toastMessage')]"));
			if (displayMsgValue.contains("Dashboard was deleted"))
			{
				searchDashboardName(" ");
				flag_sort = true;
			}

//			Sorting the Table records in descending order to get new records
			if (flag_sort)
			{
				WebElement clkAccountsSort_1 = locateElement("xpath","//span[text()='Created On']/ancestor::a");
				click(clkAccountsSort_1);
				WebElement AccountsSort = locateElement("xpath","//span[text()='Created On']/following::span[@aria-live='assertive' and contains(text(),'Sorted')][1]");
				sortMsg = getText(AccountsSort);
				if (sortMsg.contains("Sorted Descending"))
				{
					reportStep("Accounts are sorted in order for validation","Pass");
					flag_deleteValidation = true;
				}
				else
				{
					click(clkAccountsSort_1);
					sortMsg = getText(AccountsSort);
					if (sortMsg.contains("Sorted Descending"))
					{
						flag_deleteValidation = true;
					}
					else
					{
						reportStep("Accounts are not sorted in order for validation","Fail");
					}	
				}
			}
			
//			Searching for Deleted Subscription - Output Validation
			if (flag_deleteValidation)
			{
				List<WebElement> rows = locateElements("xpath","//table[contains(@class,'table_resizable-cols')]/tbody/tr");
				int size = rows.size();
				for (int i = 1; i <= size; i++)
				{
					String subscriptionNames = getText(locateElement("xpath","//table[contains(@class,'table_resizable-cols')]/tbody/tr["+i+"]/th//a"));
					if (subscriptionNames.equals(dashboardName))
					{
						reportStep("Unable to Delete Subscription: "+dashboardName,"Fail");
						break;
					}
					else
					{
						if (i == size)
						{
							reportStep("Delete Subscription was successful"+dashboardName, "Pass");
						}
					}
					
				}
			}
		}
		catch(Exception e)
		{
			reportStep("Unknown error occured while validating Delete Subscription", "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickOnEditGoal()
	{
		try {
			click(locateElement("xpath","//button[@title='Edit Goal']"));
			reportStep("Clicked on Edit Goal", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Edit Goal", "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage setGoalPrice(String value)
	{
		try {
			type(locateElement("xpath","//span[@id='currencyCode']/following-sibling::input"), value);
			reportStep("Enter Goal Pice: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter Goal Pice: "+value, "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickonSaveGoalPrice()
	{
		try {
			click(locateElement("xpath","(//span[text()='Save']/ancestor::button)[last()]"));
			reportStep("Clicked on Save Goal Price", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Save Goal Price", "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage validateGoalPrice(String setStrikePrice)
	{
		
		try {
			String ClosePrice = getClosePrice();
			ClosePrice = ClosePrice.replaceAll("\\D", "");
			int ClosePriceIntValue = Integer.parseInt(ClosePrice);
			
			String OpenPrice = getOpenPrice();
			OpenPrice = OpenPrice.replaceAll("\\D", "");
			int OpenPriceIntValue = Integer.parseInt(OpenPrice);
			
			int chkPrice = Integer.parseInt(setStrikePrice);
			String strikePrice = String.valueOf(chkPrice);
			int Price = ClosePriceIntValue + OpenPriceIntValue;
			if (Price < chkPrice)
			{
				clickOnEditGoal()
				.setGoalPrice(strikePrice)
				.clickonSaveGoalPrice();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public String getClosePrice()
	{
		String ClosePrice = null;
		try {
			ClosePrice = getText(locateElement("xpath","//span[text()='Closed']/following-sibling::span[contains(text(),'$')]"));
			reportStep("Retrieve and return Close Price: "+ClosePrice, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Retrieve and return Close Price", "Fail");
		}
		return ClosePrice;
	}
	
	public String getOpenPrice()
	{
		String OpenPrice = null;
		try {
			OpenPrice = getText(locateElement("xpath","//span[contains(text(),'Open')]/following-sibling::span[contains(text(),'$')]"));
			reportStep("Retrieve and return Open Price: "+OpenPrice, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Retrieve and return Open Price", "Fail");
		}
		return OpenPrice;
	}
	
}
