package extentreports;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import customLlsteners.BaseTest;

//adding listener is important to add attachment to allure report
@Listeners({ allurelistener.AllureListener.class })
public class TestExtent extends BaseTest {

	@Test(alwaysRun = true)
	public void readUpdates() {
		System.out.println("exec reading test");
	}

	@Test(priority = 1)
	public void DoReg() {
		System.out.println("exec registration test");
		driver.findElement(By.id("email")).sendKeys("abc");
		Assert.fail("registration falied");

	}

	@Test(priority = 2, dependsOnMethods = "DoReg")
	public void DoLogin() {
		System.out.println("exec login test");
		throw new SkipException("login skipped");

	}
}
