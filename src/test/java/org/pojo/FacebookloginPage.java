package org.pojo;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookloginPage extends BaseClass{
	public FacebookloginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "email")
	private WebElement txtemail;
	
	@FindBy(id = "pass")
	private WebElement txtpassword;
	
	@FindBy(name = "login")
	private WebElement btnlogin;

	public WebElement getTxtemail() {
		return txtemail;
	}

	public WebElement getTxtpassword() {
		return txtpassword;
	}

	public WebElement getBtnlogin() {
		return btnlogin;
	}
	
}
