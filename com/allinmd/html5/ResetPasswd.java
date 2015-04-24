package com.allinmd.html5;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.allinmd.html5.GetResetUrl;

public class ResetPasswd {
    private WebDriver driver;
    private GetResetUrl url;
    
    public ResetPasswd(){
    	url = new GetResetUrl();
    }
    
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();       
        capabilities.setCapability("automationName","Selendroid"); 			//自动化引擎
        capabilities.setCapability("platformName","Android");				//手机os     
        capabilities.setCapability("platformVersion", "4.1.1");		//真机的Android版本    
        capabilities.setCapability("deviceName", "MI_2A");
        capabilities.setCapability("udid","94122ad8");        		//物理机ID 
        capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");		//使用的是windows平台
        capabilities.setCapability("browserName", "Chrome");				//要启动的手机浏览器
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
    }
    
    @After
    public void closeDriver() throws Exception {
//        driver.quit();
    }
    
    @Test
    public void f() throws Exception{
//		driver.get(url.getUrl());
    }
}
