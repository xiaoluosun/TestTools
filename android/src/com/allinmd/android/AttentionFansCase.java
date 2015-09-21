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

import com.allinmd.po.androiddriver.IndexPageElements;
import com.allinmd.po.androiddriver.ListPageElements;
import com.allinmd.po.androiddriver.LoginPageElements;
import com.allinmd.po.androiddriver.PerCenterPageElements;
import com.allinmd.util.DriverList;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 添加\取消关注-查看关注、粉丝列表
 * @author sun
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class AttentionFansCase {
	private static AndroidDriver driver;
	private static String docName;
	
	@BeforeClass
	public void setUp() {
		driver = DriverList.androidDriverRun();
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	new LoginPageElements(driver);
    	new IndexPageElements(driver);
    	new ListPageElements(driver);
    	new PerCenterPageElements(driver);
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
		
		WebElement eIndexCaseBtn = driver.findElement(By.id(IndexPageElements.INDEX_USER_BTN));
		eIndexCaseBtn.click();
		
		List<WebElement> eDoctorName = driver.findElements(By.id(ListPageElements.DOCTOR_NAME));
		docName = eDoctorName.get(1).getText();
		eDoctorName.get(1).click();
		
		Assertion.assertEquals(docName, Utils.getText(driver, By.id(PerCenterPageElements.PER_NAME)), "进入医师个人中心错误，请检查！");
	}
	
	/**
	 * 添加/取消关注
	 */
	@Test (priority = 2)
	public void attention() {
		WebElement eAddAttention = driver.findElement(By.id(PerCenterPageElements.ADD_ATTENTION));	
		if (eAddAttention.getText().equals("已经关注")) {
			eAddAttention.click();
		}
		
		eAddAttention.click();
		Assertion.assertEquals("已经关注", Utils.getText(driver, By.id(PerCenterPageElements.ADD_ATTENTION)), "关注医师失败，请检查！");
		
		eAddAttention.click();
		
		WebElement eCancelAttention = driver.findElement(By.id(PerCenterPageElements.CANCEL_ATTENTION));
		eCancelAttention.click();
		
		Assertion.assertEquals("添加关注", Utils.getText(driver, By.id(PerCenterPageElements.ADD_ATTENTION)), "取消关注医师失败，请检查！");
	}
	
	/**
	 * 查看医师关注列表
	 */
	@Test (priority = 3)
	public void checkAttention() {
		WebElement eAttentionNum = driver.findElement(By.id(PerCenterPageElements.ATTENTION_NUM));
		if (Integer.parseInt(eAttentionNum.getText()) > 0) {
			System.out.println(docName + "共关注" + eAttentionNum.getText() + "个医师");
			eAttentionNum.click();
			
			List<WebElement> eDoctorName = driver.findElements(By.id(ListPageElements.DOCTOR_NAME));
			System.out.println("输出前" + eDoctorName.size() + "个关注医师：");
			for (int i = 0; i < eDoctorName.size() - 1; i++) {
				System.out.println(eDoctorName.get(i).getText());
			}			
			
			WebElement eBack = driver.findElement(By.id(PerCenterPageElements.BACK_PER));
			eBack.click();	
		} else {
			System.err.println(docName + "没有关注医师！");
		}		
	}
	
	/**
	 * 查看医师粉丝列表
	 */
	@Test (priority = 4)
	public void checkFans() {
		WebElement eFansNum = driver.findElement(By.id(PerCenterPageElements.FANS_NUM));
		if (Integer.parseInt(eFansNum.getText()) > 0) {
			System.out.println("\n" + docName + "共有" + eFansNum.getText() + "个粉丝");
			eFansNum.click();
			
			List<WebElement> eDoctorName = driver.findElements(By.id(ListPageElements.DOCTOR_NAME));
			System.out.println("输出前" + eDoctorName.size() + "个粉丝名字：");
			for (int i = 0; i < eDoctorName.size() - 1; i++) {
				System.out.println(eDoctorName.get(i).getText());
			}		
			
			WebElement eBack = driver.findElement(By.id(PerCenterPageElements.BACK_PER));
			eBack.click();	
		} else {
			System.err.println(docName + "没有粉丝！");
		}
	}	
}
