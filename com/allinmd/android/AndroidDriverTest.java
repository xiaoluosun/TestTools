package com.allinmd.android;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.List;

public class AndroidDriverTest {
    private WebDriver driver;
    
    @Before
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));    
        File appDir = new File(classpathRoot, "apps");						//存放app目录：apps
        File app = new File(appDir, "ContactManager.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();       
        capabilities.setCapability("automationName","Selendroid"); 
        capabilities.setCapability("deviceName","MI_2A");        			//手机型号
        capabilities.setCapability("platformName","Android");				//手机os
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");       
        capabilities.setCapability(CapabilityType.VERSION, "4.1.1");		//真机的Android版本     
        capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");		//使用的是windows平台
        capabilities.setCapability("app", app.getAbsolutePath());			//应用的绝对路径
        driver = new SwipeableWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void addContact(){
    	
        WebElement el = driver.findElement(By.name("Add Contact"));
        el.click();
        List<WebElement> textFieldsList = driver.findElements(By.tagName("textfield"));
        textFieldsList.get(0).sendKeys("wangyang");
        textFieldsList.get(1).sendKeys("18872573204");
        textFieldsList.get(2).sendKeys("stephenwang@gmail.com");
        driver.findElement(By.name("Save")).click();
        
    }
    
    public class SwipeableWebDriver extends RemoteWebDriver implements HasTouchScreen {
        private RemoteTouchScreen touch;

        public SwipeableWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
            super(remoteAddress, desiredCapabilities);
            touch = new RemoteTouchScreen(getExecuteMethod());
        }

        public TouchScreen getTouch() {
            return touch;
        }
    }
	
}
