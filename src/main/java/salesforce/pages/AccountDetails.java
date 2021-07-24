package salesforce.pages;

import salesforce.base.SalesforceBase;

public class AccountDetails extends SalesforceBase {
	
	public AccountDetails()
	{
		this.driver = getDriver();
		driver.switchTo().defaultContent();
	}
}
