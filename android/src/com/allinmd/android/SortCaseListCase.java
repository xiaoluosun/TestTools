package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.ListPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 病例列表排序、专业和标签筛选
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class SortCaseListCase {
	private static AndroidDriver driver;

	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new ListPageElements(driver);
		new LoginPageElements(driver);
		new IndexPageElements(driver);
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
		driver.findElement(By.id(IndexPageElements.INDEX_CASE_BTN)).click();
		
	}
	
	/**
	 * 病例列表按最多浏览排序
	 */
	@Test (priority = 2)
	public void caseByViewSort() {
		Assertion.assertEquals(true, ListPageElements.byViewSort(), "按最多浏览排序失败");		
	}
	
	/**
	 * 病例列表按最多评论排序
	 */
	@Test (priority = 3)
	public void caseByReviewSort() {
		Assertion.assertEquals(true, ListPageElements.byReviewSort(), "按最多评论排序失败");		
	}
	
	/**
	 * 病例列表按专业分类
	 */
	@Test (priority = 4)
	public void caseByMajorSort() {
		Assertion.assertEquals(true, ListPageElements.byMajorSort(), "按专业分类失败，未找到该专业");
	}
	
	/**
	 * 病例列表按标签分类
	 */
	@Test (priority = 5)
	public void caseByTagSort() {
		Assertion.assertEquals(true, ListPageElements.byTagSort(), "按专业分类失败，未找到该专业");
	}
}
