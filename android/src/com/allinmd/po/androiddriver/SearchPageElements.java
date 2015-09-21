package com.allinmd.po.androiddriver;

import java.util.LinkedList;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.allinmd.util.Utils;

public class SearchPageElements {
	
	// 搜索输入框
	public static String search_input = "com.allin.social:id/et_search";
	
	//搜索结果页病例tag
	public static String search_case = "com.allin.social:id/tv_search_case";
	
	//搜索结果页视频tag
	public static String search_video = "com.allin.social:id/tv_search_video";

	//搜索结果页话题tag
	public static String search_topic = "com.allin.social:id/tv_search_topic";
	
	//搜索结果页用户tag
	public static String search_user = "com.allin.social:id/tv_search_user";
	
	//搜索结果页标签tag
	public static String search_label = "com.allin.social:id/tv_search_label";
	
	//搜索结果页病例、视频、话题资源名
	public static String title_name = "com.allin.social:id/tv_video_title";
	
	//搜索结果页病例、视频、话题资源作者名
	public static String auther_name = "com.allin.social:id/tv_auther_name";
	
	//搜索结果页医师tag名字
	public static String doctor_name = "com.allin.social:id/tv_name";
	
	//搜索结果页标签name
	public static String label_name = "com.allin.social:id/tv_search_pop_item";
	
	//无结果提示
	public static String error_message = "com.allin.social:id/tv_error_layout";
	
	public static AndroidDriver driver;
	
	public SearchPageElements(AndroidDriver driver) {
		SearchPageElements.driver = driver;
	}
	
	/**
	 * 输入关键字并搜索
	 * @param keyword
	 */
	public static void searchMethod(String keywordAuthName) {
		WebElement eSearch = driver.findElement(By.id(IndexPageElements.INDEX_SEARCH_BTN));
		eSearch.click();

		WebElement eSearchInput = driver.findElement(By.id(search_input));
		eSearchInput.sendKeys(keywordAuthName);

		Utils.setInputMethod();
		driver.findElement(By.id(search_input)).click();
		driver.sendKeyEvent(66);
		
		WebElement eCaseBtn = driver.findElement(By.id(search_case));
		eCaseBtn.click();
	}
	
	/**
	 * 得到搜索结果病例、视频、话题列表作者名
	 * @return
	 */
	public static List<String> getResourceName() {
		List<String> eInfo = new LinkedList<String>();
		
		if (Utils.isElementExist(driver, By.id(error_message))) {
			System.out.println("没有搜索结果");
		} else {
			List<WebElement> eAuth = driver.findElements(By.id(auther_name));
			List<WebElement> eTitle = driver.findElements(By.id(title_name));
			for (int i = 0; i < eAuth.size() - 1; i ++) {
				eInfo.add(eTitle.get(i).getText() + "_" + eAuth.get(i).getText());
			}
		}
		return eInfo;
	}
	
	/**
	 * 得到搜索结果医师列表名字
	 * @return
	 */
	public static List<String> getDoctorName() {
		List<String> eDoctorName = new LinkedList<String>();
		
		if (Utils.isElementExist(driver, By.id(error_message))) {
			System.out.println("没有搜索结果");
		} else {
			List<WebElement> eAuth = driver.findElements(By.id(doctor_name));		
			for (int i = 0; i < eAuth.size() - 1; i ++) {
				eDoctorName.add(eAuth.get(i).getText());
			}
		}
		return eDoctorName;	
	}
	
	/**
	 * 得到搜索结果标签name
	 * @return
	 */
	public static List<String> getLabelName() {
		List<String> eLabelName = new LinkedList<String>();
		
		if (Utils.isElementExist(driver, By.id(error_message))) {
			System.out.println("没有搜索结果");
		} else {
			List<WebElement> eLabel = driver.findElements(By.id(label_name));		
			for (int i = 0; i < eLabel.size() - 1; i ++) {
				eLabelName.add(eLabel.get(i).getText());
			}
		}
		return eLabelName;	
	}
}
