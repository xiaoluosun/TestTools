package com.allinmd.po.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.MainMenuPageElements;
import com.runtime.listener.Assertion;

@Listeners({com.runtime.listener.AssertionListener.class})
public class MainMenuWebElements {
	
	// 打开主菜单 className
	@FindBy(className = IndexPageElements.MAIN_MENU)
	public static WebElement main_menu;
	
	// 设置按钮
	@FindBy(id = MainMenuPageElements.SETTING_BTN)
	public static WebElement setting_btn;
		
	// 个人中心按钮
	@FindBy(id = MainMenuPageElements.PERSONAL_CERTER)
	public static WebElement personal_center;
	
	// 发布病例按钮
	@FindBy(id = MainMenuPageElements.PUBLISH_CASE)
	public static WebElement publish_case;
	
	// 发布话题按钮
	@FindBy(id = MainMenuPageElements.CREATE_TOPIC)
	public static WebElement create_topic;
	
	public static WebDriver driver;
    
    public MainMenuWebElements(WebDriver driver){  
    	MainMenuWebElements.driver = driver; 
    	
    	PageFactory.initElements(driver, PerCenterWebElements.class);
    }
    
	/**
	 * 进入个人中心
	 */
	public static void goPersonal() {
		main_menu.click();
		personal_center.click();
		
		Assertion.assertEquals("我的", PerCenterWebElements.per_barName.getText(), "进入个人中心失败，请检查！");
	}
}
