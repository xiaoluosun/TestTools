package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.AuthPageElements;
import com.allinmd.po.androiddriver.ForgetPWPageElements;
import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.LogoutPageElements;
import com.allinmd.po.androiddriver.MainMenuPageElements;
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
	private static AndroidDriver driver;
	private static String username;
	private static String password;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new ForgetPWPageElements(driver);
		new LoginPageElements(driver);
		new LogoutPageElements(driver);
		new AuthPageElements(driver);
		new MainMenuPageElements(driver);
		new IndexPageElements(driver);
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
		Assertion.assertEquals(true, ForgetPWPageElements.forgetPhone(username, password), "密码设置失败或跳转错误，请检查！");
	}
	
	/**
	 * 设置密码后，登录验证
	 */
	@Test (priority = 2)
	public void assertPW() {
		LogoutPageElements.allinLogout();
		LoginPageElements.allinLogin(username, password);
		
		Assertion.assertEquals(true, IndexPageElements.assertGoToIndex(), "登录失败，密码设置失败或网络原因，请检查！");
	}
}
