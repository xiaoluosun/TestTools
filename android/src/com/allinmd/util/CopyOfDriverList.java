package com.allinmd.util;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.allinmd.util.Dom4jXml;
import com.runtime.listener.LogEventListener;

public class CopyOfDriverList {
	public static AndroidDriver androiddriver;
	private static WebDriver webdriver;
	private static WebDriver browserdriver;
	
	/**
	 * 用WebDriver启动chrome浏览器
	 * @return
	 */
	public static WebDriver browserDriverRun() {
		DeviceParse.getDeviceInfo();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", Dom4jXml.getValue("platformName"));				//手机os     
        capabilities.setCapability("platformVersion", Dom4jXml.getValue("platformVersion"));		//真机的Android版本    
//        capabilities.setCapability("deviceName", Dom4jXml.getValue("deviceName"));					//手机厂商&机型
//        capabilities.setCapability("udid", Dom4jXml.getValue("udid"));								//设备ID 
        capabilities.setCapability("deviceName", DeviceParse.deviceName);
        capabilities.setCapability("udid", DeviceParse.udid);    
        capabilities.setCapability("browserName", Dom4jXml.getValue("browserName")); 
        capabilities.setCapability("unicodeKeyboard", true);										//使用unicodeKeyboard的编码方式来发送字符串
        capabilities.setCapability("resetKeyboard", true);											//隐藏键盘，和unicodeKeyboard结合可以输入中文，特殊字符，英文、数字的混合等。

		try {
			Utils.restartAppium();
			browserdriver = new EventFiringWebDriver(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities)
					).register(new LogEventListener());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		
		return browserdriver;
	}
	
	/**
	 * 用AndroidDriver启动allin
	 * @return
	 */
	public static AndroidDriver androidDriverRun() {
		try {
			Utils.restartAppium();
			androiddriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities(1));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		
		return androiddriver;
	}
	
	/**
	 * 用WebDriver启动allin
	 * @return
	 */
	public static WebDriver webDriverRun() {
		try {
			Utils.restartAppium();
			webdriver = new EventFiringWebDriver(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities(1))
					).register(new LogEventListener());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		
		return webdriver;
	}
	
	/**
	 * 用AndroidDriver启动re浏览器
	 * @return
	 */
	public static AndroidDriver reRun() {
		try {
			Utils.restartAppium();
			androiddriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities(2));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		
		return androiddriver;
	}
	
	/**
	 * 启动appium需要的一些参数
	 * @param tag
	 * @return
	 */
    public static DesiredCapabilities getCapabilities(int tag) {    
    	DesiredCapabilities capabilities = new DesiredCapabilities();
    	switch(tag){
			case 1:	     
				setCapability("appPackage", "appActivity");
		        break;
		        
			case 2:	
				setCapability("rePackage", "reActivity");			
		        break;
		        
			default:
				break;
    	}
    	
        return capabilities;
    } 
    
    public static DesiredCapabilities setCapability(String appPackage, String appActivity) {
    	DeviceParse.getDeviceInfo();
		System.out.println(DeviceParse.udid);
		System.out.println(DeviceParse.deviceName);
		
    	DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", Dom4jXml.getValue("platformName"));			//手机os	    
        capabilities.setCapability("platformVersion", Dom4jXml.getValue("platformVersion"));	//真机的Android版本	  
        capabilities.setCapability("deviceName", Dom4jXml.getValue("deviceName"));			//手机厂商&机型
        capabilities.setCapability("udid", Dom4jXml.getValue("udid"));						//设备ID
//        capabilities.setCapability("deviceName", "MI_4LTE");
//        capabilities.setCapability("udid", "5026a32e");
        capabilities.setCapability("appPackage", Dom4jXml.getValue(appPackage)); 
        capabilities.setCapability("appActivity", Dom4jXml.getValue(appActivity));
        capabilities.setCapability("unicodeKeyboard", true);									//使用unicodeKeyboard的编码方式来发送字符串
        capabilities.setCapability("resetKeyboard", true);										//隐藏键盘，和unicodeKeyboard结合可以输入中文，特殊字符，英文、数字的混合等。
        
        System.out.println(capabilities);
        return capabilities;
    }
    
    @Test
    public void f() {
		System.out.println(DeviceParse.udid);
		System.out.println(DeviceParse.deviceName);
    }
}
