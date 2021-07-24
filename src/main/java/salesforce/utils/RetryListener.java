package salesforce.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer, IRetryAnalyzer {
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(this.getClass());
	}

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
