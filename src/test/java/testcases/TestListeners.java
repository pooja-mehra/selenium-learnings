package testcases;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import io.github.bonigarcia.wdm.WebDriverManager;


public class TestListeners {
		/**
		 * @param args
		 * @throws InterruptedException 
		 */
		static String browser = "chrome";
		static WebDriver webdriver;
		
		public static void main(String[] args) throws InterruptedException {

			switch(browser) {
			case "chrome":
				ChromeOptions opt = new ChromeOptions();
				//To disable te info bar for automation software
				opt.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
				//To accept insecure cert
				//opt.setAcceptInsecureCerts(true);
				//opt.addArguments("window-size=1400,1000");
				//opt.setExperimentalOption("mobileEmulation", mobileEm);
				WebDriverManager.chromedriver().setup();
				webdriver = new ChromeDriver(opt);
				break;
			
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				webdriver = new FirefoxDriver();
				break;
				
			case "edge":
				WebDriverManager.edgedriver().setup();
				webdriver = new EdgeDriver();
				break;
				
			case "opera":
				WebDriverManager.operadriver().setup();
				webdriver = new OperaDriver();
				break;
				
			case "ie":
				WebDriverManager.iedriver().setup();
				webdriver = new InternetExplorerDriver();
				break;
				
			}
			
			EventFiringWebDriver driver = new EventFiringWebDriver(webdriver);
			
			Weblisteners listner = new Weblisteners();
			
			driver.register(listner);
			
			

			driver.navigate().to("http://google.com");
			
			
			
			
			driver.findElement(By.xpath("//body/div[1]/div[1]/a[1]")).click();
			
			Thread.sleep(2000L);
			
			driver.navigate().back();
			
			driver.findElement(By.xpath("//body/div[1]/div[1]/a[2]")).click();
			
			Thread.sleep(2000L);
			
			driver.navigate().back();
			
			Thread.sleep(2000L);
			
			driver.navigate().forward();
			
			driver.navigate().back();
			
			
			
		}

	}
