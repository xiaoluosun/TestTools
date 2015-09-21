package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.PerCenterPageElements;
import com.runtime.listener.Assertion;

@Listeners({com.runtime.listener.AssertionListener.class})
public class MainMenuPageElements {
	
	// 设置按钮
	public static final String SETTING_BTN = "com.allin.social:id/ll_setting";
		
	// 个人中心按钮
	public static final String PERSONAL_CERTER = "com.allin.social:id/ll_personal_center";
	
	// 发布病例按钮
	public static final String PUBLISH_CASE = "com.allin.social:id/ll_publish_case";
	
	// 发布话题按钮
	public static final String CREATE_TOPIC = "com.allin.social:id/ll_create_topic";
	
	public static AndroidDriver driver;
    
    public MainMenuPageElements(AndroidDriver driver){  
    	MainMenuPageElements.driver = driver; 	
    	new IndexPageElements(driver);
    	new PerCenterPageElements(driver);
    }
    
	/**
	 * 进入我的个人中心
	 */
	public static void goPersonal() {
		WebElement eMainMenu = driver.findElement(By.className(IndexPageElements.MAIN_MENU));
		eMainMenu.click();
		
		WebElement ePerCenter = driver.findElement(By.id(PERSONAL_CERTER));
		ePerCenter.click();
		
		Assertion.assertEquals("我的",driver.findElement(By.id(PerCenterPageElements.PER_BARNAME)).getText(), "进入个人中心失败，请检查！");
	}
}
