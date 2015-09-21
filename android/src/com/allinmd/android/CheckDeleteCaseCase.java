package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.PublishCaseElements;
import com.allinmd.po.androiddriver.SubscribePageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 个人中心查看发布的病例
 * 删除病例
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class CheckDeleteCaseCase {
	private static AndroidDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new LoginPageElements(driver);
		new PublishCaseElements(driver);
		new SubscribePageElements(driver);
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
	}
	
	/**
	 * 个人中心-发布的病例查看刚发布的病例
	 * @param age
	 */
	@Test (priority = 2)
	@Parameters({"pub_case_title"})
	public void checkCase(String case_title) {	
		WebElement eReleaseCaseTitle = PublishCaseElements.checkCase();
		Assertion.assertEquals(case_title, eReleaseCaseTitle.getText(), "发布的话题列表第一个病例不是刚才发布的病例，请检查！");
		eReleaseCaseTitle.click();
	}
	
	/**
	 * 删除病例
	 */
	@Test (priority = 3)
	public void deleteCase() {
		Assertion.assertEquals(true, PublishCaseElements.deleteCase(), "病例未删除成功，请检查");
	}
}
