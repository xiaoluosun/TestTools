package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

@Listeners({com.runtime.listener.AssertionListener.class})
public class PublishTopicElements {
	
	// 发布话题按钮
	public static String pub_topic_btn = "com.allin.social:id/ll_create_topic";

	// 话题标题
	public static String topic_title_input = "com.allin.social:id/et_topic_title";
	
	// 输入讨论
	public static String topic_talk_input = "com.allin.social:id/et_topic_talk";
	
	// 上传图片
	public static String add_pic = "com.allin.social:id/ll_add_topicimage"; 
											 
	// 发布按钮
	public static String pub_topic = "com.allin.social:id/btn_forgetpw_confirm";
	
	// 终端页话题标题
	public static String terminal_topic_title = "com.allin.social:id/tv_case_name";	
	
	private static AndroidDriver driver;
    
    public PublishTopicElements(AndroidDriver driver){  
    	PublishTopicElements.driver = driver; 
    	new SubscribePageElements(driver);
    	new PublishCaseElements(driver);
    	new MainMenuPageElements(driver);
    }
    
    /**
     * 发布话题，并根据终端页信息验证是否发布成功。
     * @param topic_title
     * @param topic_talk
     * @return
     */
	public static String pubTopic(String topic_title, String topic_talk) {
		WebElement ePubTopicBtn = driver.findElement(By.id(pub_topic_btn));
		ePubTopicBtn.click();
		
		WebElement eTopicTitle = driver.findElement(By.id(topic_title_input));
		eTopicTitle.sendKeys(topic_title);
		
		WebElement eTopicTalk = driver.findElement(By.id(topic_talk_input));
		eTopicTalk.clear();
		eTopicTalk.sendKeys(topic_talk);
		
		WebElement eLoadPic = driver.findElement(By.id(add_pic));
		eLoadPic.click();
		
		WebElement eSelectPic1 = driver.findElement(By.xpath(PublishCaseElements.SELECT_PIC1));
		eSelectPic1.click();
		
		WebElement eSelectPic2 = driver.findElement(By.xpath(PublishCaseElements.SELECT_PIC2));
		eSelectPic2.click();
		
		WebElement eSelectPicNext = driver.findElement(By.id(PublishCaseElements.SELECT_PIC_NEXT));
		eSelectPicNext.click();	
		
		boolean flag = Utils.swipe(driver, By.id(pub_topic), 2);
		boolean ePubCaseAttribute;		
		if (flag) {
			ePubCaseAttribute = Boolean.parseBoolean(driver.findElement(By.id(pub_topic)).getAttribute("clickable"));			
			if (ePubCaseAttribute) {
				WebElement ePubCase = driver.findElement(By.id(pub_topic));
				ePubCase.click();
			} else {
				Assertion.assertEquals(true, ePubCaseAttribute, "未触发发布按钮，是置灰状态!");
			}
		} else {
			Assertion.assertEquals(true, flag, "发布按钮未找到，请检查后再试！");
		}
		
		String terminalTopicName = "";
		boolean findTopicTitle = Utils.isElementExist(driver, By.id(terminal_topic_title));
		if (findTopicTitle == false) {
			while (findTopicTitle == false) {
				findTopicTitle = Utils.isElementExist(driver, By.id(terminal_topic_title));
				if (findTopicTitle) {
					terminalTopicName = Utils.getText(driver, By.id(terminal_topic_title));
				}
			}
		} else {
			terminalTopicName = Utils.getText(driver, By.id(terminal_topic_title));
		}
		
		return terminalTopicName;
	}
	
	/**
	 * 到个人中心——发布的话题查看刚发布的话题
	 * @return
	 */
	public static String checkTopic() {
		WebElement eBackList = driver.findElement(By.id(TerminalPageElements.TERMINAL_BACK));
		eBackList.click();	
		
//		WebElement eMainMenu = driver.findElement(By.className(SubscribePageElements.MAIN_MENU));
//		eMainMenu.click();
		
		WebElement ePersonalCerter = driver.findElement(By.id(MainMenuPageElements.PERSONAL_CERTER));
		ePersonalCerter.click();
		
		WebElement eReleaseTopic = driver.findElement(By.id(PerCenterPageElements.RELEASE_TOPIC));
		eReleaseTopic.click();
		
		WebElement eReleaseTopicTitle = driver.findElement(By.id(PerCenterPageElements.RELEASE_CASE_TITLE));
		
		return eReleaseTopicTitle.getText();
	}
}
