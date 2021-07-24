package salesforce.base;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import salesforce.utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;

// To access common methods in SalesForce Application
public class SalesforceBase extends Reporter {
	
	public RemoteWebDriver driver;
//	public EventFiringWebDriver driver;
	public ChromeOptions chromeOptions;
	public FirefoxOptions firefoxOptions;
	public EdgeOptions edgeOptions;
	public String excelFileName;
	public String excelSheetName;
	public Properties prop;
	public ExtentTest testNode;
	public String browserName;
	public String sUrl,sHubUrl,sHubPort;
	
	public void launchApp(String browser)
	{
		testNode = test.createNode(testName);
		setTest(testNode);
		try {
			FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");
			prop = new Properties();
			prop.load(fis);
			browserName = prop.getProperty("browser");
			
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browserName);
			dc.setPlatform(Platform.WINDOWS);

			
			if (browserName.equalsIgnoreCase("Chrome"))
			  {
				  WebDriverManager.chromedriver().setup();
				  chromeOptions = new ChromeOptions();
				  chromeOptions.addArguments("--disable-notifications");
				  chromeOptions.addArguments("--disable-notifications");
//				  chromeOptions.addArguments("--headless");
//				  chromeOptions.setHeadless(true);
				  driver = new ChromeDriver(chromeOptions);
//				  driver = new EventFiringWebDriver(webDriver);
//				  getDriver().register((WebDriverEventListener) this);
				  setDriver(driver);
			  }
  
			  if (browserName.equalsIgnoreCase("Firefox"))
			  {
				  WebDriverManager.firefoxdriver().setup();
				  FirefoxProfile profile = new FirefoxProfile();
				  profile.setPreference("app.update.auto", false);
				  profile.setPreference("app.update.enabled", false);
				  profile.setAcceptUntrustedCertificates(false);
				  profile.setPreference("browser.shell.checkDefaultBrowser", false);
				  
				  firefoxOptions = new FirefoxOptions();
//				  firefoxOptions.addArguments("headless");
//				  firefoxOptions.setHeadless(true);
				  firefoxOptions.setProfile(profile);
				  firefoxOptions.addPreference("dom.webnotifications.enabled", false);
				  driver = new FirefoxDriver(firefoxOptions);
				  setDriver(driver);
			  }
			  
			  if (browserName.equalsIgnoreCase("Edge"))
			  {
				  WebDriverManager.edgedriver().setup();
				  edgeOptions = new EdgeOptions();
				  edgeOptions.addArguments("disable-notifications");
				  driver = new EdgeDriver(edgeOptions);
				  setDriver(driver);
			  }
			  
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			getDriver().manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			
			getDriver().get(prop.getProperty("url"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
		
	public void deletePopUpConfirmation()
	{
		try {
			WebElement clkDelete = getDriver().findElementByXPath("//button//span[text()='Delete']");
			webDriverWait4ElementToBeClickable(clkDelete);
			clkDelete.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void highlight(WebElement ele)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try {
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');",ele);
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void killBrowserInstances()
	{
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("taskkill /F /IM ChromeDriver.exe /T");
			System.out.println("...Killing existing browser instances...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void takeScreenshotOnFailed(String methodName)
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("./screenshots/failedStep_"
													+ methodName + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void copyFilefromSourceToDestinationFolder(String srcFolder,
			String fileName, String fileExtension, String destFolder)
	{
		try {
			File srcFile = new File(srcFolder+fileName+"."+fileExtension+"");
			File destFile = new File(destFolder);
			FileUtils.copyFileToDirectory(srcFile,destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isDisabled(WebElement element)
	{
		boolean displayed = element.isDisplayed();
		
		if (displayed)
		{
			return true;
		}
		else
		{
			return displayed;
		}
	}
	
	public void closeAnOpenedTab(String value)
	{
		try {
			WebElement ele = getDriver().findElementByXPath("//button[contains(@title,'"+value+"') and contains(@title,'Close')]");
			webDriverWait4ElementToBeClickable(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void solidWait(Integer sec)
	{
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	public WebElement webDriverWait4VisibilityOfEle(WebElement ele){
		try {
			new WebDriverWait(getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
		} catch(StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();			
		} catch (TimeoutException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}
	
	public WebElement webDriverWait4ElementToBeClickable(WebElement ele){
		try {
			new WebDriverWait(getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(ele));
		} catch(StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();			
		} catch (TimeoutException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}
	
	public WebElement webDriverWait4FrameToBeAvailableAndSwitchTo(WebElement ele){
		try {
			new WebDriverWait(getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
		} catch(StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (NoSuchFrameException e) {
			e.printStackTrace();			
		} catch (TimeoutException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}
	
	public int webDriverWait4FrameToBeAvailableAndSwitchTo(int eleIndex){
		try {
			new WebDriverWait(getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(eleIndex));
		} catch(StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (NoSuchFrameException e) {
			e.printStackTrace();			
		} catch (TimeoutException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eleIndex;
	}
	
	public String getRandomString(Integer length)
	{
		String randomString = "";
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		char[] text = new char[length];
		for (int i=0; i<length; i++)
		{
			text[i] = characters.charAt(random.nextInt(characters.length()));
			randomString += text[i];
		}
		return randomString;
	}
	
	public void clickOnTab(String value)
	{
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		try {
			WebElement ele = getDriver().findElementByXPath("//a[@title='"+value+"']");
			webDriverWait4VisibilityOfEle(ele);
			js.executeScript("arguments[0].click();", ele);
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void uploadAttachment(String fileLocation)
	{	
		try {
			Robot robot = new Robot();
			StringSelection up = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(up, null);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			solidWait(3);

			WebElement ele = getDriver().findElement(By.xpath("//button[@type='button']//span[text()='Done']"));
			webDriverWait4VisibilityOfEle(ele);
			ele.click();
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int takeSnap() {
		int snapNum = (int)(Math.random() * 6996);
		File srcFile = getDriver().getScreenshotAs(OutputType.FILE);
		File destFile = new File("./reports/screenshots/img_"+ snapNum +".jpg");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return snapNum;
	}
}
