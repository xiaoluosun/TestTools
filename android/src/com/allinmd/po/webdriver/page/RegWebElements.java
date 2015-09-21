package com.allinmd.po.webdriver.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.allinmd.po.androiddriver.RegPageElements;
import com.allinmd.util.GetResetPasswd;
import com.allinmd.util.SaveUserName;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 注册流程
 * @author sun
 *
 */
public class RegWebElements {
	
	// 立即注册按钮
	@FindBy(id = RegPageElements.REG_BTN)
	public static WebElement reg_button;
	
	// 注册用户名输入框
	@FindBy(id = RegPageElements.CREATE_USER)
	private static WebElement create_username;
	
	// 提示文字
	@FindBy(xpath = RegPageElements.CON_TEXT)
	private static WebElement con_text;
	
	// 下一步按钮
	@FindBy(id = RegPageElements.CREATE_USER_NEXT)
	private static WebElement createuser_next;
	
	// 注册时手机验证码
	@FindBy(id = RegPageElements.PHONE_CODE)
	private static WebElement phone_code;
	
	// 注册的密码输入框
	@FindBy(id = RegPageElements.CREATE_PW)
	private static WebElement create_password;
	
	// 创建账户按钮
	@FindBy(id = RegPageElements.CREATE_CONFIRM)
	private static WebElement create_confirm;
	
    private static WebDriver driver;
  
    public RegWebElements(WebDriver driver){  
    	RegWebElements.driver = driver; 
    	new SaveUserName();
    	new GetResetPasswd();
    	PageFactory.initElements(driver, AuthWebElements.class);
    }
    
    /**
     * 判断"立即注册"按钮是否存在
     * @return 
     */
    public static boolean regBtnIsExist() {
    	return Utils.isElementExist(driver, By.id(RegPageElements.REG_BTN));
    }
    
    /**
     * 注册邮箱账户
     * @param username
     * @param password
     */
    public static void regEmailFlow(String username, String password) {
    	reg_button.click();
    	create_username.sendKeys(username); 
    	con_text.click();

    	createuser_next.click();

    	create_password.sendKeys(password);
    	if (create_confirm.isDisplayed())
    		create_confirm.click();
    	
		Assertion.assertEquals(true, assertRegEmail(username));
    }
      
    /**
     * 注册手机账户
     * @param username
     * @param password
     */
    public static void regPhoneFlow(String username, String password) {
    	reg_button.click();
    	create_username.sendKeys(username);
    	con_text.click();					//因为邮箱自动联动后缀，必须转移下焦点。
    	createuser_next.click();
    	
    	Utils.sleep(5);
    	phone_code.sendKeys(GetResetPasswd.getPhoneCode(username));
    	create_password.sendKeys(password);
    	create_confirm.click();
    	
		Assertion.assertEquals(true, assertRegPhone(username));
    }
    
    /**
     * 验证注册后页面跳转
     */
    public static boolean assertRegEmail(String username) {
    	boolean status = false;
    	if (AuthWebElements.skipAuthIsExist()) {
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
    	if (AuthWebElements.skipAuthIsExist()) {
    		SaveUserName.phoneName(username);
    		status = true;
    	}
    	
		return status;
    }
}
