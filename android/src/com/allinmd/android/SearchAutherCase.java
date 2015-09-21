package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.SearchPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 搜索医师关键字，并验证结果
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class SearchAutherCase {
	private static AndroidDriver driver;
	private String authName;
	
	@BeforeClass
	@Parameters ({"LOGIN_PHONE_USER", "GLOBAL_PASSWORD"})
	public void setUp(String username, String password) {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new SearchPageElements(driver);
		new LoginPageElements(driver);
		
		LoginPageElements.allinLogin(username, password);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 搜索关键词
	 * @param authName
	 */
	@Test (priority = 1)
	@Parameters({"authName"})
	public void searchF(String authName) {	
		this.authName = authName;
		SearchPageElements.searchMethod(authName);	
	}
	
	/**
	 * 验证搜索结果列表病例tag页
	 */
	@Test (priority = 2)
	public void assertCase() {			
		WebElement eCaseBtn = driver.findElement(By.id(SearchPageElements.search_case));
		eCaseBtn.click();
		
		List<String> caseInfo = SearchPageElements.getResourceName();
		System.out.println("\n病例tab页搜索结果：");
		for (int i = 0; i < caseInfo.size() - 1; i ++) {			
			System.out.println(caseInfo.get(i));
			Assertion.assertEquals(true, caseInfo.get(i).contains(authName), "搜索结果显示错误，请检查！");
		}
	}
	
	/**
	 * 验证搜索结果列表视频tag页
	 */
	@Test (priority = 3)
	public void assertVideo() {	
		WebElement eVideoBtn = driver.findElement(By.id(SearchPageElements.search_video));
		eVideoBtn.click();
		
		List<String> videoInfo = SearchPageElements.getResourceName();
		System.out.println("\n视频tab页搜索结果：");
		for (int i = 0; i < videoInfo.size() - 1; i ++) {
			System.out.println(videoInfo.get(i));
//			Assertion.assertEquals(true, videoInfo.get(i).contains(authName), "搜索结果显示错误，请检查！");
		}
	}
	
	/**
	 * 验证搜索结果列表话题tag页
	 */
	@Test (priority = 4)
	public void assertTopic() {	
		WebElement eTopicBtn = driver.findElement(By.id(SearchPageElements.search_topic));
		eTopicBtn.click();
		
		List<String> topicInfo = SearchPageElements.getResourceName();
		System.out.println("\n话题tab页搜索结果：");
		for (int i = 0; i < topicInfo.size() - 1; i ++) {
			System.out.println(topicInfo.get(i));
//			Assertion.assertEquals(true, topicInfo.get(i).contains(authName), "搜索结果显示错误，请检查！");
		}
	}
	
	/**
	 * 验证搜索结果列表医师tag页
	 */
	@Test (priority = 5)
	public void assertDoctor() {			
		WebElement eDoctorBtn = driver.findElement(By.id(SearchPageElements.search_user));
		eDoctorBtn.click();
		
		List<String> doctorName = SearchPageElements.getDoctorName();
		System.out.println("\n医师tab页搜索结果：");
		for (int i = 0; i < doctorName.size() - 1; i ++) {		
			System.out.println(doctorName.get(i));
			Assertion.assertEquals(true, doctorName.get(i).contains(authName), "搜索结果显示错误，请检查！");
		}
	}
}
