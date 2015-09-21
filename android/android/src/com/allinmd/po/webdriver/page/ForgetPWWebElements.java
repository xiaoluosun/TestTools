package com.allinmd.po.webdriver.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.allinmd.util.GetResetPasswd;
import com.allinmd.util.Utils;

public class ForgetPWWebElements {
	
	// 忘记密码按钮
	@FindBy(id = "com.allin.social:id/tv_forget_pw")
	private static WebElement forget_pw_btn;
	
	// 用户名输入框
	@FindBy(id = "com.allin.social:id/et_forget_inputusername")
	private static WebElement username_input;
	
	// 确定按钮
	@FindBy(id = "com.allin.social:id/btn_forgetpw_confirm")
	private static WebElement forget_pw_confirm;
	
	// 手机验证码输入框
	@FindBy(id = "com.allin.social:id/et_input_phone_code")
	private static WebElement phone_code_input;
	
	// 发送找回密码邮件
	@FindBy(id = "com.allin.social:id/btn_findpwbyemail_confirm")
	private static WebElement send_email;
	
	// 进入邮箱按钮
	@FindBy(id = "com.allin.social:id/btn_into_email")
	private static WebElement into_email;
	
	// 设置新密码输入框
	@FindBy(id = "com.allin.social:id/et_set_newpw")
	private static WebElement new_password;
	
	// 新密码确认按钮
	@FindBy(id = "com.allin.social:id/btn_inputnewpw_confirm")
	private static WebElement newpw_confirm;
	
	private static WebDriver driver;
    
    public ForgetPWWebElements(WebDriver driver){  
    	ForgetPWWebElements.driver = driver; 
    	PageFactory.initElements(driver, LoginWebElements.class);
    	PageFactory.initElements(driver, AuthWebElements.class);
    	PageFactory.initElements(driver, IndexWebElements.class);
    	new GetResetPasswd();
    }
    
	public static boolean forgetEmail(String username) {
		LoginWebElements.login_allinmd_btn.click();
		forget_pw_btn.click();
		username_input.sendKeys(username);
		forget_pw_confirm.click();
		send_email.click();
		
		return Utils.isElementExist(driver, By.id("com.allin.social:id/btn_into_email"));
	}
	
	/**
	 * 手机找回密码
	 * @param username
	 * @return 
	 */
	public static boolean forgetPhone(String username, String password) {
		LoginWebElements.login_allinmd_btn.click();
		forget_pw_btn.click();
		username_input.sendKeys(username);
		forget_pw_confirm.click();
		Utils.sleep(4);
		phone_code_input.sendKeys(GetResetPasswd.getPhoneCode(username));
		new_password.sendKeys(password);
		newpw_confirm.click();
		
		AuthWebElements.skip_auth.click();
		return IndexWebElements.assertGoToIndex();
	}
}
