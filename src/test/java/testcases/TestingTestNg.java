package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

//no main method in testing
public class TestingTestNg {
	//methods are executed alphabetically or set priority
	//add dependency if method should not be executed if other method failed. use {"method1" , "method 2"}
	
	@Test(priority = 2, dependsOnMethods="doReg", groups="priority2")
	public void doLogin() {
		System.out.println("login test");
	}
	
	@Test(priority = 1)
	public void doReg() {
		System.out.println("registration test");
		//AssertJUnit.fail("registration failed");
	}
	
	//use soft dependencies when method is dependent but always run after dependable method
	@Test(dependsOnMethods = "doReg", alwaysRun=true)
	public void showNews() {
		System.out.println("latest News");
	}
}
