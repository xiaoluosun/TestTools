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

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.ListPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.PublishReviewElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;

/**
 * 评论-发布\查看\删除
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class PubCheckDeleteCommentCase_debug {
	private static AndroidDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new LoginPageElements(driver);
		new PublishReviewElements(driver);
		new IndexPageElements(driver);
		new ListPageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	@Test (priority = 1)
	@Parameters({"LOGIN_PHONE_USER", "GLOBAL_PASSWORD"})
	public void allinLogin(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		
		WebElement eMainMenu = driver.findElement(By.id(IndexPageElements.INDEX_CASE_BTN));
		eMainMenu.click();
		
		WebElement eFirstCase = driver.findElement(By.id(ListPageElements.FIRST_RESOURCE));
		eFirstCase.click();
	}
	
	public void replyResource() {
		
	}
}
