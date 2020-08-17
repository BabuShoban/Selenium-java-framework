package com.automation.stepdef;


import com.automation.pages.FacebookPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FaceBookStep {

	FacebookPage fbPage = new FacebookPage();
	
	@Given("open facebook application with {string} user credentials")	
	public void open_facrbook_application_login_with_user_credentials(String user) throws Throwable {
		fbPage.logintoFaceBook(user);
	}

	@When("click on status")
	public void click_on_status() throws Throwable {
		fbPage.updatestatus();
	}

	@Then("post {string} message")
	public void post_message(String message) throws InterruptedException {
		fbPage.entermessageforpost(message);
		fbPage.verifyandclickonpostmesage();
	}

}
