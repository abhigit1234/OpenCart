package com.qa.OpenCart.TestCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.OpenCart.PageObject.PO_Register;
import com.qa.OpenCart.base.TestBase;
import com.qa.OpenCart.utilities.ReadCustomerDetails;

public class TC_Register extends TestBase{
	public TC_Register() {
		super();
	}
	
	@Test(dataProviderClass = ReadCustomerDetails.class ,dataProvider = "CustomerDetails" ,groups = {"smoke","sanity","functional","regression","dailyBuild","weeklyBuild"})
	public void register(Object[] s) throws Exception {
		ReadCustomerDetails rc = new ReadCustomerDetails();
		PO_Register po = new PO_Register(driver);
		extenttest.info("user clicked myaccount");
		po.clkMyAccount();
		extenttest.info("user clicked register");
		po.clkRegister();
		//po.verifyHomeTitle();
		//po.verifyLogo();
		extenttest.info("entered firstname");
		po.setFirstName(s[0]);
		extenttest.info("entered lastname");
		po.setLastName(s[1]);
		extenttest.info("entered email");
		String email = ranEmail();
		po.setEmail(email+"@gmail.com");
		extenttest.info("entered password");
		po.setPassword(s[3]);
		extenttest.info("checked and clicked privacy policy");
		po.clkPriPol();
		extenttest.info("clicked on continue");
		po.clkCont();
		extenttest.info("screen verified");
		po.verifySucess();
		extenttest.info("clicked on continue inside login");
		po.clkContIns();
		extenttest.info("clicked on logout");
		po.Logout();
		extenttest.info("clicked on continue at end time");
		po.clkConEnd();
		
	}
	
	public String ranEmail() {
		String email = RandomStringUtils.randomAlphanumeric(10);
		return email;
	}
	
		
}
