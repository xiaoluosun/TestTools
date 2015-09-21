package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.MainMenuPageElements;
import com.allinmd.po.androiddriver.PerCenterPageElements;
import com.allinmd.po.androiddriver.SubscribePageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 添加\取消订阅-查看订阅
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class SubscribeEditCase {
	private static AndroidDriver driver;
	private static String tabName;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new LoginPageElements(driver);
    	new MainMenuPageElements(driver);
    	new PerCenterPageElements(driver);
    	new SubscribePageElements(driver);
	}
	
	@AfterClass  
	public void tearDown() {	
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
	} 
	
	/**
	 * 登录并进入医师个人中心
	 * @param username
	 * @param password
	 */
	@Test (priority = 1)
	@Parameters({"LOGIN_PHONE_USER", "GLOBAL_PASSWORD"})
	public void allinLogin(String username, String password) {
		LoginPageElements.allinLogin(username, password);
		
		MainMenuPageElements.goPersonal();
	}
	
	/**
	 * 添加订阅
	 */
	@Test (priority = 2)
	public void addSubscribe() {
		WebElement eTabNum = driver.findElement(By.id(PerCenterPageElements.TAB_NUM));
		eTabNum.click();
		
		WebElement eAddTab = driver.findElement(By.id(SubscribePageElements.ADD_SUBSCRIBE));
		eAddTab.click();
		
		List<WebElement> eSelectTab = driver.findElements(By.id(SubscribePageElements.SELECT_TAB));
		List<WebElement> eTabName = driver.findElements(By.id(SubscribePageElements.TAB_NAME));		
		for (int i = 0; i < eSelectTab.size() - 1; i++) {
			if (eSelectTab.get(i).getAttribute("checked").equals("false")) {
				eSelectTab.get(i).click();
				tabName = eTabName.get(i).getText();
				break;
			}
		}
		
		WebElement eBack = driver.findElement(By.id(SubscribePageElements.BACK));
		eBack.click();	
	}
	
	/**
	 * 验证已添加的标签并删除订阅
	 */
	@Test (priority = 3)
	public void checkSubscribe() {
		List<WebElement> eTabName = driver.findElements(By.id(SubscribePageElements.TAB_NAME));	
		boolean flag = false;
		for (int i = 0; i < eTabName.size() - 1; i++) {
			if (eTabName.get(i).getText().equals(tabName)) {
				flag = true;
				Assertion.assertEquals(true, flag, "已订阅列表找到[" + tabName + "], 添加订阅成功!");
				
				WebElement eEditSub = driver.findElement(By.id(SubscribePageElements.EDIT_SUBSCRIBE));
				eEditSub.click();	
				
				eTabName.get(i).click();
				
				WebElement eDeleteSub = driver.findElement(By.id(SubscribePageElements.DELETE_SUBSCRIBE));
				eDeleteSub.click();	
				
				List<WebElement> eTabName2 = driver.findElements(By.id(SubscribePageElements.TAB_NAME));	
				boolean flag2 = true;
				for (int j = 0; j < eTabName2.size(); j++) {
					if (eTabName2.get(i).getText().equals(tabName)) {
						flag2 = false;
						Assertion.assertEquals(false, flag2, "还可以找到标签[" + tabName + "], 删除订阅失败!");
					} 
				}			
				Assertion.assertEquals(true, flag2, "未找到标签[" + tabName + "], 删除订阅成功!");
			}
		}		
		Assertion.assertEquals(true, flag, "没有找到标签[" + tabName + "], 添加订阅失败，请检查!");
	}
}
