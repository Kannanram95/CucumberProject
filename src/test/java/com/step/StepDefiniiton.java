package com.step;

import static org.junit.Assert.assertTrue;

import org.base.BaseClass;
import org.pojo.FacebookloginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefiniiton extends BaseClass{
	
	@Given("User should hit the facebook url in GC browser")
	public void user_should_hit_the_facebook_url_in_GC_browser() {
	    tolaunchChromebrowser();
	    tolaunchUrl("https://www.facebook.com/");
	}

	@When("user should enter the username and password")
	public void user_should_enter_the_username_and_password() {
	   FacebookloginPage fb = new FacebookloginPage();
	   toFillTxt(fb.getTxtemail(),"kannanflame@gmail.com");
	   toFillTxt(fb.getTxtpassword(), "devprasadh");
	}
 
	@When("user should click the login button")
	public void user_should_click_the_login_button() {
		FacebookloginPage fb = new FacebookloginPage();
		toClick(fb.getBtnlogin());
	}

	@When("check whether user is directed to user page")
	public void check_whether_user_is_directed_to_user_page() {
	    assertTrue(driver.getCurrentUrl()!="https://www.facebook.com/");
	}

	@Then("user should quit browser")
	public void user_should_quit_browser() {
	    toQuit();
	}
}
