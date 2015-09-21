package com.allinmd.po.webdriver.cases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.allinmd.po.webdriver.page.AuthWebElements;
import com.allinmd.po.webdriver.page.ForgetPWWebElements;
import com.allinmd.po.webdriver.page.LoginWebElements;
import com.allinmd.po.webdriver.page.LogoutWebElements;
import com.allinmd.po.webdriver.page.MainMenuWebElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.RandomStr;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 手机找回密码，并验证密码是否修改
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class ForGetPWPhoneCase {
	private static WebDriver driver;
	private static String username;
	private static String password;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.webDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(driver, ForgetPWWebElements.class);
		PageFactory.initElements(driver, LoginWebElements.class);
		PageFactory.initElements(driver, LogoutWebElements.class);
		PageFactory.initElements(driver, AuthWebElements.class);
		PageFactory.initElements(driver, MainMenuWebElements.class);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 手机忘记密码
	 */
	@Test (priority = 1)
	public void phoneFindPW() {
		int ranNum = RandomStr.randomNum(2, 9);
		username = "1770001000" + ranNum;
		password = Integer.toString(RandomStr.randomNum(100000, 999999));
		Assertion.assertEquals(true, ForgetPWWebElements.forgetPhone(username, password), "密码设置失败或跳转错误，请检查！");
	}
	
	/**
	 * 设置密码后，登录验证
	 */
	@Test (priority = 2)
	public void assertPW() {
		LogoutWebElements.allinLogout();
		LoginWebElements.allinLogin(username, password);
		
		Assertion.assertEquals(true, AuthWebElements.skipAuthIsExist(), "登录失败，密码设置失败或网络原因，请检查！");
	}
}
