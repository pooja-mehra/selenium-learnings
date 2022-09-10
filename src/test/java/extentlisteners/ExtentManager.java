package extentlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Test Report:", "Rahul Arora");
		extent.setSystemInfo("Organization", "Anime");
		extent.setSystemInfo("Build no", "W2A-1234");

		return extent;
	}
}
