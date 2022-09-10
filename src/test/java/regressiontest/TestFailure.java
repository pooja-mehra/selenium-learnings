package regressiontest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFailure extends BaseTest{
	
	@Test
	public void DoAuth() {
		System.out.println("AuthTest");
		Assert.fail("Auth failed");
	}
}
