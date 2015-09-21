package com.allinmd.po.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.util.Utils;

public class IndexWebElements {
	
	// 右滑提示
	@FindBy(id = IndexPageElements.KNOW_MESSAGE)
	public static WebElement know_message;
	
	// 首页bar name
	@FindBy(id = IndexPageElements.INDEX_BAR_NAME)
	public static WebElement index_barName;
	
	// 首页病例按钮
	@FindBy(id = IndexPageElements.INDEX_CASE_BTN)
	public static WebElement index_case_btn;
	
	// 首页话题按钮
	@FindBy(id = IndexPageElements.INDEX_TOPIC_BTN)
	public static WebElement index_topic_btn;
	
	// 首页视频按钮
	@FindBy(id = IndexPageElements.INDEX_VIDEO_BTN)
	public static WebElement index_video_btn;
	
	// 首页医师按钮
	@FindBy(id = IndexPageElements.INDEX_USER_BTN)
	public static WebElement index_user_btn;
	
	// 首页搜索按钮
	@FindBy(id = IndexPageElements.INDEX_SEARCH_BTN)
	public static WebElement index_search_btn;
	
	private static WebDriver driver;
    
    public IndexWebElements(WebDriver driver){  
    	IndexWebElements.driver = driver; 
    }
    
    /**
     * 判断首页右滑提示
     */
	public static void knowMessage() {
		if (Utils.isElementExist(driver, By.id(IndexPageElements.KNOW_MESSAGE))) {
			know_message.click();		
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
