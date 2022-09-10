package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testNg2 {
	
	@Test()
	public void validateTitle() {
		
		String expectedTitle ="pass";
		String actualTitle ="fail";
		
		SoftAssert softAssert = new SoftAssert();
		//soft assert execute all the cases even if some fail
		//hard assert or assert stops execution when first case fails
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
		System.out.println("its a soft assert");
		Assert.fail("it failed");
		System.out.println("its a soft assert 2");
		
		//execute all but report the failure
		softAssert.assertAll();
	}
	

}
