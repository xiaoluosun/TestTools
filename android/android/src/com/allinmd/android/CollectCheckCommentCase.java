package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.ListPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.MainMenuPageElements;
import com.allinmd.po.androiddriver.PerCenterPageElements;
import com.allinmd.po.androiddriver.TerminalPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 评论-收藏、取消收藏、个人中心—收藏的内容验证收藏的评论
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class CollectCheckCommentCase {
	private static AndroidDriver driver;
	private static String commentText;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new LoginPageElements(driver);
    	new IndexPageElements(driver);
    	new ListPageElements(driver);
    	new TerminalPageElements();
    	new MainMenuPageElements(driver);
    	new PerCenterPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 登录进入终端页
	 * @param username
	 * @param password
	 */
	@Test (priority = 1)
	@Parameters({"LOGIN_PHONE_USER", "GLOBAL_PASSWORD"})
	public void allinLogin(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		
		WebElement eIndexCaseBtn = driver.findElement(By.id(IndexPageElements.INDEX_CASE_BTN));
		eIndexCaseBtn.click();

		List<WebElement> eResourceReview = driver.findElements(By.id(ListPageElements.review_num));
		List<WebElement> eFirstResource = driver.findElements(By.id(ListPageElements.FIRST_RESOURCE));
		
		for (int i = 0; i < eResourceReview.size() - 1; i++) {
			if (Integer.parseInt(eResourceReview.get(i).getText()) > 0) {
				eFirstResource.get(i).click(); 
				break;
			}
		}
		
	}
	
	/**
	 * 终端页收藏
	 */
	@Test (priority = 2)
	public void collectComment() {		
		Utils.swipe(driver, By.id(TerminalPageElements.COMMENT_TEXT), 5);
		
		WebElement eCommentText = driver.findElement(By.id(TerminalPageElements.COMMENT_TEXT));
		eCommentText.click();
		
		WebElement eCommentCollect = driver.findElement(By.id(TerminalPageElements.COLLECT_CASE_BTN));
		eCommentCollect.click();
		
		commentText = eCommentText.getText();
		eCommentText.click();
		
		Assert.assertEquals("取消收藏", eCommentCollect.getText(), "没有收藏成功，请检查后再试！");
		
		//为了干掉弹窗，多点击一下赞同按钮
		WebElement eCommentPrefer = driver.findElement(By.id(TerminalPageElements.PREFRR_CASE_BTN));
		eCommentPrefer.click();
	}
	
	/**
	 * 个人中心—收藏的内容验证收藏的评论
	 */
	@Test (priority = 3)
	public void checkCollectComment() {
		WebElement eBack = driver.findElement(By.id(TerminalPageElements.TERMINAL_BACK));
		eBack.click();
		
		MainMenuPageElements.goPersonal();
		
		WebElement eCollentContent = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT));
		eCollentContent.click();
		
		WebElement eCollentContentCommentBtn = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_COMMENT_BTN));
		eCollentContentCommentBtn.click();
		
		WebElement eCollentContentCommentText = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_COMMENT_TEXT));
		if (eCollentContentCommentText.getText().substring(0, 10).equals(commentText.substring(0, 10))) {
//			WebElement eCollentContentCommentTitle = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_COMMENT_TITLE));
//			eCollentContentCommentTitle.click();
			
			eCollentContentCommentText.click();
			
		} else {
			Assertion.assertEquals(commentText.substring(0, 10), eCollentContentCommentText.getText().substring(0, 10), "收藏的评论列表第一个资源不符，请检查！");
		}
				
	}
	
	/**
	 * 取消收藏
	 */
	@Test (priority = 4)
	public void cancelCollectComment() {		
		WebElement eCollentBtn = driver.findElement(By.id(TerminalPageElements.COLLECT_CASE_BTN));		
		Assertion.assertEquals("取消收藏",eCollentBtn.getText(), "是未收藏状态，请检查！");	
		
		eCollentBtn.click();
		Assertion.assertEquals("收藏", eCollentBtn.getText(), "取消收藏失败，请检查！");	
	}
}
