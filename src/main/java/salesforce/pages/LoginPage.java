package salesforce.pages;

import salesforce.base.PreAndPost;
import java.util.Properties;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class LoginPage extends PreAndPost {
	
	public LoginPage(Properties prop)
	{
		this.driver = getDriver();
		this.prop = prop;
	}
	
	public LoginPage enterUsername()
	{
		
		try {
			WebElement ele = locateElement("id", "username");
			highlightElement(ele);
			type(ele, prop.getProperty("username"));
			reportStep("Username entered as: "+prop.getProperty("username"), "Pass");
		} catch (Exception e) {
			reportStep("Username not entered in Login Page", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public LoginPage enterPassword()
	{
		try {
			WebElement ele = locateElement("id", "password");
			highlightElement(ele);
			type(ele, prop.getProperty("password"));
			reportStep("Password entered as: "+prop.getProperty("password"), "Pass");
		} catch (Exception e) {
			reportStep("Password not entered in Login Page", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage clickLogin()
	{
		try {
			click(locateElement("Login"));
//			option-1
//			WebElement pageLoad = wait.until(ExpectedConditions.visibilityOf(getDriver().findElementByXPath("//div[@class='linkElements']/a[text()='Switch to Lightning Experience']")));
//			pageLoad.click();
			
//			option-2
			WebElement pageLoad1 = driver.findElementByXPath("//div[@class='linkElements']/a[text()='Switch to Lightning Experience']");
			boolean isDisplayed = pageLoad1.isDisplayed();
			if (isDisplayed)
			{
				clickByJS(pageLoad1);
				reportStep("Successfully Logged into Saleforce", "Pass", false);
			}
		} catch (NoSuchElementException e) {
			reportStep("Salesforce Classic Login is not displayed", "Info");
		}
		catch (Exception e) {
			reportStep("Login into Saleforce application failed", "Fail");
		}
		
		return new HomePage();
	}
}
