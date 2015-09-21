package com.allinmd.po.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.LogoutPageElements;
import com.allinmd.util.Utils;

/**
 * 登出Flow
 * @author sun
 *
 */
public class LogoutWebElements {
	
	// 退出登录按钮
	@FindBy(id = LogoutPageElements.LOGOUT_BTN)
	public static WebElement logout_btn;
	
	// 登出确认按钮
	@FindBy(id = LogoutPageElements.LOGOUT_CONFIRM_BTN)
	public static WebElement logout_confirm_btn;
	
	private static WebDriver driver;
    
    public LogoutWebElements(WebDriver driver){  
    	LogoutWebElements.driver = driver; 
    	PageFactory.initElements(driver, MainMenuWebElements.class);
    }

    /**
     * 判断"唯医会员登录"按钮是否存在
     * @return 
     */
    public static boolean allmdBtnIsExist() {
    	return Utils.isElementExist(driver, By.id(LoginPageElements.LOGIN_ALLINMD_BTN));
    }
    
    
    public static void allinLogout() {	
    	MainMenuWebElements.main_menu.click();	
    	MainMenuWebElements.setting_btn.click();
    	logout_btn.click();
    	logout_confirm_btn.click();
    }
}
