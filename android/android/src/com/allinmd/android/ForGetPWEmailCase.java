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

import com.allinmd.po.androiddriver.ForgetPWPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.GetResetPasswd;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 邮箱找回密码，并验证密码是否修改
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class ForGetPWEmailCase {
	private static AndroidDriver driver;
	private static AndroidDriver browser;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new ForgetPWPageElements(driver);
		new GetResetPasswd();
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 邮箱找回密码
	 * @param username
	 */
	@Test (priority = 1)
	@Parameters({"LOGIN_EMAIL_USER", "GLOBAL_PASSWORD"})
	public void emailFindPW(String username, String password) {
		boolean flag = ForgetPWPageElements.forgetEmail(username);
	
		Assertion.assertEquals(true, flag, "重置密码邮件发送失败，请检查！");
		
		if (flag) {
			tearDown();
			setPW(username, password);
			
			browser.quit();		
			Utils.sleep(2);
			Utils.setInputMethod();
		}
	}
	
	/**
	 * 打开重置密码链接，以修改密码
	 */
	public void setPW(String username, String password) {
		browser = DriverList.browserDriverRun();
		browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		String url = GetResetPasswd.getEmailUrl(username);
		browser.get(url);
		
		Assertion.assertEquals(true, Utils.isElementExist(browser, By.id(ForgetPWPageElements.EMAIL_PW_INPUT)));
	
//		browser.switchTo().frame("loginForm");
		WebElement email_pw_input = browser.findElement(By.id(ForgetPWPageElements.EMAIL_PW_INPUT));
		email_pw_input.sendKeys(password);
		
		WebElement email_pw_confirm_btn = browser.findElement(By.id(ForgetPWPageElements.EMAIL_PW_CONFIRM_BTN));
		email_pw_confirm_btn.click();
		
		if (Utils.isElementExist(browser, By.className(ForgetPWPageElements.SUCCEED_TEXT))) {
			Assertion.assertEquals("你已经成功修改了密码", Utils.getText(browser, By.className(ForgetPWPageElements.SUCCEED_TEXT)), 
					Utils.getText(browser, By.className(ForgetPWPageElements.SUCCEED_TEXT)));
		} else {
			Assertion.assertEquals(true, false, "密码修改失败，请检查！");
		}
	}
}
