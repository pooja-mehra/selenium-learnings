package customLlsteners;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestCaptureScreen extends BaseTest{
	
	@Test
	public void doLogin() {
		System.out.println("Initializing");
		driver.findElement(By.id("email")).sendKeys("abc");
		driver.findElement(By.id("next")).click();
	}
}
