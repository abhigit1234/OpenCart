package com.qa.OpenCart.PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.OpenCart.base.TestBase;

public class PO_Register extends TestBase{
	
	@FindBy(id ="logo")
	WebElement logo;
	
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement clk_MyAcc;
	
	@FindBy(xpath = "//li/a[text()='Register']")
	WebElement clk_Reg;
	
	@FindBy(id = "input-firstname")
	WebElement fname;

	@FindBy(id = "input-lastname")
	WebElement lname;
	
	@FindBy(id = "input-email")
	WebElement email;
	
	@FindBy(id = "input-password")
	WebElement pswd;

	@FindBy(xpath =  "//input[@name='agree']")
	WebElement privacyPolicy;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement clkContinue;
	
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement verifyRegSuccess;

	@FindBy(xpath = "//a[text()='Continue']")
	WebElement clkContinueInside;
	
	@FindBy(xpath = "//div/a[text()='Logout']")
	WebElement clkLogout;
	

	@FindBy(xpath = "//a[text()='Continue']")
	WebElement clkContinueEnd;
	
	
	public PO_Register(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomeTitle() {
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}
	
	public boolean verifyLogo() {
		System.out.println(logo.isDisplayed());
		return logo.isDisplayed();
	}
	
	public void clkMyAccount() {
		clk_MyAcc.click();
	}
	public void clkRegister() {
		clk_Reg.click();
	}
	
	
	public void setFirstName(Object fn) {
		fname.sendKeys((CharSequence)fn);
	}
	public void setLastName(Object ln) {
		lname.sendKeys((CharSequence)ln);
	}
	public void setEmail(Object e) {
		email.sendKeys((CharSequence)e);
	}
	public void setPassword(Object psw) {
		pswd.sendKeys((CharSequence)psw);
	}
	public void clkPriPol() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", privacyPolicy);

		;
	}
	public void clkCont() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", clkContinue);

		
	}
	public boolean verifySucess() {
		System.out.println(verifyRegSuccess.isDisplayed());
		return  verifyRegSuccess.isDisplayed(); 
	}
	public void clkContIns() {
		clkContinueInside.click();
	}
	public void Logout() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", clkLogout);
	}
	public void clkConEnd() {
		clkContinueEnd.click();
	}
}
