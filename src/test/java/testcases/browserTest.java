package testcases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class browserTest {

	static String browser = "chrome";
	public static WebDriver driver;

	public browserTest(String browser) {
		switch (browser) {
		case "chrome":
			ChromeOptions opt = new ChromeOptions();
			// To disable te info bar for automation software
			opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			// To accept insecure cert
			// opt.setAcceptInsecureCerts(true);
			// opt.addArguments("window-size=1400,1000");
			// opt.setExperimentalOption("mobileEmulation", mobileEm);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(opt);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "opera":
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			break;

		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;

		}
	}

	public static String getScreenShot(String shotName) throws IOException {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File fullShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fullShot, new File("./screenshots/" + shotName + fileName));
		Path content = Paths.get(System.getProperty("user.dir") + "/screenshots/" + shotName + fileName);
		return content.toString();
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		// TODO Auto-generated method stub

		// for mobile emulation
		Map<String, String> mobileEm = new HashMap<>();
		mobileEm.put("deviceName", "iPhone X");

		// chrome dev tools
		/*
		 * DevTools devTools = ((ChromeDriver) driver).getDevTools();
		 * devTools.createSession();
		 */

		// handling ssl cert uding dev tools
		/*
		 * devTools.send(Security.enable());
		 * devTools.send(Security.setIgnoreCertificateErrors(true));
		 * driver.get("https://expired.badssl.com");
		 */

		// geolocation using devtools
		/*
		 * devTools.send(Emulation.setGeolocationOverride(Optional.of((Number)51.509865)
		 * , Optional.of((Number)(-0.118092)), Optional.of((Number)100)));
		 * driver.get("https://mycurrentlocation.net/");
		 */

		// emulation using devtools
		/*
		 * Map<String,Object> metrics = new HashMap<String,Object>(){ {
		 * 
		 * put("width", 375); put("height", 812); put("mobile", true);
		 * put("deviceScaleFactor", 50); } }; //when some devTools function not
		 * found/work use cdpcommand ((ChromiumDriver)
		 * driver).executeCdpCommand("Emulation.setDeviceMetricsOverride",metrics);
		 * driver.get("https://selenium.dev/");
		 */

		// block network call for ex. blocking images for fast testing using devtools
		/*
		 * devTools.send(Network.enable(Optional.of(100), Optional.of(100),
		 * Optional.of(100)));
		 * devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg",".png","*.jpeg"
		 * ))); driver.get("http://makemytrip.com");
		 */

		// To check the performance of app on slow network chrome dev tools
		/*
		 * devTools.send(Network.enable(Optional.of(100), Optional.of(100),
		 * Optional.of(100))); devTools.send(Network.emulateNetworkConditions(false, 0,
		 * 20000, 10000, Optional.of(ConnectionType.CELLULAR2G)));
		 * driver.get("http://way2automation.com");
		 */

		// overriding time zone using chr dv tool
		/*
		 * devTools.send(Emulation.setTimezoneOverride("EST"));
		 * driver.get("https://whatismytimezone.com/");
		 */

		// handling logs devyools
		/*
		 * devTools.send(Log.enable()); devTools.send(Console.enable());
		 * devTools.addListener(Log.entryAdded(), entry ->{
		 * System.out.println("Text is : "+entry.getText());
		 * System.out.println("TimeStamp is : "+entry.getTimestamp());
		 * System.out.println("Source is : "+entry.getSource());
		 * System.out.println("Level is : "+entry.getLevel());
		 * 
		 * });
		 * 
		 * devTools.addListener(Console.messageAdded(), message ->{
		 * System.out.println("Console Text is : "+message.getText()); });
		 * driver.get("http://flipkart.com"); ((JavascriptExecutor)
		 * driver).executeScript("console.log('This is a sample log')");
		 */

		// accessing req/res headers
		/*
		 * devTools.send(Network.enable(Optional.empty(), Optional.empty(),
		 * Optional.empty())); devTools.addListener(Network.requestWillBeSent(), request
		 * -> { Headers header = request.getRequest().getHeaders(); if
		 * (!header.isEmpty()) { System.out.println("Request Headers: ");
		 * header.forEach((key, value) -> { System.out.println("  " + key + " = " +
		 * value); }); } }); devTools.addListener(Network.responseReceived(), response
		 * -> { Headers header = response.getResponse().getHeaders(); if
		 * (!header.isEmpty()) { System.out.println("Response Headers: ");
		 * header.forEach((key, value) -> { System.out.println("  " + key + " = " +
		 * value); }); }
		 * System.out.println("Response URL is : "+response.getResponse().getUrl()
		 * +"  status code is : "+response.getResponse().getStatus()); }); //adding
		 * custom headers Map<String, Object> headers = new HashMap<String,Object>();
		 * headers.put("customHeaderName", "customHeaderValue"); headers.put("Pooja",
		 * "Automation "); Headers head = new Headers(headers);
		 * devTools.send(Network.setExtraHTTPHeaders(head));
		 * driver.get("https://flipkart.com");
		 */

		// mock user agent dev tools
		/*
		 * String userAgent =
		 * "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36"
		 * ; devTools.send(Network.setUserAgentOverride(userAgent, Optional.empty(),
		 * Optional.empty(), Optional.empty()));
		 * driver.get("https://www.whatismybrowser.com/detect/what-is-my-user-agent");
		 */

		// analizing performance metrics dev tools
		/*
		 * devTools.send(Performance.enable(Optional.of(Performance.EnableTimeDomain.
		 * TIMETICKS))); driver.get("http://google.com"); List<Metric> metrics =
		 * devTools.send(Performance.getMetrics()); metrics.forEach(metric
		 * ->System.out.println(metric.getName()+" : "+metric.getValue()));
		 */

		// handling svg graph
		/*
		 * driver.get("https://www.covid19india.org/");
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 * driver.findElement(By.xpath(
		 * "//body/div/div/div/div/div/div/div/div[9]/div[1]")).click();
		 * List<WebElement> graphPoints = driver.findElements(By.
		 * xpath("(//*[name()='svg' and @preserveAspectRatio='xMidYMid meet'])[6]//*[local-name()='circle']"
		 * )); System.out.println(graphPoints.size()); Actions action = new
		 * Actions(driver); for(WebElement point: graphPoints) {
		 * action.moveToElement(point).perform();
		 * System.out.println(driver.findElement(By.
		 * xpath("//div[@class='stats is-confirmed']/div/h2")).getText()); }
		 */

		// handling shadow-root elements using root elements, only open shadow root cab
		// be accessed
		// getShadowRoot is part of selenium driver so no need to cast shadow root as
		// web elemnt
		/*
		 * driver.get("chrome://downloads/"); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); WebElement
		 * root = driver.findElement(By.cssSelector("downloads-manager")); //WebElement
		 * shadowRoot1 = (WebElement)
		 * ((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot"
		 * ,root); SearchContext shadowRoot1 = root.getShadowRoot(); WebElement root2 =
		 * shadowRoot1.findElement(By.cssSelector("downloads-toolbar")); SearchContext
		 * shadowRoot2 = root2.getShadowRoot(); //WebElement shadowRoot2 = (WebElement)
		 * ((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot"
		 * ,root2); WebElement root3 =
		 * shadowRoot2.findElement(By.cssSelector("cr-toolbar")); SearchContext
		 * shadowRoot3 = root3.getShadowRoot(); //WebElement shadowRoot3 = (WebElement)
		 * ((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot"
		 * ,root3); WebElement root4 =
		 * shadowRoot3.findElement(By.cssSelector("cr-toolbar-search-field"));
		 * SearchContext shadowRoot4 = root4.getShadowRoot(); //WebElement shadowRoot4 =
		 * (WebElement)
		 * ((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot"
		 * ,root4);
		 * shadowRoot4.findElement(By.cssSelector("#searchInput")).sendKeys("Hello");
		 */

		// handling js alert using Alert Interface
		/*
		 * driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		 * driver.findElement(By.name("proceed")).click(); Thread.sleep(5000); Alert
		 * alert = driver.switchTo().alert(); alert.accept();
		 */

		/*
		 * driver.get("https://google.com"); WebElement img =
		 * driver.findElement(By.xpath("//div[@id='hplogoo']")); //use rectangle to get
		 * the height, width,x,y cord Rectangle rect = img.getRect();
		 * System.out.println(rect.height +"," + rect.getX());
		 */

		// switch to iframe to click on
		/*
		 * driver.get(
		 * "https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_iframe_create");
		 * System.out.println(driver.findElements(By.tagName("iframe")).size());
		 * driver.switchTo().frame("iframeResult");
		 * 
		 * //using javascript executor to execute js functions
		 * ((JavascriptExecutor)driver).executeScript("myFunction()");
		 * ((JavascriptExecutor)driver).executeScript("arguments[0].style.color='red'",
		 * driver.findElement(By.tagName("p")),driver.findElement(By.tagName("button")))
		 * ;
		 */

		/*
		 * driver.findElement(By.xpath("html/body/button")).click();
		 * driver.findElement(By.xpath("html/body/button")).click(); System.out.println(
		 * driver.findElements(By.tagName("iframe")).size());
		 * driver.switchTo().frame(1);
		 * driver.findElement(By.id("search2")).sendKeys("hello"); //To accept insecure
		 * cert
		 * 
		 * 
		 * //take back to main window //driver.switchTo().defaultContent();
		 * //driver.switchTo().frame("iframeResult"); or just switch to parentframe
		 * driver.switchTo().parentFrame();
		 * driver.findElement(By.xpath("html/body/button")).click();
		 * 
		 * driver.switchTo().newWindow(WindowType.TAB);
		 * driver.get("https://google.com");
		 * 
		 * driver.switchTo().newWindow(WindowType.WINDOW);
		 * driver.get("https://gmail.com");
		 * 
		 * Set<String> winids = driver.getWindowHandles(); Iterator<String> itr =
		 * winids.iterator(); while(itr.hasNext()) {
		 * driver.switchTo().window(itr.next()); System.out.println(driver.getTitle());
		 * driver.close(); }
		 */

		// earlier auth way https://uername:passwordurl
		// new secure way
		/*
		 * ((HasAuthentication)
		 * driver).register(UsernameAndPassword.of("admin","admin"));
		 * driver.get("http://the-internet.herokuapp.com/basic_auth");
		 */

		// print to pdf
		// only runs in headless mode
		/*
		 * ChromeOptions opt = new ChromeOptions(); opt.addArguments("headless");
		 * 
		 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
		 * ChromeDriver(opt); driver.get("https://selenium.dev/");
		 * driver.manage().window().maximize(); Pdf pdf = ((PrintsPage)
		 * driver).print(new PrintOptions());
		 * Files.write(Paths.get("./selenium.pdf"),OutputType.BYTES.convertFromBase64Png
		 * (pdf.getContent())); //driver.get("https://www.hdfc.com/");
		 */

		// driver.get("http://www.tizag.com/javascriptT/javascriptalert.php");
		/*
		 * driver.get("https://www.goibibo.com/"); driver.findElement(By.xpath(
		 * "/html/body/div[1]/div/div[2]/div[2]/div/div[1]/div[3]/div/p[1]/span")).click
		 * ();
		 * driver.findElement(By.xpath("//div[@aria-label= 'Tue Aug 30 2022']")).click()
		 * ;
		 */

		// handling combo checkboxes
		/*
		 * driver.get("https://www.jobserve.com/in/en/Job-Search/");
		 * driver.findElement(By.xpath("//span[@id = 'ddcl-selInd']")).click();
		 * Thread.sleep(1000); driver.findElement(By.
		 * xpath("//div[@class ='indEligCatContain']/div[@id ='industryDisplay']/div/div/div[1]"
		 * )).click(); List<WebElement> inusList = driver.findElements(By.
		 * xpath("//div[@class ='indEligCatContain']/div[@id ='industryDisplay']/div/div/div/label"
		 * )); for(WebElement indus :inusList) { System.out.println(indus.getText());
		 * if(indus.getText().equalsIgnoreCase("education")) { indus.click(); } }
		 */

		// handling calendar
		/*
		 * SimpleDateFormat df = new SimpleDateFormat(); Date testDate =
		 * df.parse("02/22/2022"); Calendar cal = Calendar.getInstance();
		 * cal.setTime(testDate); System.out.println('Day: '
		 * +cal.get(Calendar.DAY_OF_MONTH));
		 */

		/*
		 * driver.get("https://ksrtc.in/oprs-web/");
		 * driver.findElement(By.id("fromPlaceName")).sendKeys("BENG");
		 * Thread.sleep(2000);
		 * driver.findElement(By.id("fromPlaceName")).sendKeys(Keys.DOWN);
		 * driver.findElement(By.id("fromPlaceName")).sendKeys(Keys.DOWN);
		 * JavascriptExecutor exe= (JavascriptExecutor) driver; String script =
		 * "document.getElementById(\"fromPlaceName\").value;"; String text =
		 * (String)exe.executeScript(script); System.out.println(text);
		 */

		/*
		 * driver.get("https://ksrtc.in/oprs-web/guest/home.do"); driver.findElement(By.
		 * cssSelector("[class ='lbl_input latoBold  appendBottom5']")).click();
		 */

		/*
		 * driver.get("https://ksrtc.in/oprs-web/");
		 * driver.findElement(By.id("fromPlaceName")).sendKeys("BENG");
		 * driver.findElement(By.id("fromPlaceName")).sendKeys(Keys.DOWN);
		 * driver.findElement(By.id("fromPlaceName")).sendKeys(Keys.DOWN);
		 * driver.findElement(By.id("fromPlaceName")).getAttribute("value");
		 */
		/*
		 * driver.get("https://paytm.com/"); Thread.sleep(5000);
		 * driver.findElement(By.xpath("//div[@class = '_13ls6 _2R77q']")).click();
		 * System.out.println("iframes: "+
		 * driver.findElements(By.tagName("iframe")).size());
		 */

		/*
		 * ChromeOptions ops = new ChromeOptions();
		 * ops.addArguments("--disable-notifications");
		 * 
		 * driver = new ChromeDriver(ops); //driver.get("https://www.spicejet.com/");
		 * 
		 * driver.get("https://www.americangolf.co.uk/"); Actions a = new
		 * Actions(driver); Thread.sleep(2000); driver.findElement(By.xpath(
		 * "//*[@id='termly-code-snippet-support']/div/div/div/div/div/div[2]/div[2]/button"
		 * )).click(); //build().perform() is used when actions are combined else just
		 * perform() works
		 * a.moveToElement(driver.findElement(By.xpath("//input[ @id = 'q' ]"))).click()
		 * .keyDown(Keys.SHIFT).sendKeys("hello").build().perform(); Thread.sleep(2000);
		 */

		// jquery slider
		/*
		 * driver.get("https://jqueryui.com/slider/"); driver.switchTo().frame(0);
		 * WebElement slider =
		 * driver.findElement(By.xpath("//div[@id = 'slider']/span")); //to set width
		 * slider frames width can be used slider.getSize().width new
		 * Actions(driver).dragAndDropBy(slider, 200,0).perform();
		 */

		// resizable ele
		/*
		 * driver.get("https://jqueryui.com/resources/demos/resizable/default.html");
		 * WebElement resize =
		 * driver.findElement(By.xpath("//div[@id = 'resizable']/div[3]")); Actions act
		 * = new Actions(driver); act.dragAndDropBy(resize, 400, 400).perform();
		 */

		// droppable
		/*
		 * driver.get("https://jqueryui.com/resources/demos/droppable/default.html");
		 * WebElement dragable =
		 * driver.findElement(By.xpath("//div[@id = 'draggable']")); WebElement
		 * droppable = driver.findElement(By.xpath("//div[@id = 'droppable']")); new
		 * Actions(driver).dragAndDrop(dragable, droppable).perform();
		 */

		//
		/*
		 * driver.get("http://deluxe-menu.com/popup-mode-sample.html"); WebElement img =
		 * driver.findElement(By.xpath(
		 * "/html/body/div/table/tbody/tr/td[2]/div[2]/table[1]/tbody/tr/td[3]/p[2]/img"
		 * )); new Actions(driver).contextClick().perform();
		 */

		// select can be used for static dropdown
		/*
		 * Select s = new Select(driver.findElement(By.name("selectionField")));
		 * s.selectByIndex(1);
		 */

		/*
		 * driver.findElement(By.xpath("//input[@type= 'button']")).click();
		 * System.out.println(driver.switchTo().alert().getText());
		 * driver.switchTo().alert().accept();
		 */

		// To open the link innew tab if behavior is not that chord means combination
		// String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);

		/*
		 * driver.findElement(By.linkText("Blogs")).click();
		 * 
		 * Iterator<String> itr = driver.getWindowHandles().iterator();
		 * 
		 * //link.sendKeys(clickOnLink);
		 * 
		 * //System.out.println(driver.getTitle());
		 * 
		 * String parentid = itr.next(); String childid = itr.next();
		 * 
		 * driver.switchTo().window(childid); System.out.println(driver.getTitle());
		 * driver.switchTo().window(parentid); System.out.println(driver.getTitle());
		 */

		// implicit waits for each element presense for maximum 2sec. if found
		// immediately no wait
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// fullscreenshot only works with firefox
		// Date d = new Date();
		// String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		/*
		 * File fullShot = ((HasFullPageScreenshot)
		 * driver).getFullPageScreenshotAs(OutputType.FILE);
		 * FileUtils.copyFile(fullShot, new File("./screenshots"));
		 */
		/*
		 * driver.get("https://google.com"); driver.manage().window().maximize();
		 * WebElement ele = driver.findElement(By.xpath("//img[@id='hplogo']")); File
		 * screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 * BufferedImage fullImg = ImageIO.read(screenshot); Point point =
		 * ele.getLocation(); int eleWidth = ele.getSize().getWidth(); int eleHeight =
		 * ele.getSize().getHeight(); BufferedImage eleScreenshot =
		 * fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		 * ImageIO.write(eleScreenshot, "png", screenshot); File screenshotLocation =
		 * new File("./screenshot/"+fileName); FileUtils.copyFile(screenshot,
		 * screenshotLocation);
		 */

		// Ashot
		/*
		 * driver.get("http://www.way2automation.com/");
		 * driver.manage().window().maximize(); WebElement ele =
		 * driver.findElement(By.xpath("//div[6]/div[2]/div/a")); Screenshot screenshot
		 * = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).
		 * takeScreenshot(driver,ele); ImageIO.write(screenshot.getImage(), "jpg", new
		 * File(".\\screenshot\\ashotimgelement.jpg"));
		 */

		/*
		 * List<WebElement> rows =
		 * driver.findElements(By.xpath("//div[5]/table/tbody/tr"));
		 * 
		 * for(int i =1; i< rows.size(); i++) { List<WebElement> cols =
		 * driver.findElement(By.xpath("//div[5]/table/tbody/tr["+i+"]")).findElements(
		 * By.tagName("td")); for(WebElement col:cols) {
		 * System.out.print(col.getText()+"\t"); } System.out.print("\n"); }
		 */

		// explicit waits for a specific element presense
		/*
		 * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		 * 
		 * List<WebElement> checkboxes =
		 * driver.findElement(By.xpath("//td/div[4]")).findElements(By.tagName("input"))
		 * ;
		 * 
		 * for( WebElement checkbox : checkboxes) {
		 * wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click(); }
		 */

		// shorten the xpath by finding if any similar ele contains in that path using
		// console $s("//rest of the xpath")
		// driver.findElement(By.xpath("//div[4]/input[1]")).click();

		// selection can go wrong using sendkeys so use select instead
		// driver.findElement(By.name("country")).sendKeys("Germany");

		// WebElement dropdownMenu = driver.findElement(By.id("searchLanguage"));
		// Select selectOption = new Select(dropdownMenu);

		// for xpath id = url with - insted of .(leave /html/body) + path

		/*
		 * try { WebElement buttonLang =
		 * driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/div[4]/button/span"
		 * )); buttonLang.click(); System.out.println("Language button clicked"); }
		 * catch(Throwable t) { System.out.println("element not present"); }
		 * 
		 * WebElement block =
		 * driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/div[7]/div[3]"));
		 * List<WebElement> links = block.findElements(By.tagName("a"));
		 * System.out.println("Links count: "+links.size()); //List<WebElement> options
		 * = driver.findElement(By.xpath(
		 * "/html/body/div[4]/div/div/div/div/div/form/div[2]")).findElements(with(By.
		 * tagName("a"))); for(WebElement option:links) { //getText() etc
		 * System.out.println(option.getAttribute("href")); }
		 */
		// selectByVisibleText etc
		// selectOption.selectByValue("hi");

		// implicit waits for each element presense for maximum 2sec. if found
		// immediately no wait
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		/*
		 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		 * .withTimeout(Duration.ofSeconds(10)) .pollingEvery(Duration.ofSeconds(2))
		 * .ignoring(NoSuchElementException.class); driver.manage().window().maximize();
		 */

		// WebElement userName = driver.findElement(By.id("identifierId"));
		// userName.sendKeys("p.mehra86@gmail.com");

		// similarly below, near, rightof,leftof relative locators
		// keyworkd RelativeLocators can be removed, it will work
		/*
		 * WebElement elementAbove =
		 * driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.id(
		 * "user_password"))); elementAbove.sendKeys("portfolioanimate@gmail.com"); File
		 * aboveScrn = elementAbove.getScreenshotAs(OutputType.FILE);
		 * FileUtils.copyFile(aboveScrn, new File("./screenshot/above.jpg"));
		 */

		// List<WebElement> labels =
		// driver.findElements(with(By.tagName("input")).above(By.xpath("//*[@id=\"user_login\"]")));

		/*
		 * List<WebElement> inputs = driver.findElements(By.tagName("input"));
		 * 
		 * for(WebElement label:inputs) { String test = label.getText();
		 * System.out.println(test); }
		 */

		// driver.findElement(By.xpath("//*[@id=\"identifierNext\"]")).click();

		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"submitLogin\"]"))).click();
		// driver.findElement(By.xpath("//*[@id=\"submitLogin\"]")).click();

		// only close the current window
		// Thread.sleep(2000);
		// driver.close();

		// close all the windows of that session
		// driver.quit();

	}

}
