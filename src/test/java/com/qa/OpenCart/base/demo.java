package com.qa.OpenCart.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class demo {

	@Test
	public void log1() {
		System.out.println("log 1 "+Thread.currentThread().threadId());
	}
	@Test
	public void log2() {
		System.out.println("log 2 "+Thread.currentThread().threadId());
	}@Test
	public void log3() {
		System.out.println("log 3 "+Thread.currentThread().threadId());
	}
	
	
	
	/*
	 * @Test(dataProvider = "data") public void login(String[] s) { WebDriver driver
	 * = new ChromeDriver(); driver.get(
	 * "http://localhost/opencart/upload/index.php?route=account/register&language=en-gb"
	 * ); driver.findElement(By.name("firstname")).sendKeys((CharSequence)s[0]);
	 * driver.findElement(By.name("lastname")).sendKeys((CharSequence)s[1]);
	 * driver.close(); }
	 * 
	 * @DataProvider(name="data" ) public String[][] data() { String[][] data = new
	 * String[5][5]; data[0][0] ="abhi"; data[0][1] ="naidu";
	 * 
	 * data[1][0] ="hyd"; data[1][1] ="ind";
	 * 
	 * 
	 * data[2][0] ="hyd"; data[2][1] ="ind";
	 * 
	 * 
	 * data[3][0] ="hyd"; data[3][1] ="ind";
	 * 
	 * 
	 * data[4][0] ="hyd"; data[4][1] ="ind"; return data; }
	 */
}