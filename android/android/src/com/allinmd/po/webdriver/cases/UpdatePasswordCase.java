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
import com.allinmd.po.webdriver.page.LoginWebElements;
import com.allinmd.po.webdriver.page.LogoutWebElements;
import com.allinmd.po.webdriver.page.MainMenuWebElements;
import com.allinmd.po.webdriver.page.PerCenterWebElements;
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
	private static WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = DriverList.webDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(driver, PerCenterWebElements.class);
		PageFactory.initElements(driver, LoginWebElements.class);
		PageFactory.initElements(driver, LogoutWebElements.class);
		PageFactory.initElements(driver, IndexWebElements.class);
		PageFactory.initElements(driver, MainMenuWebElements.class);
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
		LoginWebElements.allinLogin(username, password);
		MainMenuWebElements.goPersonal();
		Assertion.assertEquals(true, PerCenterWebElements.securityBtnIsExist(), "\"账户安全\"按钮未找到，页面不符或元素改变，请检查！");
		PerCenterWebElements.updatePassword(password, newPassword);
		Assertion.assertEquals(true, PerCenterWebElements.assertGoToSecurity(), "页面没有正常跳转，密码或修改失败！");
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
		PerCenterWebElements.security_bar_name.click();
		LogoutWebElements.allinLogout();
		LoginWebElements.allinLogin(username, newPassword);
		
		MainMenuWebElements.goPersonal();
		PerCenterWebElements.updatePassword(newPassword, password);
		Assertion.assertEquals(true, PerCenterWebElements.assertGoToSecurity(), "页面没有正常跳转，密码或修改失败！");

	}
}
