package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.allinmd.util.Utils;

public class PerCenterPageElements {
	
	// 个人中心 action barName
	public static final String PER_BARNAME = "com.allin.social:id/textView1";
	
	// 个人中心医生名字
	public static final String PER_NAME = "com.allin.social:id/tv_name";
	
	// 他人个人中心关注数量
	public static final String ATTENTION_NUM = "com.allin.social:id/tv_attention_number";
	
	// 他人个人中心粉丝数量
	public static final String FANS_NUM = "com.allin.social:id/tv_fans_number";
	
	// 他人个人中心-添加关注
	public static final String ADD_ATTENTION = "com.allin.social:id/tv_add_attention";
	
	// 他人个人中心-取消关注
	public static final String CANCEL_ATTENTION = "com.allin.social:id/cancel_attention";
	
	// 个人中心-发布的病例
	public static final String RELEASE_CASE = "com.allin.social:id/ll_release_of_cases";
	
	// 列表无数据提示
	public static final String ERROR_MESSAGE = "com.allin.social:id/tv_error_layout";
	
	// 发布的病例、话题等列表返回个人中心
	public static final String BACK_PER = "com.allin.social:id/rl_back";
	
	// 发布的病例、话题等barName
	public static final String LIST_TITLE = "com.allin.social:id/tv_title";
	
	// 个人中心-发布的话题
	public static final String RELEASE_TOPIC = "com.allin.social:id/ll_the_topic_of_release";
	
	// 个人中心-发布的病例、话题title
	public static final String RELEASE_CASE_TITLE = "com.allin.social:id/tv_title_content";
	
	// 个人中心-发布的视频
	public static final String RELEASE_VIDEO = "com.allin.social:id/ll_video_released";
	
	// 个人中心-发布的视频title
	public static final String RELEASE_VIDEO_TITLE = "com.allin.social:id/tv_video_title";
	
	// 个人中心-收藏的内容
	public static final String COLLECT_CONTENT = "com.allin.social:id/ll_the_content_of_the_collection";
	
	// 个人中心-收藏的内容-病例
	public static final String COLLECT_CONTENT_CASE_BTN = "com.allin.social:id/ll_collection_case";
	
	// 个人中心-收藏的内容-视频
	public static final String COLLECT_CONTENT_VIDEO_BTN = "com.allin.social:id/ll_collection_video";
	
	// 个人中心-收藏的内容-话题
	public static final String COLLECT_CONTENT_TOPIC_BTN = "com.allin.social:id/ll_collection_topic";
	
	// 个人中心-收藏的内容-病例、视频、话题列表资源title
	public static final String COLLECT_CONTENT_TITLE = "com.allin.social:id/tv_video_title";
	
	// 个人中心-收藏的内容-评论
	public static final String COLLECT_CONTENT_COMMENT_BTN = "com.allin.social:id/ll_collection_comment";	
	
	// 个人中心-收藏的内容-评论内容
	public static final String COLLECT_CONTENT_COMMENT_TEXT = "com.allin.social:id/tv_dynamic_content";
	
	// 个人中心-收藏的内容-评论的资源主题
	public static final String COLLECT_CONTENT_COMMENT_TITLE = "com.allin.social:id/tv_title_content";
		
	// 个人中心-回复的内容
	public static final String REPLY_CONTENT = "com.allin.social:id/ll_the_content_of_the_reply";
	
	// 个人中心-回复的内容-TEXT
	public static final String REPLY_CONTENT_TEXT = "com.allin.social:id/tv_dynamic_content";
	
	// 个人中心-回复的内容-资源标题
	public static final String REPLY_CONTENT_TITLE = "com.allin.social:id/tv_title_content";
	
	// 个人中心-浏览记录
	public static final String BROWSING_HISTORY = "com.allin.social:id/ll_browsing_history";
	
	// 个人中心-浏览记录-资源标题
	public static final String BROWSING_HISTORY_TITLE = "com.allin.social:id/tv_name";
	
	// 标签name
	public static final String TAB_NUM = "com.allin.social:id/tv_subscription_number";
	
	// 账户安全按钮
	public static final String SECURITY_BTN = "com.allin.social:id/ll_setting";
	
	// 修改密码按钮
	public static final String UPDATE_PW = "com.allin.social:id/rl_password";
	
	// 当前密码输入框
	public static final String CURRENT_PW = "com.allin.social:id/et_current_password_hint";
	
	// 新密码输入框
	public static final String NEW_PW = "com.allin.social:id/et_new_password_hint";
	
	// 确认密码输入框
	public static final String SURE_PW = "com.allin.social:id/et_srue_password_hint";
	
	// 保存密码按钮
	public static final String SAVE_PW_BTN = "com.allin.social:id/btn_save_password";
	
	// 账户安全action bar
	public static final String SECURITY_BAR_NAME = "com.allin.social:id/tv_title";

	public static AndroidDriver driver;
    
    public PerCenterPageElements(AndroidDriver driver){  
    	PerCenterPageElements.driver = driver; 
    }
    
    /**
     * 账户安全按钮是否存在
     * @return
     */
	public static boolean securityBtnIsExist() {
    	return Utils.isElementExist(driver, By.id(SECURITY_BTN));
	}
	
	/**
	 * 修改密码
	 * @param currentPassword
	 * @param newPassword
	 */
	public static void updatePassword(String currentPassword, String newPassword) {
		WebElement security_btn = driver.findElement(By.id(SECURITY_BTN));
		security_btn.click();
		
		WebElement update_password = driver.findElement(By.id(UPDATE_PW));
		update_password.click();
		
		WebElement current_password = driver.findElement(By.id(CURRENT_PW));
		current_password.sendKeys(currentPassword);
		
		WebElement new_password = driver.findElement(By.id(NEW_PW));
		new_password.sendKeys(newPassword);
		
		WebElement sure_password = driver.findElement(By.id(SURE_PW));
		sure_password.sendKeys(newPassword);
		
		WebElement save_password = driver.findElement(By.id(SAVE_PW_BTN));
		save_password.click();	
	}
	
	/**
	 * 修改密码后验证跳转
	 * @return
	 */
	public static boolean assertGoToSecurity() {
    	boolean status = false;
    	if (Utils.getText(driver, By.id(SECURITY_BAR_NAME)).equals("账号安全")) {
    		status = true;    		
    	}
		return status;
	}
}
