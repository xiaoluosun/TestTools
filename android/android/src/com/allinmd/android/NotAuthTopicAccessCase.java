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

import com.allinmd.po.androiddriver.AuthPageElements;
import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.ListPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 未认证用户话题终端页权限判断，验证认证后返回来源页。
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class NotAuthTopicAccessCase {
	private static AndroidDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new LoginPageElements(driver);
    	new IndexPageElements(driver);
    	new ListPageElements(driver);
    	new AuthPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 验证未认证话题终端页权限判断
	 */
	@Test (priority = 1)
	@Parameters({"not_auth_user", "GLOBAL_PASSWORD"})
	public void notAuthCaseAccess(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		
		WebElement index_case_btn = driver.findElement(By.id(IndexPageElements.INDEX_TOPIC_BTN));
		index_case_btn.click();
		
		WebElement first_resource = driver.findElement(By.id(ListPageElements.FIRST_RESOURCE));
		first_resource.click();
		
		Assertion.assertEquals(true, AuthPageElements.skipAuthIsExist(), "没有跳转认证页面！");
	}
	
}
