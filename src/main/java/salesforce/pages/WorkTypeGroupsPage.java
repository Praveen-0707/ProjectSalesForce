package salesforce.pages;

import salesforce.base.PreAndPost;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class WorkTypeGroupsPage extends PreAndPost {
	
	public WorkTypeGroupsPage()
	{
		this.driver = getDriver();
		switchToDefaultContent();
	}
	
	public WorkTypeGroupsPage searchWorkTypeGroup(String value)
	{
		try {
			WebElement searchWTG = locateElement("xpath","//input[contains(@name,'WorkTypeGroup-search-input')]");
			typeAndEnter(searchWTG, value);
			reportStep("Search WorkTypeGroup: "+value, "Pass");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to search WorkTypeGroup: "+value, "Fail");
		}
		return this;
	}
		
	public WorkTypeGroupsPage clickOnNewWorkTypeGroup()
	{
		try {
			click(locateElement("xpath","//div[text()='New']"));
			reportStep("click on new WorkTypeGroup", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on new WorkTypeGroup", "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage inputWorkTypeGroupDescr(String value)
	{
		try {
			WebElement worktypeGrpName = locateElement("xpath","//label/span[text()='Description']/following::textarea");
			type(worktypeGrpName, value);
			reportStep("Enter WorkTypeGroup description: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to eEnter WorkTypeGroup description: "+value, "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage inputWorkTypeGroupName(String value)
	{
		try {
			WebElement worktypeGrpName = locateElement("xpath","(//span[text()='Work Type Group Name'])[2]/../following-sibling::input");
			type(worktypeGrpName, value);
			reportStep("Enter WorkTypeGroup Name: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to eEnter WorkTypeGroup Name: "+value, "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage selectGroupType(String value)
	{
		try {
			click(locateElement("xpath","//span[text()='Group Type']/following::a[@class='select' and @role='button']"));
			solidWait(1);
			WebElement ele = locateElement("xpath","(//div[@class='select-options' and @role='menu']/ul//a[contains(@title,'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			click(ele);
			reportStep("Select Group Type: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to select Group Type: "+value, "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage clickOnSaveButton()
	{
		try {
			click(locateElement("xpath","(//button/span[text()='Save'])[last()]"));
			reportStep("click on Save button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Save button", "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage clickRefreshButton()
	{
		try {
			click(locateElement("name","refreshButton"));
			reportStep("click on Refresh button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Refresh button", "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage clickOnWorkTypeGroupHeader()
	{
		try {
			click(locateElement("xpath","//span[@title='Work Type Group Name']/parent::a"));
			reportStep("click on WorkTypeGroupHeader", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on WorkTypeGroupHeader", "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage createNewWorkTypeGroupValidation(String wtgName)
	{
		try
		{
			String outputValue = getText(locateElement("xpath","(//span[text()='Description'])[1]/following::div//span[@class='uiOutputText']"));	
			if (outputValue.contains(wtgName))
			{
				reportStep("Work Type Group - " + outputValue + " was created", "Pass");
			}
			else
			{
				reportStep("Unable to create Work Type Group", "Fail");
			}
		}
		catch (Exception ex)
		{
			reportStep("Unknown Webdriver exception occured", "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage editWorkTypeGroup(String wtgName)
	{
		try {
			click(locateElement("xpath","(//a[text()='" + wtgName + "'])[1]//following::td//a[@role='button']"));
			click(locateElement("xpath","//div[@role='button' and @title='Edit']/.."));
			reportStep("Click on edit Work Type Group: " + wtgName, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on edit Work Type Group: " + wtgName, "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage deleteWorkTypeGroup(String wtgName)
	{
		try {
			click(locateElement("xpath","(//a[text()='" + wtgName + "'])[1]//following::td//a[@role='button']"));
			click(locateElement("xpath","//div[@role='button' and @title='Delete']/.."));
			reportStep("Click on delete Work Type Group: " + wtgName, "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on delete Work Type Group: " + wtgName, "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage clickOnWorkTypeGroupsTabOptions()
	{
		try {
			WebElement WTG = locateElement("xpath","//a[contains(@title,'Work Type Groups')]/following::div[contains(@class,'context-bar')][1]");
			clickByJS(WTG);
			solidWait(2);
			reportStep("Clicked on Work Type Group Tab", "Pass");
		} catch (JavascriptException e) {
			reportStep("Unable to click on Work Type Group Tab", "Fail");
		} catch (Exception e) {
			reportStep("Unknown Error - Unable to click on Work Type Group Tab", "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage SelectNewWorkTypeGroup()
	{
		try {
			WebElement WTG = locateElement("xpath","//span[text()='New Work Type Group']/ancestor::a[@role='menuitemcheckbox']");
			clickByJS(WTG);
			reportStep("Clicked on New Work Type Group", "Pass");
			solidWait(2);
		} catch (JavascriptException e) {
			reportStep("Unable to click on New Work Type Group", "Fail");
		} catch (Exception e) {
			reportStep("Unknown Error - Unable to click on New Work Type Group", "Fail");
		}
		return this;
	}
		
	public WorkTypeGroupsPage searchAndClickOnWorkTypeGroup(String wtgName)
	{
		try {
			searchWorkTypeGroup(wtgName);
			click(locateElement("xpath","(//a[text()='" + wtgName + "'])[1]"));
			reportStep("Search for Work Type Group: "+wtgName + ". Clicked on it", "Pass");
		} catch (Exception e) {
			reportStep("Unable to Search for Work Type Group: "+wtgName, "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage editWorkTypeGroupValidation(String value)
	{
		try
		{
			String outputValue = getText(locateElement("xpath","(//span[text()='Description'])/following::div//span[text()='Description']/following::span[@class='uiOutputTextArea']"));
			
			if (outputValue.contains(value))
			{
				reportStep("Work Type Group - " + outputValue + " was updated successfully", "Pass");
			}
			else
			{
				reportStep("Unable to update Work Type Group", "Fail");
			}
		}
		catch (Exception ex)
		{
			reportStep("Unknown Error - Unable to update Work Type Group", "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage deleteWorkTypeGroupValidation(String value)
	{
		try
		{
		List<WebElement> rows = locateElements("xpath","//table[contains(@class,'uiVirtualDataTable')]/tbody/tr");
		int size = rows.size();
		for (int i = 1; i <= size; i++)
		{
			WebElement listofWTG_Names = locateElement("xpath","//table[contains(@class,'uiVirtualDataTable')]/tbody/tr["+i+"]/th//a");
			String WTG_Names = getText(listofWTG_Names);
		
			if (WTG_Names.equals(value))
			{
				reportStep("Unable to Delete WorkTypeGroup or Duplicate entry exist", "Fail");
				break;
			}
			else
			{
				if (i == size)
				{
					reportStep("Delete WorkTypeGroup was successful","Pass");
				}
			}
		}
		}
		catch (Exception ex)
		{
			reportStep("Unknown Error - Unable to Delete WorkTypeGroup or Duplicate entry exist", "Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage sortWorkTypeGroups()
	{
		try
		{
			List<String> namesSet1 = new ArrayList<String>();
			List<WebElement> elements = locateElements("xpath","//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr");
			int size = elements.size();
			for (int i=1; i<=size; i++)
			{
				WebElement workGroupNames = locateElement("xpath","//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr["+i+"]/th//a");
				String workGroupNamesBeforeSort = getText(workGroupNames);
				namesSet1.add(workGroupNamesBeforeSort);
			}
			Collections.sort(namesSet1);

//			clicks on Name Sort
			click(locateElement("xpath","//span[@title='Work Type Group Name']/parent::a"));
			solidWait(3);
			
			List<String> namesSet2 = new ArrayList<String>(); 
			List<WebElement> wtgToSort = locateElements("xpath","//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr");
			int cnt = wtgToSort.size();
			for (int j=1; j<=cnt; j++)
			{
				WebElement workGroupNamesToSort = locateElement("xpath","//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr["+j+"]/th//a");
				String workGroupNamesAfterSort = getText(workGroupNamesToSort);
				namesSet2.add(workGroupNamesAfterSort);
			}
			
			boolean isEqual = namesSet1.equals(namesSet2);
			if (isEqual)
			{
				reportStep("Names are sorted in order." + namesSet2, "Pass");
			}
			else
			{
				reportStep("Unable to sorted the names","Fail");
			}
		}
		catch (Exception ex)
		{
			reportStep("Unknown Error - Unable to sorted the names","Fail");
		}
		return this;
	}
	
	public WorkTypeGroupsPage errValidationOnNewWTGCreation(String errMsg)
	{
		try
		{
			String errMsgValue = getText(locateElement("class","form-element__help"));
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(errMsg, errMsgValue);
			softAssert.assertAll();
			reportStep("Error validation successful for New WTG creation: "+errMsg, "Pass");
		}
		catch (Exception ex)
		{
			reportStep("Unable to validate error validation for New WTG creation", "Fail");
		}
		return this;
	}
	
}
