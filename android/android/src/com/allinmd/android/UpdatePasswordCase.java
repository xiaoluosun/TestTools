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
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.LogoutPageElements;
import com.allinmd.po.androiddriver.MainMenuPageElements;
import com.allinmd.po.androiddriver.PerCenterPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 修改密码并验证是否修改
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class UpdatePasswordCase {
	private static AndroidDriver driver;

	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new PerCenterPageElements(driver);
		new LoginPageElements(driver);
		new LogoutPageElements(driver);
		new IndexPageElements(driver);
		new MainMenuPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 修改密码
	 * @param username
	 * @param password
	 * @param newPassword
	 */
	@Test (priority = 1)
	@Parameters({"username", "GLOBAL_PASSWORD", "new_password"})
	public void updatePassword(String username, String password, String newPassword) {
		LoginPageElements.allinLogin(username, password);
		MainMenuPageElements.goPersonal();
		Assertion.assertEquals(true, PerCenterPageElements.securityBtnIsExist(), "\"账户安全\"按钮未找到，页面不符或元素改变，请检查！");
		PerCenterPageElements.updatePassword(password, newPassword);
		Assertion.assertEquals(true, PerCenterPageElements.assertGoToSecurity(), "页面没有正常跳转，密码或修改失败！");
	}
	
	/**
	 * 登录以验证新密码是否生效，最后还原密码
	 * @param username
	 * @param password
	 * @param newPassword
	 */
	@Test (priority = 2)
	@Parameters({"username", "GLOBAL_PASSWORD", "new_password"})
	public void assertPassword(String username, String password, String newPassword) {
		WebElement security_bar_name = driver.findElement(By.id(PerCenterPageElements.SECURITY_BAR_NAME));
		security_bar_name.click();
		
		LogoutPageElements.allinLogout();
		LoginPageElements.allinLogin(username, newPassword);
		
		MainMenuPageElements.goPersonal();
		PerCenterPageElements.updatePassword(newPassword, password);
		Assertion.assertEquals(true, PerCenterPageElements.assertGoToSecurity(), "页面没有正常跳转，密码或修改失败！");

	}
}
