package com.allinmd.po.webdriver.cases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.webdriver.page.LoginWebElements;
import com.allinmd.po.webdriver.page.LogoutWebElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * CAOS邮箱账号联合登录、登出
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class CAOSLoginEmailCase {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.webDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(driver, LoginWebElements.class);
		PageFactory.initElements(driver, LogoutWebElements.class);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 验证"唯医会员登录"按钮是否存在以确定页面正常跳转
	 */
	@Test (priority = 1)
	public void assertRegButton() {
		Assertion.assertEquals(true, LoginWebElements.allmdBtnIsExist(), "\"唯医会员登录\"按钮未找到，页面不符或元素改变，请检查！");
    }
	
	/**
	 * caos邮箱账户登录唯医
	 */
	@Test (priority = 2)
	@Parameters({"caos_username", "GLOBAL_PASSWORD"})
	public void loginEmail(String username, String GLOBAL_PASSWORD) {
		LoginWebElements.caosLogin(username, GLOBAL_PASSWORD);
	}
	
	/**
	 * 登出
	 */
	@Test (priority = 3)
	public void logoutEmail() {
		LogoutWebElements.allinLogout();
		Assertion.assertEquals(true, LoginWebElements.allmdBtnIsExist());
	}
}
