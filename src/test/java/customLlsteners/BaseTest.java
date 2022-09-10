package customLlsteners;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import testcases.browserTest;

public class BaseTest {
	public static String browser ="firefox";
	public static WebDriver driver;
		@BeforeSuite
		public void setUp() {
			
			if(driver == null) {
				new testcases.browserTest(browser);
				driver = browserTest.driver;
				driver.get("http://www.google.com/");
				driver.manage().window().maximize();
			}
			
		}
		
		@AfterSuite
		public void tearDown() {
			System.out.println("closing");
			driver.quit();
		}

}
