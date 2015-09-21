package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.ListPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.PerCenterPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 医师个人中心查看各类资源（发布的病例、话题、回复等等）
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class CheckDoctorResourceCase {
	private static AndroidDriver driver;
	private static String docName;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new LoginPageElements(driver);
    	new IndexPageElements(driver);
    	new ListPageElements(driver);
    	new PerCenterPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 登录并进入医师个人中心
	 * @param username
	 * @param password
	 */
	@Test (priority = 1)
	@Parameters({"LOGIN_PHONE_USER", "GLOBAL_PASSWORD"})
	public void allinLogin(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		
		WebElement eIndexCaseBtn = driver.findElement(By.id(IndexPageElements.INDEX_USER_BTN));
		eIndexCaseBtn.click();
		
		List<WebElement> eDoctorName = driver.findElements(By.id(ListPageElements.DOCTOR_NAME));
		docName = eDoctorName.get(1).getText();
		eDoctorName.get(1).click();
		
		Assertion.assertEquals(docName, Utils.getText(driver, By.id(PerCenterPageElements.PER_NAME)), "进入医师个人中心错误，请检查！");
	}
	
	/**
	 * 发布的病例
	 */
	@Test (priority = 2)
	public void checkReleaseCase() {
		Utils.swipe(driver, By.id(PerCenterPageElements.COLLECT_CONTENT), 5);
		
		WebElement eReCaseBtn = driver.findElement(By.id(PerCenterPageElements.RELEASE_CASE));
		eReCaseBtn.click();	
		
		Assertion.assertEquals("发布的病例", Utils.getText(driver, By.id(PerCenterPageElements.LIST_TITLE)), "进入列表错误，请检查");
		
		if (Utils.isElementExist(driver, By.id(PerCenterPageElements.ERROR_MESSAGE))) {
			System.err.println("\n[" + docName + "]发布的病例列表无数据");
		} else {
			List<WebElement> eReCaseTitle = driver.findElements(By.id(PerCenterPageElements.RELEASE_CASE_TITLE));
			System.out.println("\n[" + docName + "]发布的病例：");
			for (int i = 0; i < eReCaseTitle.size() - 1; i++) {
				System.out.println(eReCaseTitle.get(i).getText());
			}
		}
		
		WebElement eBack = driver.findElement(By.id(PerCenterPageElements.BACK_PER));
		eBack.click();	
		
	}
	
	/**
	 * 发布的话题
	 */
	@Test (priority = 3)
	public void checkReleaseTopic() {		
		WebElement eReTopicBtn = driver.findElement(By.id(PerCenterPageElements.RELEASE_TOPIC));
		eReTopicBtn.click();	
		
		Assertion.assertEquals("发布的话题", Utils.getText(driver, By.id(PerCenterPageElements.LIST_TITLE)), "进入列表错误，请检查");
		
		if (Utils.isElementExist(driver, By.id(PerCenterPageElements.ERROR_MESSAGE))) {
			System.err.println("\n[" + docName + "]发布的话题列表无数据");
		} else {
			List<WebElement> eReTopicTitle = driver.findElements(By.id(PerCenterPageElements.RELEASE_CASE_TITLE));
			System.out.println("\n[" + docName + "]发布的话题：");
			for (int i = 0; i < eReTopicTitle.size(); i++) {
				System.out.println(eReTopicTitle.get(i).getText());
			}
		}
		
		WebElement eBack = driver.findElement(By.id(PerCenterPageElements.BACK_PER));
		eBack.click();	
		
	}
	
	/**
	 * 发布的视频
	 */
	@Test (priority = 4)
	public void checkReleaseVideo() {		
		WebElement eReVideoBtn = driver.findElement(By.id(PerCenterPageElements.RELEASE_VIDEO));
		eReVideoBtn.click();	
		
		Assertion.assertEquals("发布的视频", Utils.getText(driver, By.id(PerCenterPageElements.LIST_TITLE)), "进入列表错误，请检查");
		
		if (Utils.isElementExist(driver, By.id(PerCenterPageElements.ERROR_MESSAGE))) {
			System.err.println("\n[" + docName + "]发布的视频列表无数据");
		} else {
			List<WebElement> eReVideoTitle = driver.findElements(By.id(PerCenterPageElements.RELEASE_VIDEO_TITLE));
			System.out.println("\n[" + docName + "]发布的视频：");
			for (int i = 0; i < eReVideoTitle.size(); i++) {
				System.out.println(eReVideoTitle.get(i).getText());
			}
		}
		
		WebElement eBack = driver.findElement(By.id(PerCenterPageElements.BACK_PER));
		eBack.click();	
	}
	
	/**
	 * 回复的内容
	 */
	@Test (priority = 5)
	public void checkComment() {		
		WebElement eCommentContentBtn = driver.findElement(By.id(PerCenterPageElements.REPLY_CONTENT));
		eCommentContentBtn.click();	
		
		Assertion.assertEquals("回复的内容", Utils.getText(driver, By.id(PerCenterPageElements.LIST_TITLE)), "进入列表错误，请检查");
		
		if (Utils.isElementExist(driver, By.id(PerCenterPageElements.ERROR_MESSAGE))) {
			System.err.println("\n[" + docName + "]回复的内容列表无数据");
		} else {
			List<WebElement> eContentTitle = driver.findElements(By.id(PerCenterPageElements.REPLY_CONTENT_TITLE));
			List<WebElement> eContentText = driver.findElements(By.id(PerCenterPageElements.REPLY_CONTENT_TEXT));
			System.out.println("\n[" + docName + "]回复的内容：");
			for (int i = 0; i < eContentTitle.size(); i++) {
				System.out.println(eContentTitle.get(i).getText() + "_\"" + eContentText.get(i).getText() + "\"");
			}
		}
		
		WebElement eBack = driver.findElement(By.id(PerCenterPageElements.BACK_PER));
		eBack.click();	
	}
	
	/**
	 * 收藏的内容
	 */
	@Test (priority = 6)
	public void checkCollect() {		
		WebElement eCollectContentBtn = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT));
		eCollectContentBtn.click();	
		
		Assertion.assertEquals("收藏的内容", Utils.getText(driver, By.id(PerCenterPageElements.LIST_TITLE)), "进入列表错误，请检查");
		
		List<WebElement> eCollect = new LinkedList<WebElement>();
		eCollect.add(driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_CASE_BTN)));
		eCollect.add(driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_VIDEO_BTN)));
		eCollect.add(driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_TOPIC_BTN)));
		
		for (int i = 0; i < eCollect.size(); i ++) {
			eCollect.get(i).click();		
			if (Utils.isElementExist(driver, By.id(PerCenterPageElements.ERROR_MESSAGE))) {
				System.err.println("\n[" + docName + "]收藏的资源列表无数据");
			} else {
				List<WebElement> eCollectTitle = driver.findElements(By.id(PerCenterPageElements.COLLECT_CONTENT_TITLE));
				System.out.println("\n[" + docName + "]收藏的资源：");
				for (int j = 0; j < eCollectTitle.size(); j++) {
					System.out.println(eCollectTitle.get(j).getText());
				}
			}
		}
		
		WebElement eCollectComment = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_COMMENT_BTN));
		eCollectComment.click();
		if (Utils.isElementExist(driver, By.id(PerCenterPageElements.ERROR_MESSAGE))) {
			System.err.println("\n[" + docName + "]收藏的内容—评论列表无数据");
		} else {
			List<WebElement> eCollectCommentTitle = driver.findElements(By.id(PerCenterPageElements.COLLECT_CONTENT_COMMENT_TITLE));
			List<WebElement> eCollectCommentText = driver.findElements(By.id(PerCenterPageElements.COLLECT_CONTENT_COMMENT_TEXT));
			System.out.println("\n[" + docName + "]收藏的内容—评论：");
			for (int i = 0; i < eCollectCommentTitle.size(); i++) {
				System.out.println(eCollectCommentTitle.get(i).getText() + "_" + eCollectCommentText.get(i).getText());
			}
		}
		
		WebElement eBack = driver.findElement(By.id(PerCenterPageElements.BACK_PER));
		eBack.click();	
	}
	
	/**
	 * 浏览记录
	 */
	@Test (priority = 7)
	public void checkBrowsing() {		
		WebElement eBrowsingBtn = driver.findElement(By.id(PerCenterPageElements.BROWSING_HISTORY));
		eBrowsingBtn.click();	
		
		Assertion.assertEquals("浏览记录", Utils.getText(driver, By.id(PerCenterPageElements.LIST_TITLE)), "进入列表错误，请检查");
		
		if (Utils.isElementExist(driver, By.id(PerCenterPageElements.ERROR_MESSAGE))) {
			System.err.println("\n[" + docName + "]浏览记录列表无数据");
		} else {
			List<WebElement> eBrowsingTitle = driver.findElements(By.id(PerCenterPageElements.BROWSING_HISTORY_TITLE));
			System.out.println("\n[" + docName + "]浏览记录：");
			for (int i = 0; i < eBrowsingTitle.size(); i++) {
				System.out.println(eBrowsingTitle.get(i).getText());
			}
		}
		
		WebElement eBack = driver.findElement(By.id(PerCenterPageElements.BACK_PER));
		eBack.click();	
	}
}
