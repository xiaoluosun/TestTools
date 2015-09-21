package com.allinmd.util;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.allinmd.util.Dom4jXml;

/**
 * 修改设备hosts
 * @author sun
 *
 */
public class AlterDeviceHosts {
	
	private static AndroidDriver driver;
	
	public static void setUp() {	
		driver = DriverList.reRun();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
	}
	
	public static void clickTag() {
        WebElement elA = driver.findElementByAccessibilityId("书签");
        elA.click();
        
        WebElement elB =  driver.findElement(By.xpath(".//android.widget.TextView[contains(@text, '/system/etc/hosts')]"));
        elB.click();
        
        WebElement elD = driver.findElement(By.xpath(".//android.widget.EditText"));
        elD.click();
        
        String content = elD.getAttribute("text");
        clearText(content);
	}
	
	public static void clearText(String text) {
		driver.sendKeyEvent(123);
		for (int i =0; i < text.length(); i++) 
			driver.sendKeyEvent(67);		
	}
	
	public static void inputText() {
		WebElement elD = driver.findElement(By.xpath(".//android.widget.ScrollView"));
        elD.sendKeys("192.168.1.26 m.allinmd.cn");
	}
	
	public static void saveHosts() {
        WebElement elB =  driver.findElement(By.xpath(".//android.widget.ImageButton"));
        elB.click();
        
        WebElement elD = driver.findElement(By.xpath(".//android.widget.LinearLayout[@index=0]"));
        elD.click();
	}
    
	@Test
	public static void alterHosts() {
		int num = Integer.parseInt(Dom4jXml.getValue("switch"));
    	switch(num){
			case 1:	
				setUp();
				clickTag();
				saveHosts();
				break;
			case 2:	
				setUp();
				clickTag();
				inputText();
				saveHosts();
				break;
				
    		default:
    			break;
    	}
	}
}
