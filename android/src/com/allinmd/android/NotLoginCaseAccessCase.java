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
import com.allinmd.po.androiddriver.ListPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 未登录用户病例终端页权限判断，验证登录后返回来源页
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class NotLoginCaseAccessCase {
	private static AndroidDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new LoginPageElements(driver);
    	new IndexPageElements(driver);
    	new ListPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 验证未登录病例终端页权限判断
	 */
	@Test (priority = 1)
	public void notLoginCaseAccess() {
		WebElement exper_btn = driver.findElement(By.id(LoginPageElements.EXPER_BTN));
		exper_btn.click();
		
		IndexPageElements.knowMessage();
		
		WebElement index_case_btn = driver.findElement(By.id(IndexPageElements.INDEX_CASE_BTN));
		index_case_btn.click();
		
		WebElement first_resource = driver.findElement(By.id(ListPageElements.FIRST_RESOURCE));
		first_resource.click();
		
		Assertion.assertEquals(true, LoginPageElements.allmdBtnIsExist(), "没有跳转登录页面！");
	}
	
	/**
	 * 登录成功是否返回来源页
	 * @param username
	 * @param password
	 */
	@Test (priority = 2)
	@Parameters({"LOGIN_EMAIL_USER", "GLOBAL_PASSWORD"})
	private void loginAllmd(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		Assertion.assertEquals(true, ListPageElements.assertListBar("病例"), "未登录 or 登录后未返回列表！");
	}
}
