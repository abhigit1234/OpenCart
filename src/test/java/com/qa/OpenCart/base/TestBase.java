package com.qa.OpenCart.base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.OpenCart.utilities.TestUtil;

public class TestBase {
	public static WebDriver driver;
	public static Properties p;
	public static FileInputStream fis;
	public static ExtentReports extent;
	public static ExtentTest extenttest;
	public static ExtentSparkReporter spark;
	public static String repName;
	public static String timeStamp;
	public static Logger log ;

	@Parameters("browser")
	@BeforeTest
	public void initialiseBrowser(ITestContext context,String browser) throws Exception {
log = LogManager.getLogger();
		try {
			p = new Properties();
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
			p.load(fis);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		//String browser = p.getProperty("browser");
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("invalid browser initialised");
			break;

		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.pageLoadTimeOut));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.implicitWait));
		log.info("browser launched");
		driver.get(p.getProperty("baseurl"));
		extenttest = extent.createTest(context.getName());
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String device = cap.getBrowserName() + " "
				+ cap.getBrowserVersion().substring(0, cap.getBrowserVersion().indexOf("."));
		String author = context.getCurrentXmlTest().getParameter("author");
		extenttest.assignAuthor(author);
		extenttest.assignDevice(device);
	}

	@AfterTest
	public void tearDown() {
		log.info("browser closed");
		driver.close();
	}

	@BeforeSuite
	public void initialiseReport() {
		timeStamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
		repName = "TestReport"+timeStamp+".html";
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(".//ExtentReports//"+repName);
		extent.attachReporter(spark);
		extent.setSystemInfo("os name", System.getProperty("os.name"));
		extent.setSystemInfo("os version", System.getProperty("os.version"));
		extent.setSystemInfo("java version", System.getProperty("java.version"));
		extent.setSystemInfo("java name", System.getProperty("java.name"));
		extent.setSystemInfo("user name", System.getProperty("user.name"));
		extent.setSystemInfo("user country", System.getProperty("user.country"));
		spark.config().setReportName("abhilash babu");
		spark.config().setTheme(Theme.DARK);
		spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
		spark.config().setDocumentTitle("OpenCart demo");

	}

	@AfterSuite
	public void generateReports() throws IOException {
		extent.flush();                        
		Desktop.getDesktop().browse(new File(".//ExtentReports//"+repName).toURI());
	}

	@BeforeMethod
	public void groups(Method m) {
		extenttest.assignCategory(m.getAnnotation(Test.class).groups());
	}

	@AfterMethod
	public void checkStatus(Method m, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String capture = capture(result.getTestContext().getName() + " " + result.getMethod().getMethodName() + ".png");
			extenttest.addScreenCaptureFromPath(capture);
			extenttest.info(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extenttest.fail(m.getName() + " is passed ");
		}
	}

	public String capture(String fileName) {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		String date = ldt.format(dtf);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".//ScreenShots//"+date+ fileName);
		try {
			FileUtils.copyFile(source, dest);
		} catch (Exception e) {
			System.out.println(e);
		
		}	System.out.println("screen saved successfully");
		return dest.getAbsolutePath();
		
	}
}