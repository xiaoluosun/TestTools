package com.allinmd.po.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.allinmd.po.androiddriver.LogoutPageElements;

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
	
	@SuppressWarnings("unused")
	private WebDriver driver;
    
    public LogoutWebElements(WebDriver driver){  
    	this.driver = driver; 
    	PageFactory.initElements(driver, MainMenuWebElements.class);
    }   
    
    public static void allinLogout() {	
    	MainMenuWebElements.main_menu.click();	
    	MainMenuWebElements.setting_btn.click();
    	logout_btn.click();
    	logout_confirm_btn.click();
    }
}
