package salesforce.pages;

import salesforce.base.PreAndPost;

public class LogoutPage extends PreAndPost {
	
	public LogoutPage()
	{
		this.driver = getDriver();
		driver.switchTo().defaultContent();
	}
	
	public LoginPage clickLogout()
	{
		try {
			click(locateElement("xpath", "//img[@alt='User' and @title='User']/ancestor::button"));
			click(locateElement("link", "Log Out"));
			reportStep("Logged out from Application", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Log out", "Fail");
		}
		return new LoginPage(prop);
	}
}
