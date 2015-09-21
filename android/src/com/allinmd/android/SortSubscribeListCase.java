package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.ListPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.SubscribePageElements;
import com.allinmd.po.androiddriver.MainMenuPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 订阅列表排序、专业和标签筛选
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class SortSubscribeListCase {
	private static AndroidDriver driver;

	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new ListPageElements(driver);
		new LoginPageElements(driver);
		new MainMenuPageElements(driver);
		new SubscribePageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	@Test (priority = 1)
	@Parameters ({"LOGIN_PHONE_USER", "GLOBAL_PASSWORD"})
	public void allinLogin(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		
		MainMenuPageElements.goPersonal();
	}
	
	/**
	 * 进入订阅列表
	 */
	@Test (priority = 2)
	public void rssList() {
		SubscribePageElements.goSubscription();
	}
	/**
	 * 病例列表按最多浏览排序
	 */
	@Test (priority = 3)
	public void caseByViewSort() {
		WebElement eCase = driver.findElement(By.id(SubscribePageElements.case_btn));
		eCase.click();
		
		Assertion.assertEquals(true, ListPageElements.byViewSort(), "按最多浏览排序失败");		
	}
	
	/**
	 * 病例列表按最多评论排序
	 */
	@Test (priority = 4)
	public void caseByReviewSort() {
		Assertion.assertEquals(true, ListPageElements.byReviewSort(), "按最多评论排序失败");		
	}

	
	/**
	 * 话题列表按最多浏览排序
	 */
	@Test (priority = 5)
	public void topicByViewSort() {
		WebElement eTopic = driver.findElement(By.id(SubscribePageElements.topic_btn));
		eTopic.click();
		
		Assertion.assertEquals(true, ListPageElements.byViewSort(), "按最多浏览排序失败");		
	}
	
	/**
	 * 话题列表按最多评论排序
	 */
	@Test (priority = 6)
	public void topicByReviewSort() {
		Assertion.assertEquals(true, ListPageElements.byReviewSort(), "按最多评论排序失败");		
	}
	
	/**
	 * 视频列表按最多浏览排序
	 */
	@Test (priority = 7)
	public void videoByViewSort() {
		WebElement eVideo = driver.findElement(By.id(SubscribePageElements.video_btn));
		eVideo.click();
		
		Assertion.assertEquals(true, ListPageElements.byViewSort(), "按最多浏览排序失败");		
	}
	
	/**
	 * 视频列表按最多评论排序
	 */
	@Test (priority = 8)
	public void videoByReviewSort() {
		Assertion.assertEquals(true, ListPageElements.byReviewSort(), "按最多评论排序失败");		
	}
}
