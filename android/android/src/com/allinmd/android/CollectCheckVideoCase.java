package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

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
 * 视频-收藏、取消收藏、个人中心—收藏的内容验证收藏的视频
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class CollectCheckVideoCase {
	private static AndroidDriver driver;
	private static String videoName;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new LoginPageElements(driver);
    	new IndexPageElements(driver);
    	new ListPageElements(driver);
    	new MainMenuPageElements(driver);
    	new PerCenterPageElements(driver);
    	new TerminalPageElements();

	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 进入终端页
	 * @param username
	 * @param password
	 */
	@Test (priority = 1)
	@Parameters({"LOGIN_PHONE_USER", "GLOBAL_PASSWORD"})
	public void allinLogin(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		
		WebElement index_case_btn = driver.findElement(By.id(IndexPageElements.INDEX_VIDEO_BTN));
		index_case_btn.click();
		
		WebElement first_resource = driver.findElement(By.id(ListPageElements.FIRST_RESOURCE));
		first_resource.click();
	}
	
	/**
	 * 终端页收藏
	 */
	@Test (priority = 2)
	public void collectVideo() {	
		WebElement collect_case_btn = driver.findElement(By.id(TerminalPageElements.COLLECT_VIDEO_BTN));
		collect_case_btn.click();
		
		WebElement case_name = driver.findElement(By.id(TerminalPageElements.VIDEO_NAME));
		videoName = case_name.getText();
		
		Assert.assertEquals("取消收藏", Utils.getText(driver, By.id(TerminalPageElements.COLLECT_VIDEO_BTN)), "没有收藏成功，请检查后再试！");
	}
	
	/**
	 * 个人中心—收藏的内容验证收藏的视频
	 */
	@Test (priority = 3)
	public void checkCollectVideo() {
		WebElement terminal_back = driver.findElement(By.id(TerminalPageElements.TERMINAL_BACK));		
		terminal_back.click();
				
		MainMenuPageElements.goPersonal();
		
		WebElement collect_content = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT));
		collect_content.click();
		
		WebElement collect_content_case_btn = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_VIDEO_BTN));
		collect_content_case_btn.click();
		
		Assertion.assertEquals(videoName, Utils.getText(driver, By.id(PerCenterPageElements.COLLECT_CONTENT_TITLE)), "收藏的视频列表第一个资源不符，请检查！");			
	}
	
	/**
	 * 取消收藏
	 */
	@Test (priority = 4)
	public void cancelCollectVideo() {
		WebElement collect_content_title = driver.findElement(By.id(PerCenterPageElements.COLLECT_CONTENT_TITLE));
		collect_content_title.click();	
				
		Assertion.assertEquals("取消收藏", Utils.getText(driver, By.id(TerminalPageElements.COLLECT_VIDEO_BTN)), "是未收藏状态，请检查！");	
		
		WebElement collect_case_btn = driver.findElement(By.id(TerminalPageElements.COLLECT_VIDEO_BTN));
		collect_case_btn.click();
		
		Assertion.assertEquals("收藏", Utils.getText(driver, By.id(TerminalPageElements.COLLECT_VIDEO_BTN)), "取消收藏失败，请检查！");		
	}
}
