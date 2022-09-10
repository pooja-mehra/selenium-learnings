package parameterization;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParameter {
	static String fileName = "/Applications/code/seleniumLearnings/files/parameter.xlsx";
	
	public static ExcelReader excelReader = new ExcelReader();
	
	//or using name of dataprovider method
	//to read data from another testfile add dataProviderClass= thisclassname.class
	@Test(dataProvider ="dp1")
	public void printUserInfo(String FName, String LName) {
		System.out.println(FName);
	}
	
	//parameterization using xml, now execute using xml
	@Parameters({"browser"})
	@Test(invocationCount = 2, threadPoolSize = 2)
	public void getBrowser(String browser) {
		System.out.println(browser);
	}
	
	//parallel = true to run tests cases from data ex. excel parallely
	@DataProvider(name="dp1")
	public static Object[][] getData(Method m) throws IOException{
		Object[][] data = null;
		//to provide different data/arguments to test methods using same eprovider
		if(m.getName().equals("printUserInfo")) {
			data = excelReader.getExcelData(fileName);
			System.out.println(data[0][1]);
		}
		
		return data;
		/*Object[][] data = new Object[1][2];
		data[0][0] = "pooja";
		data[0][1] ="mehra";
		return data;*/
	}

}
