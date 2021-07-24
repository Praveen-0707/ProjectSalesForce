package salesforce.design;

import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public interface IBrowserActions {

	/**
	 * This method will locate the element using any given locator
	 * @param locator  - The locator by which the element to be found
	 * @param locValue - The locator value by which the element to be found
	 * @author Praveen Raj A
	 * @throws NoSuchElementException
	 */
	public WebElement locateElement(String locator, String locValue) ;	

	/**
	 * This method will locate the elements using any given locator
	 * @param locator  - The locator by which the elements to be found
	 * @param locValue - The locator value by which the elements to be found
	 * @author Praveen Raj A
	 * @return A list of all WebElements, or an empty list if nothing matches.
	 */
	public List<WebElement> locateElements(String type, String value);
	/**
	 * This method will enter the value in the given text field 
	 * @param ele   - The WebElement (text field) in which the data to be entered
	 * @param data  - The data to be sent to the WebElement
	 * @author Praveen Raj A
	 * @throws ElementNotVisibleException		 * 
	 */
	public void type(WebElement ele, String data) ;

	/**
	 * This method will click the element and take snap
	 * @param ele   - The WebElement (button/link/element) to be clicked
	 * @author Praveen Raj A
	 */
	public void click(WebElement ele);

	/**
	 * This method will get the text of the element
	 * @param ele   - The WebElement (button/link/element) in which text to be retrieved
	 * @author Praveen Raj A - TestLeaf
	 */
	public String getText(WebElement ele);

	/**
	 * This method will select the drop down visible text
	 * @param ele   - The WebElement (dropdown) to be selected
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Praveen Raj A - TestLeaf
	 */
	public void selectDropDownUsingVisibleText(WebElement ele, String value) ;

	/**
	 * This method will select the drop down using value
	 * @param ele   - The WebElement (dropdown) to be selected
	 * @param value The value attribute to be selected from the dropdown 
	 * @author Praveen Raj A - TestLeaf
	 */
	public void selectDropDownUsingValue(WebElement ele, String value) ;
	
	/**
	 * This method will select the drop down using index
	 * @param ele   - The WebElement (dropdown) to be selected
	 * @param index The index to be selected from the dropdown 
	 * @author Praveen Raj A - TestLeaf
	 */
	public void selectDropDownUsingIndex(WebElement ele, int index) ;

	/**
	 * This method will verify browser actual title with expected
	 * @param title - The expected title of the browser
	 * @author Praveen Raj A - TestLeaf
	 */
	public boolean verifyExactTitle(String expectedTitle);
	
	/**
	 * This method will verify browser actual title with expected text using contains
	 * @param title - The expected title of the browser
	 * @author Praveen Raj A - TestLeaf
	 */
	public boolean verifyPartialTitle(String expectedTitle);


	/**
	 * This method will verify exact given text with actual text on the given element
	 * @param ele   - The WebElement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @author Praveen Raj A - TestLeaf
	 */
	public void verifyExactText(WebElement ele, String expectedText);

	/**
	 * This method will verify given text contains actual text on the given element
	 * @param ele   - The WebElement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @author Praveen Raj A - TestLeaf
	 */
	public void verifyPartialText(WebElement ele, String expectedText);

	/**
	 * This method will verify exact given attribute's value with actual value on the given element
	 * @param ele   - The WebElement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 * @author Praveen Raj A - TestLeaf
	 */
	public void verifyExactAttribute(WebElement ele, String attribute, String value);

	/**
	 * This method will verify partial given attribute's value with actual value on the given element
	 * @param ele   - The WebElement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 * @author Praveen Raj A - TestLeaf
	 */
	public void verifyPartialAttribute(WebElement ele, String attribute, String value);

	/**
	 * This method will verify if the element (Radio button, Checkbox)  is selected
	 * @param ele   - The WebElement (Radio button, Checkbox) to be verified
	 * @author Praveen Raj A - TestLeaf
	 */
	public void verifySelected(WebElement ele);

	/**
	 * This method will verify if the element is visible in the DOM
	 * @param ele   - The WebElement to be checked
	 * @author Praveen Raj A - TestLeaf
	 */
	public void verifyDisplayed(WebElement ele);

	/**
	 * This method will switch to the Window of interest
	 * @param index The window index to be switched to. 0 -> first window 
	 * @author Praveen Raj A - TestLeaf
	 */
	public void switchToWindow(int index);

	/**
	 * This method will switch to the specific frame
	 * @param ele   - The WebElement (frame) to be switched
	 * @author Praveen Raj A - TestLeaf
	 */
	public void switchToFrame(WebElement ele);

	/**
	 * This method will accept the alert opened
	 * @author Praveen Raj A - TestLeaf
	 */
	public void acceptAlert();

	/**
	 * This method will dismiss the alert opened
	 * @author Praveen Raj A - TestLeaf
	 */
	public void dismissAlert();

	/**
	 * This method will return the text of the alert
	 * @author Praveen Raj A - TestLeaf
	 */
	public String getAlertText();

	/**
	 * This method will close the active browser
	 * @author Praveen Raj A - TestLeaf
	 */
	public void closeActiveBrowser();		

	/**
	 * This method will close all the browsers
	 * @author Praveen Raj A - TestLeaf
	 */
	public void closeAllBrowsers();

}


