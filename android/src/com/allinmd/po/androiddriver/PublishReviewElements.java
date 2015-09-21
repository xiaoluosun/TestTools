package com.allinmd.po.androiddriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class PublishReviewElements {
	
	// 回复内容输入框
	public static String reply_text_input = "com.allin.social:id/et_cases_talk";
	
	// 回复上传图片
	public static String reply_add_pic = "com.allin.social:id/ll_add_topicimage";
	
	// 发布评论
	public static String pub_review = "com.allin.social:id/btn_att_major_next";
	
	public static AndroidDriver driver;
	
	public PublishReviewElements(AndroidDriver driver) {
		PublishReviewElements.driver = driver;
		new TerminalPageElements();
		new MainMenuPageElements(driver);
	}
	
	public static void replyResource(String content) {
		WebElement eReplyBtn = driver.findElement(By.id(TerminalPageElements.REPLY_BTN));
		eReplyBtn.click();
		
		WebElement eReplyText = driver.findElement(By.id(reply_text_input));
		eReplyText.sendKeys(content);
		
		WebElement eReplyAddPic = driver.findElement(By.id(reply_add_pic));
		eReplyAddPic.click();
		
		WebElement eSelectPic1 = driver.findElement(By.id(PublishCaseElements.SELECT_PIC1));
		eSelectPic1.click();
		
		WebElement eSelectPic2 = driver.findElement(By.id(PublishCaseElements.SELECT_PIC2));
		eSelectPic2.click();
		
		WebElement eSelectPicNext = driver.findElement(By.id(PublishCaseElements.SELECT_PIC_NEXT));
		eSelectPicNext.click();
				
		WebElement ePubReview = driver.findElement(By.id(pub_review));
		ePubReview.click();
	}
	
	/**
	 * 到个人中心——回复的内容查看刚发布的评论
	 * @return
	 */
	public static String checkReview(String text) {		
		WebElement eBackList = driver.findElement(By.id(TerminalPageElements.TERMINAL_BACK));
		eBackList.click();	
		
		WebElement ePersonalCerter = driver.findElement(By.id(MainMenuPageElements.PERSONAL_CERTER));
		ePersonalCerter.click();
		
		WebElement eReplyContent = driver.findElement(By.id(PerCenterPageElements.REPLY_CONTENT));
		eReplyContent.click();
		
		boolean flag = false;
		String ReplyTitle = "";
		while (flag == false) {
			List<WebElement> eReplyText = driver.findElements(By.id(PerCenterPageElements.REPLY_CONTENT_TEXT));
			List<WebElement> eReplyTitle = driver.findElements(By.id(PerCenterPageElements.REPLY_CONTENT_TITLE));
			
			for (int i = 0; i < eReplyText.size() - 1; i++) {
				if (eReplyText.get(i).getText().equals(text)) {
					eReplyTitle.get(i).click();
					flag = true;
					ReplyTitle = eReplyTitle.get(i).getText();
				} else {
					flag = false;
				}
			}
			
			Dimension windowSize = driver.manage().window().getSize();						
			driver.swipe(windowSize.getWidth() / 2, windowSize.getHeight() / 2 + 500, windowSize.getWidth() / 2, 300 , 0);
			
		}
		return ReplyTitle;
	}
	
}
