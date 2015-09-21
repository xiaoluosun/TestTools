package com.allinmd.po.webdriver.cases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.webdriver.page.IndexWebElements;
import com.allinmd.po.webdriver.page.ListWebElements;
import com.allinmd.po.webdriver.page.LoginWebElements;
import com.allinmd.po.webdriver.page.MainMenuWebElements;
import com.allinmd.po.webdriver.page.PerCenterWebElements;
import com.allinmd.po.webdriver.page.TerminalWebElements;
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
	private static WebDriver driver;
	private static String videoName;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.webDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	PageFactory.initElements(driver, LoginWebElements.class);
    	PageFactory.initElements(driver, IndexWebElements.class);
    	PageFactory.initElements(driver, ListWebElements.class);
    	PageFactory.initElements(driver, MainMenuWebElements.class);
    	PageFactory.initElements(driver, PerCenterWebElements.class);
    	PageFactory.initElements(driver, TerminalWebElements.class);
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
		LoginWebElements.allinLogin(username, password);
		
		IndexWebElements.index_video_btn.click();
		ListWebElements.first_resource.click();
	}
	
	/**
	 * 终端页收藏
	 */
	@Test (priority = 2)
	public void collectVideo() {		
		TerminalWebElements.collect_video_btn.click();
		videoName = TerminalWebElements.video_name.getText();
		Assert.assertEquals("取消收藏", TerminalWebElements.collect_video_btn.getText(), "没有收藏成功，请检查后再试！");
	}
	
	/**
	 * 个人中心—收藏的内容验证收藏的视频
	 */
	@Test (priority = 3)
	public void checkCollectVideo() {
		TerminalWebElements.terminal_back.click();
		MainMenuWebElements.goPersonal();
		
		PerCenterWebElements.collect_content.click();
		PerCenterWebElements.collect_content_video_btn.click();
		
		Assertion.assertEquals(videoName, PerCenterWebElements.collect_content_title.getText(), "收藏的视频列表第一个资源不符，请检查！");		
	}
	
	/**
	 * 取消收藏
	 */
	@Test (priority = 4)
	public void cancelCollectVideo() {
		PerCenterWebElements.collect_content_title.click();		
		Assertion.assertEquals("取消收藏", TerminalWebElements.collect_video_btn.getText(), "是未收藏状态，请检查！");	
		
		TerminalWebElements.collect_video_btn.click();
		Assertion.assertEquals("收藏", TerminalWebElements.collect_video_btn.getText(), "取消收藏失败，请检查！");	
	}
}
