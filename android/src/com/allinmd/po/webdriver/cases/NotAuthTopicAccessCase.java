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
 * 未认证用户话题终端页权限判断。
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class NotAuthTopicAccessCase {
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
	 * 验证未认证话题终端页权限判断
	 */
	@Test (priority = 1)
	@Parameters({"not_auth_user", "GLOBAL_PASSWORD"})
	public void notAuthCaseAccess(String username, String password) {
		LoginWebElements.allinLogin(username, password);
		AuthWebElements.skip_auth.click();
		IndexWebElements.knowMessage();
		IndexWebElements.index_topic_btn.click();
		ListWebElements.first_resource.click();
		Assertion.assertEquals(true, AuthWebElements.skipAuthIsExist(), "没有跳转认证页面！");
	}
}
