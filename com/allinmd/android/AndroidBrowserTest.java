package com.allinmd.android;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidBrowserTest {
    private WebDriver driver;
    
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();       
        capabilities.setCapability("automationName","Selendroid"); 			//自动化引擎
        capabilities.setCapability("platformName","Android");				//手机os     
        capabilities.setCapability("platformVersion", "4.1.1");		//真机的Android版本     
        capabilities.setCapability("udid","94122ad8");        		//物理机ID 
        capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");		//使用的是windows平台
        capabilities.setCapability("browserName", "Chrome");				//要启动的手机浏览器
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
    }
    
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
    
    @Test
    public void allinLogin() throws InterruptedException {		//登陆
		driver.get("http://m.allinmd.cn");
		driver.findElement(By.xpath("html/body/section/div[1]/div[2]/a/div")).click();
		driver.findElement(By.xpath(".//*[@id='allinLoginPage']/div[2]/div[1]/a[2]")).click();
		driver.findElement(By.id("email")).sendKeys("test@smc.com");
		driver.findElement(By.name("password")).sendKeys("111111");
		driver.findElement(By.xpath(".//*[@id='loginBtn']")).click();	
    } 
}
