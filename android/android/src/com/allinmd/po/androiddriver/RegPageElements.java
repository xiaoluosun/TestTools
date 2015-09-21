package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.allinmd.util.GetResetPasswd;
import com.allinmd.util.SaveUserName;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 注册流程
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class RegPageElements {
	
	// 立即注册按钮
	public static final String REG_BTN = "com.allin.social:id/btn_login_reg";
	
	// 注册用户名输入框
	public static final String CREATE_USER = "com.allin.social:id/et_create_username";
	
	// 提示文字(xpath)
	public static final String CON_TEXT = "//*[contains(@text,'输入你的用户名,创建新账户')]";
	
	// 下一步按钮
	public static final String CREATE_USER_NEXT = "com.allin.social:id/btn_createuser_next";
	
	// 注册时手机验证码
	public static final String PHONE_CODE = "com.allin.social:id/et_input_phone_code";
	
	// 注册的密码输入框
	public static final String CREATE_PW = "com.allin.social:id/et_create_password";
	
	// 创建账户按钮
	public static final String CREATE_CONFIRM = "com.allin.social:id/btn_create_confirm";
	
	// 错误提示
	public static final String ERROR_MESSAGE = "com.allin.social:id/tv_error_message";
	
    private static AndroidDriver driver;
  
    public RegPageElements(AndroidDriver driver){  
    	RegPageElements.driver = driver; 
    	new SaveUserName();
    	new GetResetPasswd();
    	new AuthPageElements(driver);
    }
    
    /**
     * 判断"立即注册"按钮是否存在
     * @return 
     */
    public static boolean regBtnIsExist() {
    	return Utils.isElementExist(driver, By.id(REG_BTN));
    }
    
    /**
     * 注册邮箱账户
     * @param username
     * @param password
     */
    public static void regEmailFlow(String username, String password) {
		WebElement reg_button = driver.findElement(By.id(REG_BTN));  	
    	reg_button.click();
    	
    	WebElement create_username = driver.findElement(By.id(CREATE_USER)); 
    	create_username.sendKeys(username); 
    	
    	WebElement con_text = driver.findElement(By.xpath(CON_TEXT)); 
    	con_text.click();

    	WebElement createuser_next = driver.findElement(By.id(CREATE_USER_NEXT)); 
    	createuser_next.click();

    	WebElement create_password = driver.findElement(By.id(CREATE_PW)); 
    	create_password.sendKeys(password);
    	
    	WebElement create_confirm = driver.findElement(By.id(CREATE_CONFIRM)); 
    	    	
    	if (create_confirm.isDisplayed()) {
    		create_confirm.click();
    	}
    	Assertion.assertEquals(false, Utils.isElementExist(driver, By.id(ERROR_MESSAGE)), Utils.getText(driver, By.id(ERROR_MESSAGE)));

    	Assertion.assertEquals(true, assertRegEmail(username));
    }
      
    /**
     * 注册手机账户
     * @param username
     * @param password
     */
    public static void regPhoneFlow(String username, String password) {
		WebElement reg_button = driver.findElement(By.id(REG_BTN));  	
    	reg_button.click();
    	
    	WebElement create_username = driver.findElement(By.id(CREATE_USER)); 
    	create_username.sendKeys(username); 
    	  	
    	WebElement con_text = driver.findElement(By.xpath(CON_TEXT)); 
    	con_text.click();					//因为邮箱自动联动后缀，必须转移下焦点。
    	   	
    	WebElement createuser_next = driver.findElement(By.id(CREATE_USER_NEXT)); 
    	createuser_next.click();
    	
    	Utils.sleep(5);
    	WebElement phone_code = driver.findElement(By.id(PHONE_CODE));
    	if (GetResetPasswd.getPhoneCode(username).equals("404")) {
    		Assertion.assertEquals(false, true, "接口未返回数据，请检查！");
    	} else {
    		phone_code.sendKeys(GetResetPasswd.getPhoneCode(username));
    	
	    	WebElement create_password = driver.findElement(By.id(CREATE_PW)); 
	    	create_password.sendKeys(password);
	
	    	WebElement create_confirm = driver.findElement(By.id(CREATE_CONFIRM)); 
	    		    	
	    	if (create_confirm.isDisplayed()) {
	    		create_confirm.click();
    		}
	    	Assertion.assertEquals(false, Utils.isElementExist(driver, By.id(ERROR_MESSAGE)), Utils.getText(driver, By.id(ERROR_MESSAGE)));

	    	Assertion.assertEquals(true, assertRegPhone(username));
    	}
    }
    
    /**
     * 验证注册后页面跳转
     */
    public static boolean assertRegEmail(String username) {
    	boolean status = false;
    	if (AuthPageElements.skipAuthIsExist()) {
    		SaveUserName.emailName(username);
    		status = true;
    	}
    	
		return status;
    }
    
    /**
     * 验证注册后页面跳转
     */
    public static boolean assertRegPhone(String username) {
    	boolean status = false;
    	if (AuthPageElements.skipAuthIsExist()) {
    		SaveUserName.phoneName(username);
    		status = true;
    	}
    	
		return status;
    }
}
