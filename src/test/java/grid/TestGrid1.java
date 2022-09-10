package grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestGrid1 {

	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	public Capabilities cap;

	public WebDriver getDriver() {

		return driver.get();
	}

	// @Parameters({ "browser" })
	@Test(dataProvider = "getData")
	public void doLogin(String browser) throws MalformedURLException, InterruptedException {

		// java -jar selenium-server-4.1.0.jar node --detect-drivers true
		// --publish-events tcp://192.168.1.16:4442 --subscribe-events
		// tcp://192.168.1.24:4443

		if (browser.equals("safari")) {

			cap = new SafariOptions();

		} else if (browser.equals("chrome")) {

			cap = new ChromeOptions();

		} else if (browser.equals("edge")) {

			cap = new EdgeOptions();
		}

		driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), cap));

		getDriver().get("https://www.way2automation.com/");
		// getDriver().findElement(By.name("q")).sendKeys("Hello Grid !!!" + browser);
		System.out.println(getDriver().getTitle() + "---on browser---" + browser);
		// Thread.sleep(10000);
		getDriver().quit();
	}

	@DataProvider(parallel = true)
	public Object[][] getData() {

		Object[][] data = new Object[3][1];

		data[0][0] = "chrome";
		data[1][0] = "edge";
		data[2][0] = "safari";

		return data;

	}
}
