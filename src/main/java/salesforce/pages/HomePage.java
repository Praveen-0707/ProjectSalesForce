package salesforce.pages;

import salesforce.base.PreAndPost;
import org.openqa.selenium.WebElement;

public class HomePage extends PreAndPost {
	
	public HomePage()
	{
		this.driver = getDriver();
		switchToDefaultContent();
	}
	
	public HomePage clickToggleButton()
	{
		try {
			click(locateElement("class", "slds-icon-waffle"));
			reportStep("Clicked on Application Launcher", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Application Launcher", "Fail");
		}
		return this;
	}
	
	public HomePage clickViewAll()
	{
		try {
			click(locateElement("xpath","//button[text()='View All' and @class='slds-button']"));
			reportStep("Clicked on View All from Application Launcher", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on View All from Application Launcher", "Fail");
		}
		return this;
	}
	
	public HomePage selectTaskFromNavigationControl(String control)
	{
		try {
			click(locateElement("xpath","//button[@title='Show Navigation Menu']"));
			solidWait(9);
			WebElement selectTask = locateElement("xpath", "(//a[@title='"+control+"'])[last()]");
			scrollToVisibleElement(selectTask);
			click(selectTask);
			reportStep("Selected Task: "+ control +" from Navigation Menu on HomePage", "Pass");
			solidWait(3);
		} catch (Exception e) {
			reportStep("Unable to selected Task: "+ control +" from Navigation Menu on HomePage", "Fail");
		}
		return this;
	}
	
	public HomePage searchApp(String value)
	{
		try {
			type(locateElement("xpath", "//input[@type='search' and @placeholder='Search apps or items...']"), value);
			reportStep("Search for "+value+" application on HomePage", "Pass");
		} catch (Exception e) {
			reportStep("Unable to search for "+value+" application on HomePage", "Fail");
		}
		return this;
	}
	
	public void searchAndClickToLaunchApp(String value)
	{
		searchApp(value);
		clickOnApp(value);
	}
	
	public void clickOnApp(String appValue)
	{
		try {
			click(locateElement("xpath", "//p//mark[contains(text(),'"+appValue+"')]"));
			reportStep("Clicked on "+appValue+" Link", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on "+appValue+" Link", "Fail");
		}
	}
	
	public SalesPage clickOnSales()
	{
		try {
			click(locateElement("xpath", "(//p/mark[text()='Sales'])[last()-1]"));
			reportStep("Clicked on Sales Link", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Sales Link", "Fail");
		}
		return new SalesPage();
	}
	
}
