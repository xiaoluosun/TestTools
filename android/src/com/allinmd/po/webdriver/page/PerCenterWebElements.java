package com.allinmd.po.webdriver.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.allinmd.po.androiddriver.PerCenterPageElements;
import com.allinmd.util.Utils;

public class PerCenterWebElements {
	
	// 个人中心 action barName
	@FindBy(id = PerCenterPageElements.PER_BARNAME)
	public static WebElement per_barName;
	
	// 他人个人中心-添加关注
	@FindBy(id = PerCenterPageElements.ADD_ATTENTION)
	public static WebElement add_attention;
	
	// 他人个人中心-取消关注
	@FindBy(id = PerCenterPageElements.CANCEL_ATTENTION)
	public static WebElement cancel_attention;
	
	// 个人中心-发布的病例
	@FindBy(id = PerCenterPageElements.RELEASE_CASE)
	public static WebElement release_case;
	
	// 个人中心-发布的病例-病例title
	@FindBy(id = PerCenterPageElements.RELEASE_CASE_TITLE)
	public static WebElement release_case_title;
	
	// 个人中心-发布的话题
	@FindBy(id = PerCenterPageElements.RELEASE_TOPIC)
	public static WebElement release_topic;
	
	// 个人中心-发布的话题-话题title
	@FindBy(id = PerCenterPageElements.RELEASE_TOPIC)
	public static WebElement release_topic_title;
	
	// 个人中心-收藏的内容
	@FindBy(id = PerCenterPageElements.COLLECT_CONTENT)
	public static WebElement collect_content;
	
	// 个人中心-收藏的内容-病例
	@FindBy(id = PerCenterPageElements.COLLECT_CONTENT_CASE_BTN)
	public static WebElement collect_content_case_btn;
	
	// 个人中心-收藏的内容-视频
	@FindBy(id = PerCenterPageElements.COLLECT_CONTENT_VIDEO_BTN)
	public static WebElement collect_content_video_btn;
	
	// 个人中心-收藏的内容-话题
	@FindBy(id = PerCenterPageElements.COLLECT_CONTENT_TOPIC_BTN)
	public static WebElement collect_content_topic_btn;
	
	// 个人中心-收藏的内容-病例、视频、话题列表资源title
	@FindBy(id = PerCenterPageElements.COLLECT_CONTENT_TITLE)
	public static WebElement collect_content_title;
	
	// 个人中心-收藏的内容-评论
	@FindBy(id = PerCenterPageElements.COLLECT_CONTENT_COMMENT_BTN)
	public static WebElement collect_content_comment_btn;
	
	// 账户安全按钮
	@FindBy(id = PerCenterPageElements.SECURITY_BTN)
	public static WebElement security_btn;
	
	// 修改密码按钮
	@FindBy(id = PerCenterPageElements.UPDATE_PW)
	public static WebElement update_password;
	
	// 当前密码输入框
	@FindBy(id = PerCenterPageElements.CURRENT_PW)
	public static WebElement current_password;
	
	// 新密码输入框
	@FindBy(id = PerCenterPageElements.NEW_PW)
	public static WebElement new_password;
	
	// 确认密码输入框
	@FindBy(id = PerCenterPageElements.SURE_PW)
	public static WebElement sure_password;
	
	// 保存密码按钮
	@FindBy(id = PerCenterPageElements.SAVE_PW_BTN)
	public static WebElement save_password;
	
	// 账户安全action bar
	@FindBy(id = PerCenterPageElements.SECURITY_BAR_NAME)
	public static WebElement security_bar_name;
		
	// 订阅数量按钮
	@FindBy(id = PerCenterPageElements.TAB_NUM)
	public static WebElement subscription_num;
	
	public static WebDriver driver;
    
    public PerCenterWebElements(WebDriver driver){  
    	PerCenterWebElements.driver = driver; 
    }
    
    /**
     * 账户安全按钮是否存在
     * @return
     */
	public static boolean securityBtnIsExist() {
    	return Utils.isElementExist(driver, By.id(PerCenterPageElements.SECURITY_BTN));
	}
	
	/**
	 * 修改密码
	 * @param currentPassword
	 * @param newPassword
	 */
	public static void updatePassword(String currentPassword, String newPassword) {
		PerCenterWebElements.security_btn.click();
		PerCenterWebElements.update_password.click();
		PerCenterWebElements.current_password.sendKeys(currentPassword);
		PerCenterWebElements.new_password.sendKeys(newPassword);
		PerCenterWebElements.sure_password.sendKeys(newPassword);
		PerCenterWebElements.save_password.click();	
	}
	
	/**
	 * 修改密码后验证跳转
	 * @return
	 */
	public static boolean assertGoToSecurity() {
    	boolean status = false;
    	if (PerCenterWebElements.security_bar_name != null) {
    		return PerCenterWebElements.security_bar_name.getText().equals("账户安全");
    	}
		return status;
	}
} 
