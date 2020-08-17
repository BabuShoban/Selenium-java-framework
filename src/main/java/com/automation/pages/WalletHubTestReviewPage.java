package com.automation.pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.ElementClickInterceptedException;

import com.automation.utilities.BaseClass;
import com.automation.utilities.PropertyLoader;

public class WalletHubTestReviewPage extends BaseClass {

	private final static String CONFIG_FILE_NAME = "/Common.properties";
	private static Properties pageProperties = new PropertyLoader(CONFIG_FILE_NAME).load();

	private final static String CONFIG_FILE_NAME1 = "/Locator/WalletHubLocator.properties";
	private static Properties pageProperties1 = new PropertyLoader(CONFIG_FILE_NAME1).load();

	String Reviewmesage = pageProperties.getProperty("Common.reviewmessage");

	// login to wallethub using by geting credentials from Excel sheet
	public void loginWalletHub(String user) throws Throwable {
		XSSFSheet sh = ExcelData(user);
		browser.get(pageProperties.getProperty("Common.URL"));
		VerifyAndClickElementByXpath(pageProperties1.getProperty("WalletHub.Login.link"));
		VerifyAndEnterText(pageProperties1.getProperty("WalletHub.Username.text"), sh.getRow(1).getCell(1).toString());
		VerifyAndEnterText(pageProperties1.getProperty("WalletHub.Password.text"), sh.getRow(1).getCell(2).toString());
		VerifyAndClickElementByXpath(pageProperties1.getProperty("WalletHub.Login.button"));
	}

	// To validate and hover the review stars
	public void clickonStar() throws Exception {
		Wait();
		browser.navigate().to(pageProperties.getProperty("Common.secondUrl"));
		scrollDownPage();
		Thread.sleep(1000);
		for (int i = 1; i < 5; i++) {

			VerifyAndHoverElementByCss(pageProperties1.getProperty("WalletHub.RatingStar.FourthStart").replaceAll("num", "" + i));
			if (i == 5) {
				VerifyAndClickElementByCss(
						pageProperties1.getProperty("WalletHub.RatingStar.FourthStart").replaceAll("num", "" + i));
			}
		}
	}

	// Select the 5 start from review starts setion
	public void SelectStart() throws Exception {
		try {
			System.err.println(pageProperties1.getProperty("WalletHub.RatingStar.FourthStart").replaceAll("num", "5"));
			VerifyAndClickElementByCss(
					pageProperties1.getProperty("WalletHub.RatingStar.FourthStart").replaceAll("num", "5"));
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		}

	}

	// To Complete the review page
	public void selectDropdown() throws Exception {

		VerifyAndClickElementByCss(pageProperties1.getProperty("WalletHub.Policy.dropdownlist"));
		VerifyAndClickElementByXpath(pageProperties1.getProperty("WalletHub.Policy.Dropdown"));
		VerifyAndEnterTextByCss(pageProperties1.getProperty("WalletHub.PolicyReview.text"), Reviewmesage);
		VerifyAndClickElementByCss(pageProperties1.getProperty("WalletHub.Submit.Button"));

	}

	// To Validate Confirmation Page
	public void VerifyConfirmationPage() throws Exception {

		waitForElementVisible(pageProperties1.getProperty("WalletHub.review.header"));
		VerifyTextByXpath(pageProperties1.getProperty("WalletHub.review.header"), "Your review has been posted.");
		VerifyTextByCss(pageProperties1.getProperty("WalletHub.ConfirmationPage.text"), Reviewmesage);
		VerifyAndClickElementByCss(pageProperties1.getProperty("WalletHub.CoutinueButton"));
	}

	// Verify review comment which was submitted
	public void verifyReviewComments() throws Throwable {
		Wait();
		browser.navigate().to(pageProperties.getProperty("Common.profileReview"));
		waitForElementVisible(pageProperties1.getProperty("WalletHub.tab.activity"));
		VerifyAndClickElementByXpath(pageProperties1.getProperty("WalletHub.tab.activity"));
		VerifyAndClickElementByXpath(pageProperties1.getProperty("WalletHub.tab.reviews"));
		VerifyTextByXpath(pageProperties1.getProperty("WalletHub.review.content"), Reviewmesage);

	}
}
