package com.allinmd.po.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

@Listeners({com.runtime.listener.AssertionListener.class})
public class LoginWebElements {
	
	// 唯医会员登录按钮
	@FindBy(id = LoginPageElements.LOGIN_ALLINMD_BTN)
	public static WebElement login_allinmd_btn;
	
	// 体验一下按钮
	@FindBy(id = LoginPageElements.EXPER_BTN)
	public static WebElement exper_btn;

	// 唯医登录输入邮箱
	@FindBy(id= LoginPageElements.LOGIN_ALLINMD_USER)
	public static WebElement login_allinmd_user;
	
	// 唯医登录输入密码
	@FindBy(id = LoginPageElements.LOGIN_ALLINMD_PW)
	public static WebElement login_allinmd_pw;
	
	// CAOS会员登录按钮
	@FindBy(id = LoginPageElements.LOGON_CAOS_BTN)
	public static WebElement login_caos_btn;
	
	// CAOS输入邮箱
	@FindBy(id = LoginPageElements.LOGIN_CAOS_USER)
	public static WebElement login_caos_user;
	
	// CAOS输入密码
	@FindBy(id = LoginPageElements.LOGIN_CAOS_PW)
	public static WebElement login_caos_pw;
	
	// 登录错误信息
	@FindBy(id = LoginPageElements.LOGIN_ERRORMESSAGE)	
	public static WebElement login_errormessage;
	
	private static WebDriver driver;
    
    public LoginWebElements(WebDriver driver){  
    	LoginWebElements.driver = driver; 
    	PageFactory.initElements(driver, IndexWebElements.class);
    }

    /**
     * 判断"唯医会员登录"按钮是否存在
     * @return 
     */
    public static boolean allmdBtnIsExist() {
    	return Utils.isElementExist(driver, By.id(LoginPageElements.LOGIN_ALLINMD_BTN));
    }
    
    /**
     * 唯医登录
     * @param username
     * @param password
     */
    public static void allinLogin(String username, String password) {
    	login_allinmd_btn.click();
    	login_allinmd_user.sendKeys(username);
    	login_allinmd_pw.sendKeys(password);
    	login_allinmd_btn.click();	
    	
    	IndexWebElements.knowMessage();
    	Assertion.assertEquals(true, IndexWebElements.assertGoToIndex(), "未跳转到首页，是否登录失败，请检查！");
    }
    
    /**
     * CAOS登录
     * @param username
     * @param password
     */
    public static void caosLogin(String username, String password) {
    	login_caos_btn.click();
    	login_caos_user.sendKeys(username);
    	login_caos_pw.sendKeys(password);
    	login_caos_btn.click();
    	
    	IndexWebElements.knowMessage();
    	Assertion.assertEquals(true, IndexWebElements.assertGoToIndex(), "未跳转到首页，是否登录失败，请检查！");
    }
}
