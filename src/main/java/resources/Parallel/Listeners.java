package resources.Parallel;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.Parallel.BasePage;
import base.Parallel.ExtentManager;


public class Listeners extends BasePage implements ITestListener {

	public Listeners() throws IOException {
		super();
	}

	public synchronized void onStart(ITestContext context) {
		ExtentManager.getReport();
		
		/**
		 * The below is moved to onTestSuccess, onTestFailure and onTestSkipped
		 * This will allow us to use result object reference instead of context. 
		 */
		//ITestResult result;
		ExtentManager.createTest(context.getName(), context.getName());

	}
	
	
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println("Test passed: " + result.getName());
	  }
	
	
	
	public synchronized void onTestFailure(ITestResult result) {
		//ExtentManager.createTest(result.getName(), result.getName());
		ExtentManager.getTest().fail(result.getThrowable());
		try {
			System.out.println("Test failed: " + result.getName());
			takeSnapShot(result.getMethod().getMethodName());
			ExtentManager.attachImage();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized void onFinish(ITestContext context) {
		ExtentManager.flushReport();
	}

}

