package regressiontest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class test3 extends BaseTest{
	//method with @BeforeTest exec before every testcase, @AfterTest similarly
	//same for suite, group, class and method
	
	@Test(priority = 2, dependsOnMethods="doReg")
	public void doLogin() {
		System.out.println("login test");
	}
	
	@Test(priority = 1)
	public void doReg() {
		System.out.println("registration test");
		AssertJUnit.fail("registration failed");
	}
	
	//use soft dependencies when method is dependent but always run after dependable method
	@Test(dependsOnMethods = "doReg", alwaysRun=true)
	public void showNews() {
		System.out.println("latest News");
	}
	
	@Test
	public void doSkip() {
		//to skip the test case forcefully, also condition be used
		throw new SkipException("skip this test case");
	}
}
