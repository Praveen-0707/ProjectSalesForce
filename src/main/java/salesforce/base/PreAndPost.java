package salesforce.base;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import salesforce.design.BrowserActions;
import salesforce.utils.ReadExcel;

public class PreAndPost extends BrowserActions {
	
	@BeforeSuite
	public void beforeSuite() {
		killBrowserInstances();
		startReport();
	}
	
	@BeforeClass
	public void beforeClass() {
		testDetailedReport();		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		launchApp(browserName);
	}

	@AfterMethod
	public void afterMethod() {
		closeActiveBrowser();
	}

	@AfterSuite
	public void afterSuite() {
		endReport();
	}

	@DataProvider(name="fetchData")
	public  Object[][] getData() throws IOException{
		return ReadExcel.readData(excelFileName,excelSheetName);		
	}
	
}
