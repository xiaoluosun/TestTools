package com.allinmd.po.webdriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.allinmd.po.androiddriver.TerminalPageElements;

public class TerminalWebElements {

	// 病例终端页标题
	@FindBy(id = TerminalPageElements.CASE_NAME)
	public static WebElement case_name;
	
	// 视频终端页标题
	@FindBy(id = TerminalPageElements.VIDEO_NAME)
	public static WebElement video_name;
	
	// 终端页转发按钮
	@FindBy(id = TerminalPageElements.TRANSPOND_BTN)
	public static WebElement transpond_btn;
	
	// 终端页回复按钮
	@FindBy(id = TerminalPageElements.REPLY_BTN)
	public static WebElement reply_btn;
	
	// 病例终端页收藏按钮
	@FindBy(id = TerminalPageElements.COLLECT_CASE_BTN)
	public static WebElement collect_case_btn;
	
	// 视频终端页收藏按钮
	@FindBy(id = TerminalPageElements.COLLECT_VIDEO_BTN)
	public static WebElement collect_video_btn;
	
	// 终端页回退到列表
	@FindBy(id = TerminalPageElements.TERMINAL_BACK)
	public static WebElement terminal_back;
}
