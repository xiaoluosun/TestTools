package com.allinmd.po.webdriver.cases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.webdriver.page.AuthWebElements;
import com.allinmd.po.webdriver.page.IndexWebElements;
import com.allinmd.po.webdriver.page.ListWebElements;
import com.allinmd.po.webdriver.page.LoginWebElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 未认证用户病例终端页权限判断，验证认证后返回来源页。
 * 依赖RegEmailCase
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class NotAuthCaseAccessCase {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.webDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(driver, LoginWebElements.class);
		PageFactory.initElements(driver, IndexWebElements.class);
		PageFactory.initElements(driver, ListWebElements.class);
		PageFactory.initElements(driver, AuthWebElements.class);
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
	@Parameters({"GLOBAL_PASSWORD"})
	public void notAuthCaseAccess(String password) {
		LoginWebElements.allinLogin(RegEmailCase.username, password);
		AuthWebElements.skip_auth.click();
		IndexWebElements.knowMessage();
		IndexWebElements.index_case_btn.click();
		ListWebElements.first_resource.click();
		Assertion.assertEquals(true, AuthWebElements.skipAuthIsExist(), "没有跳转认证页面！");
	}
	
	/**
	 * 权限判断后认证成功是否返回来源页
	 * @param username
	 * @param password
	 */
	@Test (priority = 2)
	private void authAllmd() {
		AuthWebElements.doctorCer();
		AuthWebElements.selectMajor();
		Assertion.assertEquals(true, ListWebElements.assertListBar("病例"), "未认证 or 认证后未返回列表！");
	}
}
