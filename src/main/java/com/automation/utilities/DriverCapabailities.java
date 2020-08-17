package com.automation.utilities;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverCapabailities extends BaseClass {

	private final static String CONFIG_FILE_NAME = "/Common.properties";
	private static Properties pageProperties = new PropertyLoader(CONFIG_FILE_NAME).load();

	public void getWebdriver()
	{
		if(browser==null)
		{
			if(pageProperties.getProperty("Common.browser").equalsIgnoreCase("fireFoxDriver"))
			{

				WebDriverManager.firefoxdriver().proxy(pageProperties.getProperty("Common.Proxy")).setup();
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				dc.setCapability("marionette", true);
				FirefoxOptions foptions =new FirefoxOptions(dc);
				browser =new FirefoxDriver(foptions);
				browser_operations();
			}

			else if(pageProperties.getProperty("Common.browser").equalsIgnoreCase("chromeDriver"))
			{
				//WebDriverManager.chromedriver().proxy(pageProperties.getProperty("Common.Proxy")).setup();
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				browser =new ChromeDriver(options);
				browser_operations();
			}

			else if(pageProperties.getProperty("Common.browser").equalsIgnoreCase("iExplorerDriver"))
			{
				WebDriverManager.iedriver().proxy(pageProperties.getProperty("Common.Proxy")).setup();
				browser =new InternetExplorerDriver();
				browser_operations();
			}
		}
	}

	public void browser_operations()
	{
		browser.manage().window().maximize();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//browser.get(pageProperties.getProperty("Common.URL"));
	}

	public void QuitBrowser()
	{

	/*	try {
			//Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		browser.quit();*/
	}

}
