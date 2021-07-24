package salesforce.pages;

import salesforce.base.PreAndPost;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class NewDashboardPage extends PreAndPost {
	
	public NewDashboardPage()
	{
		this.driver = getDriver();
		try
		{
			solidWait(5);
			WebElement iframe = locateElement("xpath","(//iframe[@title='dashboard'])[last()]");
			switchToFrame(iframe);
			reportStep("Switched to Frame", "Pass");
		}
		catch(NoSuchFrameException e)
		{
			reportStep("NoSuchFrameExceptionCaptured", "Warn");
			switchToFrame(1);
		}
		catch(Exception e)
		{
			reportStep("Unable to switched to Frame", "Fail");
		}
	}
	
	public NewDashboardPage inputDashboardDescr(String value)
	{
		try {
			WebElement dashboardDescr = locateElement("xpath","//input[@id='dashboardDescriptionInput' and @type='text']");
			type(dashboardDescr, value);
			reportStep("Enter Dashboard Description: "+value, "Pass");
		} catch (WebDriverException e) {
			reportStep("Unable to enter Dashboard Description: "+value, "Fail");
		}
		return this;
	}
	
	public NewDashboardPage inputDashboardName(String value)
	{
		try {
			WebElement dashboardName = locateElement("xpath","//label[text()='Name']/following::div//input[@id='dashboardNameInput']");
			type(dashboardName, value);
			reportStep("Enter DashboardName: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter DashboardName: "+value, "Fail");
		}
		return this;
	}
		
	public NewDashboardPage clickOnCreateButton()
	{
		try {
			click(locateElement("xpath","//button[@id='submitBtn']"));
			solidWait(3);
			switchToDefaultContent();
			reportStep("Clicked on Create Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Create Button", "Fail");
		}
		return this;
	}
	
	public NewDashboardPage clickonDoneButton()
	{
		try {
			click(locateElement("xpath","//button[contains(@class,'doneEditing') and text()='Done']"));
			reportStep("Clicked on Done Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Done Button", "Fail");
		}
		return this;
	}
	
	public ServiceConsolePage clickOnSubscribeButton()
	{
		try {
			click(locateElement("xpath","//button[text()='Subscribe']"));
			reportStep("Clicked on Subscribe Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Subscribe Button", "Fail");
		}
		return new ServiceConsolePage();
	}
	
	public NewDashboardPage verifyDashboardTitle(String value)
	{
		try {
			WebElement TitleElement = locateElement("xpath","//div[@class='slds-page-header__name-title']//span[contains(text(),'Dashboard')]/following-sibling::span");
			String TitleVerification = getText(TitleElement);					
			if (TitleVerification.contains(value))
			{
				reportStep("Verified Dashboard title"+value, "Fail");
			}
			else
			{
				reportStep("Unable to verify Dashboard title", "Fail");
			}
		} catch (Exception e) {
			reportStep("Unable to verify Dashboard title", "Fail");
		}
		return this;
	}
}
