package salesforce.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestcases implements IRetryAnalyzer {

	int maxRetry = 3;
	int retryCount = 0;
	
	public boolean retry(ITestResult result) {
		if ( retryCount < maxRetry)
		{
			++retryCount;
			return true;
		}
		
		return false;
	}
}
