package com.allinmd.po.androiddriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import io.appium.java_client.android.AndroidDriver;

@Listeners({com.runtime.listener.AssertionListener.class})
public class SubscribePageElements {
	
	// 添加订阅
	public static final String ADD_SUBSCRIBE = "com.allin.social:id/ll_add_subscribe";
	
	// 编辑订阅按钮
	public static final String EDIT_SUBSCRIBE = "com.allin.social:id/btn_edit";
	
	// 删除订阅按钮
	public static final String DELETE_SUBSCRIBE = "com.allin.social:id/ll_delete";
	
	// 订阅选中按钮
	public static final String SELECT_TAB = "com.allin.social:id/cb_checkbox";
	
	// 订阅名字
	public static final String TAB_NAME = "com.allin.social:id/tv_name";
	
	// 返回已订阅列表
	public static final String BACK = "com.allin.social:id/rl_back";
	
	// 标签name
	public static String label_name = "com.allin.social:id/tv_name";
	
	// 标签name2
	public static String label_name2 = "com.allin.social:id/textView1";
	
	// 订阅列表病例tab
	public static String case_btn = "com.allin.social:id/tv_collection_case";
	
	// 订阅列表话题tab
	public static String topic_btn = "com.allin.social:id/tv_collection_topic";
	
	// 订阅列表视频tab
	public static String video_btn = "com.allin.social:id/tv_collection_video";

	private static AndroidDriver driver;
	
	public SubscribePageElements(AndroidDriver driver) {
		SubscribePageElements.driver = driver;
		new PerCenterPageElements(driver);
	}
	
	/**
	 * 从个人中心进入订阅列表
	 */
	public static void goSubscription() {
		WebElement eLabelNum = driver.findElement(By.id(PerCenterPageElements.TAB_NUM));
		eLabelNum.click();
		
		List<WebElement> eLabelName = driver.findElements(By.id(label_name));
		eLabelName.get(0).click();
		
//		String eLabelName2 = driver.findElement(By.id(label_name2)).getText();
		
//		Assertion.assertEquals(eLabelName.getText(), eLabelName2.getText(), "进入订阅列表错误，请检查！");
	}
}
