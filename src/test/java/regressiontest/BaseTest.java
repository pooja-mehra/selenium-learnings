package regressiontest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	@BeforeSuite
	public void setUp() {
		System.out.println("Initializing");
		
	}
	
	@AfterSuite
	public void tearDown() {
		System.out.println("closing");
		
	}

}
