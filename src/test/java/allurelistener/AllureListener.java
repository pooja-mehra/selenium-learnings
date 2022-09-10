package allurelistener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener {
	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	public InputStream is;

	public void onTestStart(ITestResult result) {

	}

	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	public void onTestSuccess(ITestResult result) {

	}

	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	@Attachment(value = "failed screenshot", type = "image/png")
	public void onTestFailure(ITestResult result) {
		System.out.println("test failed");
		String shotName = result.getName().toString();
		try {
			String path = testcases.browserTest.getScreenShot(shotName);
			is = new FileInputStream(path);
			Allure.addAttachment("Test Failed!", is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * Allure.addAttachment("My attachment", "My attachment content"); try { is =
		 * new FileInputStream(
		 * "/Applications/code/seleniumLearnings/screenshots/doLoginWed_Sep_07_17_53_29_PDT_2022.png"
		 * ); // BufferedImage fullImg = ImageIO.read(is); // ByteArrayOutputStream bos
		 * = new ByteArrayOutputStream(); // ImageIO.write(fullImg, "png", bos); //
		 * byte[] data = bos.toByteArray(); Allure.addAttachment("test failed", is);
		 * System.out.println("try block"); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

	/**
	 * Invoked each time a test is skipped.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */
	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	/**
	 * Invoked each time a method fails but has been annotated with
	 * successPercentage and this failure still keeps it within the success
	 * percentage requested.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	/**
	 * Invoked each time a test fails due to a timeout.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 */
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 */
	public void onStart(ITestContext context) {
		// not implemented
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 */
	public void onFinish(ITestContext context) {

	}

}
