package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class BaseClass {

	public static WebDriver browser = null;
	public static WebElement element;
	public static String File_Load = "/Common.properties";
	private static Properties shoban = new PropertyLoader(File_Load).load();

	int index = 0;
	static List<String> items = new ArrayList<String>();

	public void VerifyAndClickElementByXpath(String locator) {

		element = browser.findElement(By.xpath(locator));

		if (element.isDisplayed()) {
			element.click();
		}
	}

	public void VerifyAndClickElementByCss(String locator) throws Exception {

		element = browser.findElement(By.cssSelector(locator));
		if (element.isDisplayed()) {
			element.click();
		} else {
			throw new Exception("ElementnotfoundExpection");
		}

	}

	public void VerifyAndEnterText(String locator, String text) {
		element = browser.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			element.sendKeys(text);
		}
	}
	
	public void VerifyAndEnterTextByCss(String locator, String text) {
		element = browser.findElement(By.cssSelector(locator));
		if (element.isDisplayed()) {
			element.sendKeys(text);
		}
	}
	
	public void VerifyAndEnterTextByID(String locator, String text) {
		element = browser.findElement(By.id(locator));
		if (element.isDisplayed()) {
			element.sendKeys(text);
		}
	}

	public void VerifyAndHoverElementByCss(String locator) {

		element = browser.findElement(By.cssSelector(locator));
		if (element.isEnabled()) {
			Actions act = new Actions(browser);
			act.moveToElement(element).build().perform();

		}
	}

	public void waitForElementVisible(String locator) {

		Wait<WebDriver> wait = new WebDriverWait(browser, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));

	}

	public void scrollDownPage() {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript("arguments[0].scrollIntoView(true);",
				browser.findElement(By.xpath("//review-star[@class='rvs-svg']")));
	}

	public void VerifyTextByXpath(String locator, String expectedtext) {
		element = browser.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			String actualtext = element.getText();
			Assert.assertEquals(actualtext.contains(expectedtext), true);
		}

	}
	
	public void VerifyTextByCss(String locator, String expectedtext) {
		element = browser.findElement(By.cssSelector(locator));
		if (element.isDisplayed()) {
			String actualtext = element.getText();
			Assert.assertEquals(actualtext.contains(expectedtext), true);
		}

	}
	
	public void VerifyEntertextbyJS(String locator,String text)
	{
		element = browser.findElement(By.xpath(locator));
		String js = "arguments[0].setAttribute('value','"+text+"')";
		((JavascriptExecutor) browser).executeScript(js, element);
	}
	
	public void Wait() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public XSSFSheet ExcelData(String user) throws Throwable
	{
		System.err.println("sdadas"+user);

		File path = new File(shoban.getProperty("authenticationData"));
		FileInputStream excel = new FileInputStream(path);

		XSSFWorkbook xs = new XSSFWorkbook(excel);
		XSSFSheet sh = xs.getSheetAt(0);

		for(int i=0; i<=sh.getLastRowNum();i++)
		{

			XSSFCell cel = sh.getRow(i).getCell(0);
			items.add(cel.toString());
		}

		/*try {

			for (int v=0; v<items.size();v++) {
				if(items.get(v).equalsIgnoreCase(user))
				{
					System.out.println("true");
					System.out.println(sh.getRow(v).getCell(1).toString() + "\t" + sh.getRow(v).getCell(2).toString() + "\t" + sh.getRow(v).getCell(3).toString());
					break;

				}
			}
		}

		catch( Exception e)
		{

			throw new Exception("Data not found in excel");
		}*/
		return sh;

	}
}