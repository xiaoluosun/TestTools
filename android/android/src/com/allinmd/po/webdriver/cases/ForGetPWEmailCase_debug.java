package com.allinmd.po.webdriver.cases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.webdriver.page.ForgetPWWebElements;
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
public class ForGetPWEmailCase_debug {
	private static WebDriver driver;
	private static WebDriver browser;
	private static String url;
	
	public ForGetPWEmailCase_debug() {
		new GetResetPasswd();
	}
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.webDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(driver, ForgetPWWebElements.class);

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
	@Parameters({"username"})
	public void emailFindPW(String username) {
		Assertion.assertEquals(true, ForgetPWWebElements.forgetEmail(username));
		tearDown();
		url = GetResetPasswd.getEmailUrl(username);
	}
	
	/**
	 * 打开重置密码链接，以修改密码
	 */
	@Test (priority = 2)
	public void setPW() {
		browser = DriverList.browserDriverRun();
		browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		browser.get(url);
	}
}
