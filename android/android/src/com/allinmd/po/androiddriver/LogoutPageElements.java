package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;

/**
 * 登出Flow
 * @author sun
 *
 */
public class LogoutPageElements {
	
	// 退出登录
	public static final String LOGOUT_BTN = "com.allin.social:id/btn_login_allmd";
	
	// 登出确认按钮
	public static final String LOGOUT_CONFIRM_BTN = "com.allin.social:id/tv_confirm";
	
	private static AndroidDriver driver;
    
    public LogoutPageElements(AndroidDriver driver){  
    	LogoutPageElements.driver = driver; 
    	new MainMenuPageElements(driver);
    	new IndexPageElements(driver);
    }

    /**
     * 右滑拉出主菜单
     */
    /* 
    public static void allinLogout() {
    	Dimension size = driver.manage().window().getSize();
    	driver.swipe(100, size.getHeight() / 2, size.getWidth()-20, size.getHeight() / 2, 100);
    	driver.findElement(By.id(MainMenuPageElements.SETTING_BTN)).click();
    	driver.findElement(By.id(logout_btn)).click();
    	driver.findElement(By.id(logout_confirm_btn)).click();
    }
    */
    
    public static void allinLogout() {
    	driver.findElement(By.className(IndexPageElements.MAIN_MENU)).click();
    	driver.findElement(By.id(MainMenuPageElements.SETTING_BTN)).click();
    	driver.findElement(By.id(LOGOUT_BTN)).click();
    	driver.findElement(By.id(LOGOUT_CONFIRM_BTN)).click();
    }
}
