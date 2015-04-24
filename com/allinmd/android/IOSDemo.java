package com.allinmd.android;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.URL;

public class IOSDemo {
    private AppiumDriver driver;
    
    @Before
    public void setUp() throws Exception {
    	File appDir = new File(System.getProperty("user.dir"));
    	final File app = new File(appDir, "apps/TestApp/build/Release-iphonesimulator/TestApp.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();  
        capabilities.setCapability("platformName","IOS");				//手机os 
        capabilities.setCapability("platformVersion", "8.0");			//要启动的手机OS版本
        capabilities.setCapability("deviceName", "iPhone Simulator");	//手机类型或模拟器类型，比如MI_2A/Android Emulator/iPhone Simulator
//        capabilities.setCapability("udid","94122ad8");        		//物理机ID 
        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");		//使用的是Mac平台
        capabilities.setCapability("app", app.getAbsolutePath());		//得到app绝对路径
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
    }
    
    @After
    public void tearDown() throws Exception {
//        driver.quit();
    }
    
    @Test
    public void allinLogin() throws InterruptedException {		//登陆
    	final WebElement text = driver.findElement(By.xpath("//UIATextField[1]"));
    } 
}

