package com.automation.pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.automation.utilities.BaseClass;
import com.automation.utilities.PropertyLoader;

public class FacebookPage extends BaseClass {
		
		private final static String CONFIG_FILE_NAME = "/Common.properties";
		private static Properties pageProperties1 = new PropertyLoader(CONFIG_FILE_NAME).load();
		
		private final static String FB_FILE_NAME = "/Locator/FBLocator.properties";
		private static Properties fbProperties = new PropertyLoader(FB_FILE_NAME).load();

		public void logintoFaceBook(String User) throws Throwable {
			System.err.println(User);
			XSSFSheet sh = ExcelData(User);
			browser.get(pageProperties1.getProperty("FB.URL"));
			VerifyAndEnterTextByID(fbProperties.getProperty("Facebook.emailaddress.text"),sh.getRow(0).getCell(1).toString());
			VerifyAndEnterTextByID(fbProperties.getProperty("Facebook.password.text"), sh.getRow(0).getCell(2).toString());
			VerifyAndClickElementByCss(fbProperties.getProperty("Facebook.Submitbutton"));
		}
		
		public void updatestatus() throws InterruptedException {
			
			VerifyAndClickElementByXpath(fbProperties.getProperty("Facebook.statusMessage.text"));
			waitForElementVisible(fbProperties.getProperty("FaceBook.statusMessage2.text"));
			Thread.sleep(5000);
			//VerifyAndClickElementByXpath(fbProperties.getProperty("FaceBook.statusMessage2.text"));
			
		}
		
		public void entermessageforpost(String message) throws InterruptedException
		{
			VerifyEntertextbyJS(fbProperties.getProperty("FaceBook.statusMessage3.text"), message);
			Thread.sleep(5000);
		}
		
		public void verifyandclickonpostmesage() {
			VerifyAndClickElementByXpath(fbProperties.getProperty("Facebook.Post.button"));			
		}
	}