package com.test.flipkart;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.flipkart.utilities.GeneralMethods;
import com.page.classes.HomePage;
import com.page.classes.ItemPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


public class OrderItem {
	DesiredCapabilities capabilities;
	AppiumDriver<MobileElement> driver;
	
	ExtentReports report;
	ExtentTest test;
	String startTimeString = null;
	GeneralMethods gm;
	HomePage hp;
	ItemPage ip;
	
	@BeforeClass
	public void beforeclass() {
		try {
			//Give IP of the internet
			// Set Properties
			System.out.println("Test Begis");
			gm = new GeneralMethods();
			long starttime = System.currentTimeMillis();
			startTimeString = String.valueOf(starttime);
			report = new ExtentReports("src/test/resources/reports"+"/FlipkartTestMobile" + startTimeString + "/" + "ExtentReport.html");
			
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "phone");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.flipkart.android");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.flipkart.android.SplashActivity");
			capabilities.setCapability(MobileCapabilityType.UDID, "149e059d");
			
			//Give IP of the internet
			driver = new AndroidDriver<>(new URL("http://192.168.43.228:4723/wd/hub"), capabilities);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}

	}

	@AfterClass
	public void afterclass() {
		try {
			report.flush();
			driver.quit();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
		}

	}

	@Test
	public void openFlipkart() {
		try {
			test = report.startTest("Open Flipkart");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			test.log(LogStatus.PASS, "Opened Flipkart");
			test.log(LogStatus.PASS, "Snapshot beside", test.addScreenCapture(
					gm.captureScreenShot("openFlipkart", "FlipkartTestMobile" + startTimeString,driver)));
		} catch(Exception e) {
			test.log(LogStatus.FAIL, "Could not open Flipkart",e);
		} finally {
			report.endTest(test);
		}
	}
	
	@Test(dependsOnMethods = { "openFlipkart" })
	public void searchItem() {
		try {
			test = report.startTest("Search Item");
			String item = "Android";
			int itemNumber = 1;
			try {
				driver.findElement(
						By.xpath("//android.widget.ImageView[@resource-id='com.flipkart.android:id/custom_back_icon']"))
						.click();
				test.log(LogStatus.PASS, "Login window opened");
				test.log(LogStatus.PASS, "Snapshot beside", test.addScreenCapture(
						gm.captureScreenShot("searchItem", "FlipkartTestMobile" + startTimeString,driver)));
			} catch (Exception e) {

			} finally {
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id='com.flipkart.android:id/search_widget_textbox']"))
						.click();
				test.log(LogStatus.PASS,"Searchbox opened");
				test.log(LogStatus.PASS, "Snapshot beside", test.addScreenCapture(
						gm.captureScreenShot("searchItem", "FlipkartTestMobile" + startTimeString,driver)));
				driver.findElement(By.xpath(
						"//android.widget.EditText[@resource-id='com.flipkart.android:id/search_autoCompleteTextView']"))
						.sendKeys(item);
				test.log(LogStatus.PASS,"IPhone "+"is written in searchbox");
				test.log(LogStatus.PASS, "Snapshot beside", test.addScreenCapture(
						gm.captureScreenShot("searchItem", "FlipkartTestMobile" + startTimeString,driver)));
				// driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.flipkart.android:id/search_autoCompleteTextView']")).submit();
				List<MobileElement> suggestions = driver.findElements(By.xpath(
						"//androidx.recyclerview.widget.RecyclerView[@resource-id='com.flipkart.android:id/auto_suggest_recycler_view']/android.widget.RelativeLayout"));
				suggestions.get(0).click();
				test.log(LogStatus.PASS,"1st suggestion is clicked");
				test.log(LogStatus.PASS, "Snapshot beside", test.addScreenCapture(
						gm.captureScreenShot("searchItem", "FlipkartTestMobile" + startTimeString,driver)));
			}
		} catch(Exception e) {
			test.log(LogStatus.FAIL, "Could not open Flipkart",e);
		} finally {
			report.endTest(test);
		}
	}
	
	@Test(dependsOnMethods = { "searchItem" })
	public void grantLocation() {
		try {
			test = report.startTest("Grant Location");
			driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.flipkart.android:id/allow_button']"))
					.click();
			test.log(LogStatus.PASS,"Location request acknowledged");
			test.log(LogStatus.PASS, "Snapshot beside", test.addScreenCapture(
					gm.captureScreenShot("searchItem", "FlipkartTestMobile" + startTimeString,driver)));
			driver.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();
			test.log(LogStatus.PASS,"Location granted");
			test.log(LogStatus.PASS, "Snapshot beside", test.addScreenCapture(
					gm.captureScreenShot("searchItem", "FlipkartTestMobile" + startTimeString,driver)));
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e);
			try {
				test.log(LogStatus.PASS, "Snapshot beside", test.addScreenCapture(
						gm.captureScreenShot("searchItem", "FlipkartTestMobile" + startTimeString,driver)));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Assert.assertTrue(false, "Did not grant location");
			
		} finally {
			report.endTest(test);
		}

	}
	
	

	
}
