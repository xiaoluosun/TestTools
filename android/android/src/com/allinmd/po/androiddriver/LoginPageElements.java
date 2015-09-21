package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

@Listeners({com.runtime.listener.AssertionListener.class})
public class LoginPageElements {
	
	// 唯医会员登录按钮
	public static final String LOGIN_ALLINMD_BTN = "com.allin.social:id/btn_login_allmd";
	
	// 体验一下按钮
	public static final String EXPER_BTN = "com.allin.social:id/btn_login_exper";
	
	// 唯医登录输入邮箱
	public static final String LOGIN_ALLINMD_USER = "com.allin.social:id/et_callmdlogin_user";
	
	// 唯医登录输入密码
	public static final String LOGIN_ALLINMD_PW = "com.allin.social:id/et_allmdlogin_pw";
	
	// CAOS会员登录按钮
	public static final String LOGON_CAOS_BTN = "com.allin.social:id/btn_login_caos";
	
	// CAOS输入邮箱
	public static final String LOGIN_CAOS_USER = "com.allin.social:id/et_caoslogin_user";
	
	// CAOS输入密码
	public static final String LOGIN_CAOS_PW = "com.allin.social:id/et_caoslogin_pw";
	
	// 登录错误信息
	public static final String ERROR_MESSAGE = "com.allin.social:id/tv_login_errormessage";	
	
	private static AndroidDriver driver;
	
    public LoginPageElements(AndroidDriver driver){  
    	LoginPageElements.driver = driver; 
    	new IndexPageElements(driver);
    	new AuthPageElements(driver);
    }

    /**
     * 判断"唯医会员登录"按钮是否存在
     * @return 
     */
    public static boolean allmdBtnIsExist() {
    	return Utils.isElementExist(driver, By.id(LOGIN_ALLINMD_BTN));
    }
    
    /**
     * 唯医登录
     * @param username
     * @param password
     */
    public static void allinLogin(String username, String password) {
    	driver.findElement(By.id(LOGIN_ALLINMD_BTN)).click();
    	driver.findElement(By.id(LOGIN_ALLINMD_USER)).sendKeys(username);
    	driver.findElement(By.id(LOGIN_ALLINMD_PW)).sendKeys(password);
    	driver.findElement(By.id(LOGIN_ALLINMD_BTN)).click();
    	
    	Assertion.assertEquals(false, Utils.isElementExist(driver, By.id(ERROR_MESSAGE)), Utils.getText(driver, By.id(ERROR_MESSAGE)));
    	
    	if (AuthPageElements.skipAuthIsExist()) {
    		WebElement skip_auth = driver.findElement(By.id(AuthPageElements.SKIP_AUTH));
    		skip_auth.click();
    	} 	
		IndexPageElements.knowMessage();
		Assertion.assertEquals(true, IndexPageElements.assertGoToIndex(), "未跳转到首页，是否登录失败，请检查！");
    	
    }
    
    /**
     * CAOS登录
     * @param username
     * @param password
     */
    public static void caosLogin(String username, String password) {
    	driver.findElement(By.id(LOGON_CAOS_BTN)).click();
    	driver.findElement(By.id(LOGIN_CAOS_USER)).sendKeys(username);
    	driver.findElement(By.id(LOGIN_CAOS_PW)).sendKeys(password);
    	driver.findElement(By.id(LOGON_CAOS_BTN)).click();
    	
    	Assertion.assertEquals(false, Utils.isElementExist(driver, By.id(ERROR_MESSAGE)), Utils.getText(driver, By.id(ERROR_MESSAGE)));
    	
    	if (AuthPageElements.skipAuthIsExist()) {
    		WebElement skip_auth = driver.findElement(By.id(AuthPageElements.SKIP_AUTH));
    		skip_auth.click();
    	} 	
		IndexPageElements.knowMessage();
		Assertion.assertEquals(true, IndexPageElements.assertGoToIndex(), "未跳转到首页，是否登录失败，请检查！");
    }
	
}
