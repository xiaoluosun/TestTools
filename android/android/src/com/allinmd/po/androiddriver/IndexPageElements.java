package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;

import com.allinmd.util.Utils;

public class IndexPageElements {
	
	// 右滑提示
	public static final String KNOW_MESSAGE = "com.allin.social:id/btn_i_kown";
	
	//打开主菜单  className
	public static final String MAIN_MENU = "android.widget.ImageButton";
	
	// 首页bar name
	public static final String INDEX_BAR_NAME = "com.allin.social:id/tv_actionbar_name";
	
	// 首页搜索按钮
	public static final String INDEX_SEARCH_BTN = "com.allin.social:id/ll_search";
	
	// 首页病例按钮
	public static final String INDEX_CASE_BTN = "com.allin.social:id/btn_case";

	// 首页视频按钮
	public static final String INDEX_VIDEO_BTN = "com.allin.social:id/btn_video";
	
	// 首页医师按钮
	public static final String INDEX_USER_BTN = "com.allin.social:id/btn_user";
	
	// 首页话题按钮
	public static final String INDEX_TOPIC_BTN = "com.allin.social:id/btn_topic";

	private static AndroidDriver driver;
	
    public IndexPageElements(AndroidDriver driver){  
    	IndexPageElements.driver = driver; 
    }
    
    /**
     * 判断首页右滑提示
     */
	public static void knowMessage() {
		if (Utils.isElementExist(driver, By.id(KNOW_MESSAGE))) {
			driver.findElement(By.id(KNOW_MESSAGE)).click();	
		}
	}
	
	/**
	 * 验证认证后页面跳转
	 * @return
	 */
	public static boolean assertGoToIndex() {
		return Utils.isElementExist(driver, By.id(IndexPageElements.INDEX_BAR_NAME));
	}	
}
