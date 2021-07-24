package salesforce.pages;

import salesforce.base.PreAndPost;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AccountsPage extends PreAndPost {
	
	public AccountsPage()
	{
		this.driver = getDriver();
		switchToDefaultContent();
	}
	
	public AccountsPage searchAccount(String value)
	{
		try {
			WebElement searchAcc = locateElement("name", "Account-search-input");
			typeAndEnter(searchAcc, value);
			solidWait(2);
			reportStep("Search Account: "+value, "Pass");
		} catch (Exception e) {
			reportStep("unable yo Search Account: "+value, "Fail");
		}
		return this;
	}
	
	public AccountsPage clickOnCreateNewAccount()
	{
		try {
			click(locateElement("xpath", "//div[text()='New']"));
			reportStep("Clicked on New Account button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to clicked on New Account button", "Fail");
		}
		return this;
	}
	
	public AccountsPage inputAccountName(String value)
	{
		try {
			type(locateElement("xpath", "//input[@name='Name']"), value);
			reportStep("Enter Account Name as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Account Name: "+value, "Fail");
		}
		return this;
	}
	
	public AccountsPage inputPhoneNumber(String phoneNumber)
	{
		try {
			type(locateElement("xpath", "//input[@name='Phone']"), phoneNumber);
			reportStep("Enter Phone Number as: "+phoneNumber, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Phone Number as: "+phoneNumber, "Fail");
		}
		return this;
	}
	
	public AccountsPage inputBillingAddress(String value)
	{
		try {
			type(locateElement("xpath", "//label[text()='Billing Street']/following-sibling::div/textarea"), value);
			reportStep("Enter Billing Address as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Billing Address: "+value, "Fail");
		}
		return this;
	}
	
	public AccountsPage inputShippingAddress(String value)
	{
		try {
			type(locateElement("xpath", "//label[text()='Shipping Street']/following-sibling::div/textarea"), value);
			reportStep("Enter Shipping Address as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Shipping Address: "+value, "Fail");
		}
		return this;
	}
	
	public AccountsPage inputDescription(String value)
	{
		try {
			type(locateElement("xpath", "//label[text()='Description']/following-sibling::div/textarea"), value);
			reportStep("Enter Description as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Enter Description: "+value, "Fail");
		}
		return this;
	}
	
	public AccountsPage selectType(String value)
	{
		try {
			click(locateElement("xpath", "//label[text()='Type']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			solidWait(1);
			WebElement ele = locateElement("xpath", "(//label[text()='Type']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Click on Type dropdown and select value as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Type dropdown and select value as: "+value, "Fail");
		}
		return this;
	}

	public AccountsPage selectOwnership(String value)
	{
		try {
			WebElement dd_ownership = locateElement("xpath", "//label[text()='Ownership']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']");
			click(dd_ownership);
			solidWait(3);
			WebElement ele = locateElement("xpath", "(//label[text()='Ownership']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Click on Ownership dropdown and select value as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Ownership dropdown and select value as: "+value, "Fail");
		}
		return this;
	}

	public AccountsPage selectIndustry(String value)
	{
		try {
			WebElement dd_industry = locateElement("xpath","//label[text()='Industry']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']");
			click(dd_industry);
			solidWait(1);
			WebElement ele = locateElement("xpath","(//label[text()='Industry']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
//			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Click on Industry dropdown and select value as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Industry dropdown and select value as: "+value, "Fail");
		}
		return this;
	}

	public AccountsPage selectUpsellOpportunity(String value)
	{
		try {
			WebElement dd_upsellOpp = locateElement("xpath","//label[text()='Upsell Opportunity']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']");
			click(dd_upsellOpp);
			solidWait(1);
			WebElement ele = locateElement("xpath","(//label[text()='Upsell Opportunity']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Click on UpsellOpportunity dropdown and select value as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on UpsellOpportunity dropdown and select value as: "+value, "Fail");
		}
		return this;
	}

	public AccountsPage selectActive(String value)
	{
		try {
			WebElement dd_active = locateElement("xpath","//label[text()='Active']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']");
			click(dd_active);
			solidWait(1);
			WebElement ele = locateElement("xpath","(//label[text()='Active']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Click on Active dropdown and select value as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Active dropdown and select value as: "+value, "Fail");
		}
		return this;
	}

	public AccountsPage selectCustomerPriority(String value)
	{
		try {
			WebElement dd_CustomerPriority = locateElement("xpath","//label[text()='Customer Priority']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']");
			webDriverWait4VisibilityOfEle(dd_CustomerPriority);
			scrollToVisibleElement(dd_CustomerPriority);
			click(dd_CustomerPriority);
			solidWait(2);
			WebElement ele = locateElement("xpath","(//label[text()='Customer Priority']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Click on CustomerPriority dropdown and select value as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on CustomerPriority dropdown and select value as: "+value, "Fail");
		}
		return this;
	}

	public AccountsPage selectSLA(String value)
	{
		try {
			WebElement dd_SLA = locateElement("xpath","//label[text()='SLA']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']");
			webDriverWait4VisibilityOfEle(dd_SLA);
			scrollToVisibleElement(dd_SLA);
			click(dd_SLA);
			solidWait(2);
			WebElement ele = locateElement("xpath","(//label[text()='SLA']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Click on SLA dropdown and select value as: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on SLA dropdown and select value as: "+value, "Fail");
		}
		return this;
	}
	
	public AccountsPage clickRefreshButton()
	{
		try {
			click(locateElement("name", "refreshButton"));
			reportStep("Clicked on refresh button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on refresh button", "Fail");
		}
		return this;
	}
	
	public AccountsPage clickOnSaveButton()
	{
		try {
			click(locateElement("xpath", "//button[@name='SaveEdit' and text()='Save']"));
			reportStep("Clicked on save button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on save button", "Fail");
		}
		return this;
	}
	
	public AccountsPage clickOnAccountNameHeader()
	{
		try {
			click(locateElement("xpath","//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/.."));
			reportStep("Clicked on Account Names Header for Sorting", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Account Names Header", "Fail");
		}
		return this;
	}
	
	public AccountsPage editAccount(String accName)
	{
		try {
			WebElement editAcc = locateElement("xpath","(//a[text()='"+accName+"'])[1]//following::td//a[@role='button']");
			click(editAcc);
			editAcc = locateElement("xpath","//div[@role='button' and @title='Edit']/..");
			click(editAcc);
			reportStep("Clicked Edit Option on Account: "+accName, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Click Edit Option on Account: "+accName, "Fail");
		}
		return this;
	}
	
	public AccountsPage deleteAccount(String accName)
	{
		try {
			WebElement delAcc = locateElement("xpath","(//a[text()='" + accName + "'])[1]//following::td//a[@role='button']");
			click(delAcc);
			delAcc = locateElement("xpath","//div[@role='button' and @title='Delete']/..");
			click(delAcc);
			reportStep("Clicked Delete Option on Account: "+accName, "Pass");
		} catch (Exception e) {
			reportStep("Unable to Click Delete Option on Account: "+accName, "Fail");
		}
		return this;
	}
	
	public AccountsPage createAccountValidation(String accName)
	{
		try
		{
			WebElement output = locateElement("xpath","//span[contains(text(),'Account')]//a");
			String outputValue = getText(output);
			
			if (outputValue.contains(accName))
			{
				reportStep("Account Created successfully: "+accName, "Pass");
			}
			else
			{
				reportStep("Unable to Create Account: "+accName, "Fail");
			}
		}
		catch (Exception ex)
		{
			reportStep("Unable to Create Account: "+accName, "Fail");
		}
		return this;
	}
	
	public AccountsPage editAccountValidation(String accName, String phNum)
	{
		String outputValueSplit = null;
		String outputphno = null;
		
		try
		{
			String outputValue = getText(locateElement("xpath","//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
			if (outputValue.contains(accName))
			{
				reportStep("Account updated: "+accName, "Pass");
				solidWait(3);
				String editedValue = getText(locateElement("xpath","(//a[text()='"+accName+"'])[1]//following::td[2]//span[contains(@class,'forceOutputPhone')]"));
				outputphno = editedValue;
				if (editedValue.contains("-"))
				{
					outputValueSplit = editedValue.split("\\-")[0];
					outputValueSplit = outputValueSplit.replace("(", "");
					outputValueSplit = outputValueSplit.replace(")", "");
					outputValueSplit = outputValueSplit.replace(" ", "");
					outputphno = outputValueSplit + editedValue.split("\\-")[1];
				}
				
				else if (phNum.equals(outputphno))
				{
					reportStep("Phone Number successfully validated for account: "+accName, "Pass");
				}
				else
				{
					reportStep("Unable to validate Phone Number for Account: "+accName, "Fail");
				}
			}
			else
			{
				reportStep("Unable to validate Phone Number for Account: "+accName, "Fail");
			}
		}
		catch (Exception ex)
		{
			reportStep("Unable to validate Phone Number for Account: "+accName, "Fail");
		}
		return this;
	}
	
	public AccountsPage deleteAccountValidation(String accName)
	{
		try
		{
			String outputValue = getText(locateElement("xpath","//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
			if (outputValue.contains(accName))
			{
				outputValue = outputValue.split("\\.")[0];
				reportStep("Account deleted successfully: "+accName, "Pass");
			}
			else
			{
				reportStep("Unable to delete Account: "+accName, "Fail");
			}
		}
		catch (Exception ex)
		{
			reportStep("Unable to delete Account: "+accName, "Fail");
		}
		return this;
	}
	
	public AccountsPage sortAccountsByName()
	{
		String sortMsg = null;
		boolean flag = false;
		
		try
		{
			click(locateElement("xpath","//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/.."));
			reportStep("Clicked on Accounts Header to sort accounts", "Info");
			solidWait(3);
			WebElement AccountsSort = locateElement("xpath","//span[@aria-live='assertive' and contains(text(),'Sorted')]");
			sortMsg = getText(AccountsSort);
			if (sortMsg.contains("Sorted Ascending"))
			{
				reportStep("Accounts sorted in Ascending Order", "Info");
				flag = true;
			}
			else
			{
				click(locateElement("xpath","//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/.."));
				sortMsg = getText(AccountsSort);
				if (sortMsg.contains("Sorted Ascending"))
				{
					reportStep("Accounts sorted in Ascending Order", "Info");
					flag = true;
				}
				else
				{
					reportStep("Unable to sort Accounts", "Fail");
				}
			}
			
	//		WebElements comparison post sorting
			if (flag)
			{		
				List<WebElement> rows = locateElements("xpath","//table[contains(@class,'uiVirtualDataTable')]/tbody/tr");
				int size = rows.size();
				String[] actual_order = new String[size-1];
				String[] sorted_order = new String[size-1];
				for (int i=1; i<size; i++)
				{
					WebElement listofAcc_Names = locateElement("xpath","//table[contains(@class,'uiVirtualDataTable')]/tbody/tr["+i+"]/th//a");
					listofAcc_Names.sendKeys(Keys.PAGE_DOWN); 	//scrolls page dynamically adjusted to Web Table content
					String str = getText(listofAcc_Names);
					actual_order[i-1] = str;
					sorted_order[i-1] = str;
				}
				
				Arrays.sort(sorted_order); 		// sorting the Array in ascending order
				int length = actual_order.length;
	//			int length1 = sorted_order.length;
				for (int j=0; j<=length-1; j++)
				{
					if (actual_order[j].equals(sorted_order[j]))
					{
						if (j==length-1) {
						reportStep("Verified sorted accounts displayed in Ascending Order", "Pass");}
					}
					else
					{
						reportStep("Unable to verify sorted accounts Order", "Fail");
					}
				}
			}
		}
		catch (Exception ex)
		{
			reportStep("Unable to sort and verify accounts", "Fail");
		}
		return this;
	}
}
