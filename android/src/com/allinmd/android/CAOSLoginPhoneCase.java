package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.LogoutPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * CAOS手机账号联合登录、登出
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class CAOSLoginPhoneCase {
	private static AndroidDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new LoginPageElements(driver);
		new LogoutPageElements(driver);
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
		Assertion.assertEquals(true, LoginPageElements.allmdBtnIsExist(), "\"唯医会员登录\"按钮未找到，页面不符或元素改变，请检查！");
    }
	
	/**
	 * caos手机账户登录唯医
	 */
	@Test (priority = 2)
	@Parameters({"caos_username", "GLOBAL_PASSWORD"})
	public void loginPhone(String username, String GLOBAL_PASSWORD) {
		LoginPageElements.caosLogin(username, GLOBAL_PASSWORD);
	}
	
	/**
	 * 登出
	 */
	@Test (priority = 3)
	public void logoutEmail() {
		LogoutPageElements.allinLogout();
		Assertion.assertEquals(true, LoginPageElements.allmdBtnIsExist());
	}
}
