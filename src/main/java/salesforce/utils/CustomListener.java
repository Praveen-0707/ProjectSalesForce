package salesforce.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.ITestAnnotation;

import salesforce.base.SalesforceBase;

public class CustomListener extends SalesforceBase implements ITestListener {
	
//	public CustomListener()
//	{
//		initListeners(driver);
//	}
//	
//	protected void initListeners(RemoteWebDriver driver) {
//		this.driver = driver;
//	    }
	
	public void onTestFailure(ITestResult result)
	{
//		System.out.println("...Testcase Failed...");
//		System.out.println(result.getName());
//		takeScreenshotOnFailed(result.getMethod().getMethodName());
	}

}