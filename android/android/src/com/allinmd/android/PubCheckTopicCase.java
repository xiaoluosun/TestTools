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
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.PublishTopicElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 话题-发布\查看
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class PubCheckTopicCase {
	private static AndroidDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		new LoginPageElements(driver);
		new PublishTopicElements(driver);
		new IndexPageElements(driver);
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
		
		WebElement eMainMenu = driver.findElement(By.className(IndexPageElements.MAIN_MENU));
		eMainMenu.click();
	}
	
	/**
	 * 发布话题
	 * @param topic_title
	 * @param age
	 * @param main_suit
	 * @param topic_talk
	 */
	@Test (priority = 2)
	@Parameters({"pub_topic_title", "pub_topic_talk"})
	public void pubTopic(String topic_title, String topic_talk) {	
		String terminalTopicName = PublishTopicElements.pubTopic(topic_title, topic_talk);
		Assertion.assertEquals(topic_title, terminalTopicName, "发布话题后跳转的终端页不符，请检查！");
	}
	
	/**
	 * 到个人中心——发布的话题查看刚发布的话题
	 * @param topic_title
	 */
	@Test (priority = 3)
	@Parameters({"pub_topic_title"})
	public void checkTopic(String topic_title) {
		String getTopicTitle = PublishTopicElements.checkTopic();
		Assertion.assertEquals(topic_title, getTopicTitle, "个人中心-发布的话题下未找到刚发布的话题，请检查！");
	}
}
