package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.allinmd.util.GetResetPasswd;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

@Listeners({com.runtime.listener.AssertionListener.class})
public class ForgetPWPageElements {
	
	// 忘记密码按钮
	private static final String FORGET_PW_BTN = "com.allin.social:id/tv_forget_pw";
	
	// 用户名输入框
	private static final String USERNAME_INPUT = "com.allin.social:id/et_forget_inputusername";
	
	// 确定按钮
	private static final String FORGET_PW_CONFIRM = "com.allin.social:id/btn_forgetpw_confirm";
	
	// 手机验证码输入框
	private static final String PHONE_CODE_INPUT = "com.allin.social:id/et_input_phone_code";
	
	// 发送找回密码邮件
	private static final String SEND_EMAIL = "com.allin.social:id/btn_findpwbyemail_confirm";
	
	// 进入邮箱按钮
	private static final String INTO_EMAIL = "com.allin.social:id/btn_into_email";
	
	// 设置新密码输入框
	private static final String NEW_PASSWORD = "com.allin.social:id/et_set_newpw";
	
	// 新密码确认按钮
	private static final String NEW_PW_CONFIRM = "com.allin.social:id/btn_inputnewpw_confirm";
	
	// 密码修改成功提示
	private static final String SUCCEED_MESSAGE = "com.allin.social:id/textView1";
	
	// 进入首页按钮
	private static final String GO_INDEX_BTN = "com.allin.social:id/btn_updateSuccessfully_back";
	
	// 邮箱-新密码吗输入框
	public static final String EMAIL_PW_INPUT = "password-hide";
	
	// 邮箱-设置密码确定
	public static final String EMAIL_PW_CONFIRM_BTN = "loginBtn";
	
	// 邮箱-设置密码确定(className)
	public static final String SUCCEED_TEXT = "para-fuwu";
	
	private static AndroidDriver driver;
    
    public ForgetPWPageElements(AndroidDriver driver){  
    	ForgetPWPageElements.driver = driver; 
    	new LoginPageElements(driver);
    	new AuthPageElements(driver);
    	new IndexPageElements(driver);
    	new GetResetPasswd();
    }
    
    /**
     * 邮箱找回密码，发送重置链接
     * @param username
     * @return
     */
	public static boolean forgetEmail(String username) {
		WebElement login_allinmd_btn = driver.findElement(By.id(LoginPageElements.LOGIN_ALLINMD_BTN));
		login_allinmd_btn.click();
		
		WebElement forget_pw_btn = driver.findElement(By.id(FORGET_PW_BTN));
		forget_pw_btn.click();
		
		WebElement username_input = driver.findElement(By.id(USERNAME_INPUT));
		username_input.sendKeys(username);
		
		WebElement forget_pw_confirm = driver.findElement(By.id(FORGET_PW_CONFIRM));
		forget_pw_confirm.click();
		
		WebElement send_email = driver.findElement(By.id(SEND_EMAIL));
		send_email.click();
		
		return Utils.isElementExist(driver, By.id(INTO_EMAIL));
	}
	
	/**
	 * 手机找回密码
	 * @param username
	 * @return 
	 */
	public static boolean forgetPhone(String username, String password) {
		WebElement login_allinmd_btn = driver.findElement(By.id(LoginPageElements.LOGIN_ALLINMD_BTN));
		login_allinmd_btn.click();
		
		WebElement forget_pw_btn = driver.findElement(By.id(ForgetPWPageElements.FORGET_PW_BTN));
		forget_pw_btn.click();
		
		WebElement username_input = driver.findElement(By.id(ForgetPWPageElements.USERNAME_INPUT));
		username_input.sendKeys(username);
		
		WebElement forget_pw_confirm = driver.findElement(By.id(ForgetPWPageElements.FORGET_PW_CONFIRM));
		forget_pw_confirm.click();
				
		Utils.sleep(4);
		WebElement phone_code_input = driver.findElement(By.id(ForgetPWPageElements.PHONE_CODE_INPUT));
    	if (GetResetPasswd.getPhoneCode(username).equals("404")) {
    		Assertion.assertEquals(false, true, "接口未返回数据，请检查！");
    	} else {
			phone_code_input.sendKeys(GetResetPasswd.getPhoneCode(username));
			
			WebElement new_password = driver.findElement(By.id(ForgetPWPageElements.NEW_PASSWORD));
			new_password.sendKeys(password);
			
			WebElement newpw_confirm = driver.findElement(By.id(ForgetPWPageElements.NEW_PW_CONFIRM));
			newpw_confirm.click();
			
			if (Utils.isElementExist(driver, By.id(SUCCEED_MESSAGE))) {
				Assertion.assertEquals(true, Utils.getText(driver, By.id(SUCCEED_MESSAGE)).equals("你已经成功修改了密码"));
				
				WebElement go_index_btn = driver.findElement(By.id(GO_INDEX_BTN));
				go_index_btn.click();	
				
				IndexPageElements.knowMessage();
				
			} else {
				Assertion.assertEquals(true, Utils.isElementExist(driver, By.id(SUCCEED_MESSAGE)), "密码重置失败，请检查！");
			}
			
    	}
    	
		return IndexPageElements.assertGoToIndex();
	}
	
	/**
	 * 邮箱找回密码，访问H5重置
	 * @param password
	 */
	public static void forgetH5Email(String username, String password) {

		
		Assertion.assertEquals(true, Utils.isElementExist(driver, By.id(EMAIL_PW_INPUT)));
	
		WebElement email_pw_input = driver.findElement(By.id(EMAIL_PW_INPUT));
		email_pw_input.sendKeys(password);
		
		WebElement email_pw_confirm_btn = driver.findElement(By.id(EMAIL_PW_CONFIRM_BTN));
		email_pw_confirm_btn.click();
		
		if (Utils.isElementExist(driver, By.id(SUCCEED_TEXT))) {
			Assertion.assertEquals("你已经成功修改了密码", Utils.getText(driver, By.id(SUCCEED_TEXT)), Utils.getText(driver, By.id(SUCCEED_TEXT)));
		} else {
			Assertion.assertEquals(true, false, "密码修改失败，请检查！");
		}
	}
}
