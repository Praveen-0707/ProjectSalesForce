package salesforce.utils;

import java.io.IOException;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public abstract class Reporter {
	
	private static ThreadLocal<RemoteWebDriver> localDriver = new ThreadLocal<RemoteWebDriver>();
	private static ThreadLocal<ExtentTest> localTest = new ThreadLocal<ExtentTest>();
	
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public ExtentTest test;
	public String testName, testDescription, testAuthor, testCategory;
	
	public void setDriver(RemoteWebDriver driver)
	{
		localDriver.set(driver);
	}
	
	public static synchronized RemoteWebDriver getDriver()
	{
		return localDriver.get();
	}
	
	
	public void setTest(ExtentTest node)
	{
		localTest.set(node);
	}
	
	public static synchronized ExtentTest getTest()
	{
		return localTest.get();
	}
	
	public void startReport()
	{
		reporter = new ExtentHtmlReporter("./reports/result.html");
//		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		init_logs();
		Logs.info("Reporter Started in Before Suite");
	}
	
	public void init_logs()
	{
		DOMConfigurator.configure("log4j.xml");
	}
	
	public void endReport()
	{
		extent.flush();
		Logs.info("Reporter ended in After Suite");
		System.gc();
	}
	
	public void testDetailedReport()
	{
		test = extent.createTest(testName, testDescription);
		test.assignAuthor(testAuthor);
		test.assignCategory(testCategory);
	}
	
	public void reportStep(String message, String status, boolean snap)
	{
		MediaEntityModelProvider img = null;
		int snapNum = 0;
		
		try {
			if ((snap && status.equalsIgnoreCase("fail")) || (snap && status.equalsIgnoreCase("warn")))
			{
				
				snapNum = takeSnap();
				System.out.println("Img: "+snapNum);
				img = MediaEntityBuilder.createScreenCaptureFromPath(".././reports/screenshots/img_"+ snapNum +".jpg").build();
			}
			
		if (status.equalsIgnoreCase("pass"))
		{
			getTest().pass(message, img);
		}
		if (status.equalsIgnoreCase("info"))
		{
			getTest().info(message, img);
		}
		if (status.equalsIgnoreCase("warn"))
		{
			getTest().warning(message, img);
		}
		if (status.equalsIgnoreCase("fail"))
		{
			getTest().fail(message, img);
			throw new RuntimeException();
		}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reportStep(String message, String status)
	{
		reportStep(message, status, true);
	}
	public abstract int takeSnap();
//	public int takeSnap()
//	{
//		int snapNum = (int)(Math.random() * 6996);
//		File srcFile = getDriver().getScreenshotAs(OutputType.FILE);
//		File destFile = new File("./reports/screenshots/img_"+ snapNum +".jpg");
//		try {
//			FileUtils.copyFile(srcFile, destFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return snapNum;
//	}
	
	public void endTestDetails()
	{
		Logs.endTestCase(testName);
	}
}
