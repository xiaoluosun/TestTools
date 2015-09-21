package com.allinmd.po.webdriver.cases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.webdriver.page.IndexWebElements;
import com.allinmd.po.webdriver.page.ListWebElements;
import com.allinmd.po.webdriver.page.LoginWebElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 未登录用户话题终端页权限判断，验证登录后返回来源页
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class NotLoginTopicAccessCase {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.webDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(driver, LoginWebElements.class);
		PageFactory.initElements(driver, IndexWebElements.class);
		PageFactory.initElements(driver, ListWebElements.class);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 验证未登录话题终端页权限判断
	 */
	@Test (priority = 1)
	public void notLoginCaseAccess() {
		LoginWebElements.exper_btn.click();
		IndexWebElements.knowMessage();
		IndexWebElements.index_topic_btn.click();
		ListWebElements.first_resource.click();
		Assertion.assertEquals(true, LoginWebElements.allmdBtnIsExist(), "没有跳转登录页面！");
	}
	
	/**
	 * 登录成功是否返回来源页
	 * @param username
	 * @param password
	 */
	@Test (priority = 2)
	@Parameters({"LOGIN_EMAIL_USER", "GLOBAL_PASSWORD"})
	private void loginAllmd(String username, String password) {
		LoginWebElements.allinLogin(username, password);
		Assertion.assertEquals(true, ListWebElements.assertListBar("话题"), "未登录 or 登录后未返回列表！");
	}
}
