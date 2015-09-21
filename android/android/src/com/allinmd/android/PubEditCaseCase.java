package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.PublishCaseElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 病例-发布\编辑
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class PubEditCaseCase {
	private static AndroidDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new LoginPageElements(driver);
		new PublishCaseElements(driver);
		new IndexPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	@Test (priority = 1)
	@Parameters({"LOGIN_PHONE_USER", "GLOBAL_PASSWORD"})
	public void allinLogin(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		
		WebElement eMainMenu = driver.findElement(By.className(IndexPageElements.MAIN_MENU));
		eMainMenu.click();
	}
	
	/**
	 * 发布病例
	 * @param case_title
	 * @param age
	 * @param main_suit
	 * @param case_talk
	 */
	@Test (priority = 2)
	@Parameters({"pub_case_title", "pub_case_age", "pub_main_suit", "pub_case_talk"})
	public void pubCase(String case_title, String age, String main_suit, String case_talk) {	
		String terminalCaseName = PublishCaseElements.pubCase(case_title, age, main_suit, case_talk);
		Assertion.assertEquals(case_title, terminalCaseName, "发布病例后跳转的终端页不符，请检查！");
	}
	
	/**
	 * 编辑病例
	 * @param age
	 */
	@Test (priority = 3)
	@Parameters({"edit_case_age"})
	public void editCase(String age) {	
		String ageYear = PublishCaseElements.editCase(age);
		Assertion.assertEquals(age + "岁", ageYear, "年龄未更新，编辑病例失败，请检查！");
	}
}
