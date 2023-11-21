package com.qa.OpenCart.base;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass extends TestBase implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		capture(result.getTestContext().getName()+" "+result.getMethod().getMethodName()+".png");
	
	}

	
	
}
