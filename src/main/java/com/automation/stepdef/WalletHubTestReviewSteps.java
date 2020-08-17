package com.automation.stepdef;

import com.automation.pages.WalletHubTestReviewPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WalletHubTestReviewSteps {
	WalletHubTestReviewPage pages = new WalletHubTestReviewPage();

	@Given("open wallethub application and login with {string}")
	public void login(String user) throws Throwable {
		pages.loginWalletHub(user);
	}

	@When("select review star")
	public void selecting_star_and_verify_color() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		pages.clickonStar();
		pages.SelectStart();
	}

	@Then("select health insurance and submit review comments")
	public void selectDropdown() throws Exception {
		pages.selectDropdown();
		pages.VerifyConfirmationPage();
	}

	@Then("verify submitted review comments")
	public void verifyReviewComments() throws Throwable {
		pages.verifyReviewComments();
	}
}
