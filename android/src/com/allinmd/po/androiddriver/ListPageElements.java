package com.allinmd.po.androiddriver;

import java.util.LinkedList;
import java.util.List;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.allinmd.util.Utils;

public class ListPageElements {
	
	// 列表第一条资源
	public static final String FIRST_RESOURCE = "com.allin.social:id/tv_video_title";
	
	// 列表actionbar
	public static final String LIST_BAR = "com.allin.social:id/tv_actionbar_name";
	
	// 排序按钮
	public static String sort_btn = "com.allin.social:id/ll_sort";
	
	// 标签按钮
	public static String sign_btn = "com.allin.social:id/ll_sign";
	
	// 专业按钮
	public static String major_btn = "com.allin.social:id/ll_major";
	
	// 医师列表第一个医生
	public static final String DOCTOR_NAME = "com.allin.social:id/tv_name";
	
	// 阅读数
	public static String view_num = "com.allin.social:id/tv_video_playnum";
	
	// 评论数
	public static String review_num = "com.allin.social:id/tv_review_number";
	
	// 选择的专业
	public static String selected_major = "//*[contains(@text, '关节')]";
	
	// 选择的标签
	public static String selected_tag = "//*[contains(@text, '人工膝关节置换')]";
	
	// 话题选择的标签
	public static String topic_selected_tag = "//*[contains(@text, '小儿骨科')]";
	
	// 从终端页返回列表
	public static String back_list = "com.allin.social:id/rl_back";
	
	// 视频终端页展开按钮
	public static String operate_btn = "com.allin.social:id/ll_operate";

	private static AndroidDriver driver;
	
	public ListPageElements(AndroidDriver driver) {
		ListPageElements.driver = driver;
	}
	
	/**
	 * 列表按最多浏览排序
	 * @return
	 */
	public static boolean byViewSort() {
		WebElement eSign = driver.findElement(By.id(sort_btn));
		Dimension size = eSign.getSize();
		
		driver.findElement(By.id(sort_btn)).click();
		
		TouchAction action = new TouchAction(driver);
		action.tap(size.getHeight() + 650, size.getWidth() + 450).perform();
		Utils.sleep(2);
		
		List<WebElement> eList = driver.findElements(By.id(view_num));
		List<String> eView = new LinkedList<String>();	
		
//		System.out.println("列表按最多浏览排序结果：");
		for(int i = 0; i < eList.size() - 1; i++) {	
			/*
			if (Integer.parseInt(eView.get(i).getText()) > Integer.parseInt
					(eView.get(i + 1).getText()) == true) {
				System.out.println("第" + (i + 1) + "个帖子阅读数:" + eView.get(i).getText());
				System.out.println("第" + (i + 2) + "个帖子阅读数:" + eView.get(i + 1).getText());
			} else {
				System.err.println("按最多浏览排序失败");
			}
			*/
			eView.add(eList.get(i).getText().replace("万+", "0000"));			
//			System.out.println("第" + (i + 1) + "个帖子阅读数:" + eList.get(i).getText());
//			System.out.println("第" + (i + 2) + "个帖子阅读数:" + eList.get(i + 1).getText());
		}		
		
		boolean flag = false;
		for(int i = 0; i < eView.size() - 1; i++) {		
			flag = Integer.parseInt(eView.get(i)) >= Integer.parseInt
					(eView.get(i + 1));						
		}
		
		return flag;
	}
	
	/**
	 * 列表按最多评论排序
	 * @return
	 */
	public static boolean byReviewSort() {
		WebElement eSign = driver.findElement(By.id(sort_btn));
		Dimension size = eSign.getSize();
		
		driver.findElement(By.id(sort_btn)).click();
		
		TouchAction action = new TouchAction(driver);
		action.tap(size.getHeight() + 650, size.getWidth() + 580).perform();
		Utils.sleep(2);
		
		List<WebElement> eList = driver.findElements(By.id(review_num));
		List<String> eReview = new LinkedList<String>();
		
//		System.out.println("列表按最多评论排序结果：");
		for(int i = 0; i < eList.size() - 1; i++) {
			eReview.add(eList.get(i).getText().replaceAll("万+", "0000"));
//			System.out.println("第" + (i + 1) + "个帖子评论数:" + eList.get(i).getText());
//			System.out.println("第" + (i + 2) + "个帖子评论数:" + eList.get(i + 1).getText());
		}		
		
		boolean flag = false;
		for(int i = 0; i < eReview.size() - 1; i++) {			
			flag = Integer.parseInt(eReview.get(i)) >= Integer.parseInt
					(eReview.get(i + 1));						
		}
		
		return flag;
	}
	
	/**
	 * 列表按专业分类
	 * @return 
	 */
	public static boolean byMajorSort() {
		WebElement eSign = driver.findElement(By.id(sort_btn));
		Dimension size = eSign.getSize();
		
		driver.findElement(By.id(major_btn)).click();
		
		TouchAction action = new TouchAction(driver);
		action.tap(size.getHeight() + 650, size.getWidth() + 450).perform();
		Utils.sleep(2);
		
		return sortClass(selected_major);
	}
	
	/**
	 * 列表按标签分类
	 * @return 
	 */
	public static boolean byTagSort() {
		WebElement eSign = driver.findElement(By.id(sort_btn));
		Dimension size = eSign.getSize();
		
		driver.findElement(By.id(sign_btn)).click();
		
		TouchAction action = new TouchAction(driver);
		action.tap(size.getHeight() + 650, size.getWidth() + 900).perform();
		Utils.sleep(2);
		
		return sortClass(selected_tag);

	}
	
	/**
	 * 话题列表按标签分类
	 * @return 
	 */
	public static boolean topicByTagSort() {
		WebElement eSign = driver.findElement(By.id(sort_btn));
		Dimension size = eSign.getSize();
		
		driver.findElement(By.id(sign_btn)).click();
		
		TouchAction action = new TouchAction(driver);
		action.tap(size.getHeight() + 650, size.getWidth() + 900).perform();
		Utils.sleep(2);
		
		return sortClass(topic_selected_tag);

	}
	
	public static boolean sortClass(String classS) {
		WebElement eCase = driver.findElement(By.id(FIRST_RESOURCE));
		eCase.click();
		
		if (Utils.isElementExist(driver, By.id(operate_btn))) {
			WebElement eOperate = driver.findElement(By.id(operate_btn));
			eOperate.click();
		}
		
		boolean flag = Utils.swipe(driver, By.xpath(classS), 5);
		
		WebElement eBack = driver.findElement(By.id(back_list));
		eBack.click();
		
		return flag;
	}
	
	public static boolean assertListBar(String barName) {
    	boolean status = false;
    	WebElement eListBar = driver.findElement(By.id(LIST_BAR));
    	if (eListBar.getText().equals(barName)) {
    		status = true;
    	}
		return status;
	}	
}
