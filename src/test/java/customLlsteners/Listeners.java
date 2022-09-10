package customLlsteners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener{
	public void onTestFailure(ITestResult result) {
	    // not implemented
		String shotName = result.getName().toString();
		try {
			testcases.browserTest.getScreenShot(shotName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

}
