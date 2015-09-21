package com.allinmd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 封装webdriver的一些基本操作
 * sendKeys、切换窗口、判断元素是否存在等等
 * @author sun
 *
 */
public class WebDriverUtils {
	
	/**
	 * 重新启动Appium
	 */
	public static void restartAppium() {
		killProcess(4723);
		cmd("cmd /c C:\\Users\\sun\\Desktop\\SoftWare\\AppiumServer.vbs");
	}
	
	/**
	 * 传入端口号终止本地进程
	 * @param port 
	 */
	public static void killProcess(int port) {
        String[] str = null;
        String temp = null;
        String line = null;
        String commant = "cmd /c netstat -ano | findstr " + port + " | findstr  LISTENING";
		try {
			Process process = Runtime.getRuntime().exec(commant);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));	 			
	        while ((line = reader.readLine()) != null) {
//	        	System.out.println(line.toString());
	        	str = line.toString().split(" ");		
				for(String s: str) {
					temp = s;
				}			
				Runtime.getRuntime().exec("taskkill /F /PID " + temp);	        	
	        }
	        process.waitFor();
	     
		} catch (IOException | InterruptedException e) {
			e.getMessage();
		}
	} 
			
	/**
	 * 执行dos命令
	 * @param command
	 */
	public static void cmd(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行Thread.sleep
	 * @param d
	 */
	public static void sleep(double d) {
		try {
			d *= 1000;
			Thread.sleep((int)d);
		} catch(Exception e) {}
	}
	
    /**
     * 模拟键盘输入
     * @param by 定位器：id,name,classname,xpath....
     * @param value	input的值
     */
	public void sendKeys(WebDriver driver, By by, String value){
    	driver.findElement(by).sendKeys(value);
    }
    
    /**
     * 点击事件
     * @param by 
     */
	public void click(WebDriver driver, By by){
        driver.findElement(by).click(); 
    }
    
    /**
     * 判断元素是否存在
     * @param reg_button
     * @return true or false
     */
	public static boolean isElementExist(WebDriver driver, By by) { 
        try { 
            driver.findElement(by);
            return true; 
        } catch(NoSuchElementException e) { 
            return false; 
        } 
    } 
    
    /**
     * 取得元素文本
     * @param by
     * @return 未找到返回"Text not existed!"
     */
	public String getText(WebDriver driver, By by) { 
        try { 
        return driver.findElement(by).getText();
        } catch (NoSuchElementException e) { 
            return "Text not existed!"; 
        } 
    } 
    
    /**
     * 验证是否包含text，如包含则click
     * @param by
     * @param text
     */
	public void clickElementContainingText(WebDriver driver, By by, String text){
        List<WebElement>elementList = driver.findElements(by);
        for(WebElement e:elementList){
            if(e.getText().contains(text)){
            	e.click();
                break;
            }
        }     
    }
    
    /**
     * 传入text，得到href属性的值
     * @param by
     * @param text 
     * @return
     */
	public String getLinkUrlContainingText(WebDriver driver, By by, String text){
        List<WebElement>subscribeButton = driver.findElements(by);
        String url = null;
        for(WebElement e:subscribeButton){
            if(e.getText().contains(text)){
                url = e.getAttribute("href");
                break;
            }
        }
        return url;
    }
    
    /**
     * 得到href属性的值
     * @param by
     * @return
     */
	public String getLinkUrl(WebDriver driver, By by){
        return driver.findElement(by).getAttribute("href");
    }
    
    /**
     * 传入title，得到窗口句柄然后切换窗口
     * @param windowTitle
     */
    public void switchToWindow(WebDriver driver, String windowTitle) {
    	Set<String> windowHandles = driver.getWindowHandles();
        for (String handler: windowHandles) {
            String title = driver.getTitle();
            if (title.equals(windowTitle)) {
            	driver.switchTo().window(handler);
                break;
            }
        }
    }
    
    /**
     * 判断列表是否存在元素
     * @param by
     * @return true or false
     */
    public boolean isElementsExist(WebDriver driver, By by) {
        return (driver.findElements(by).size()) > 0 ? true : false;
    }
    	
    /**
     * 获取隐藏节点的属性值
     * @param parentNode  事件源节点的xpath
     * @param childNode  目标节点的xpath
     * @param attribute 要获取目标节点的哪个属性
     */
    public String getHiddenAttribute(WebDriver driver, By parentNode,By childNode,String attribute){
    	driver.findElement(parentNode).click();
        return  driver.findElement(childNode).getAttribute(attribute);
    }
    
    /**
     * 获取隐藏节点的文本
     * @param parentNode  事件源节点的定位
     * @param childNode  目标节点的定位
     * @param attribute 要获取目标节点的哪个属性
     */
    public String getHiddenText(WebDriver driver, By parentNode,By childNode,String attribute){
    	driver.findElement(parentNode).click();
        return  driver.findElement(childNode).getText();
    }
    
    /**
     * Appium + Android的启动参数
     * @return
     * @throws Exception
     */
    public static DesiredCapabilities androidRun() {
        DesiredCapabilities capabilities = new DesiredCapabilities();       
        capabilities.setCapability("platformName","Android");				//手机os     
        capabilities.setCapability("platformVersion", "4.4.2");		//真机的Android版本    
        capabilities.setCapability("deviceName", "HUAWEI_MT7_TL00");
        capabilities.setCapability("udid","P4M7N15430018890");        		//物理机ID 
        capabilities.setCapability("appPackage","com.allin.social"); 
        capabilities.setCapability("appActivity",".loading.BootingActivity");
//        capabilities.setCapability("browserName", "Chrome");				//要启动的手机浏览器
        capabilities.setCapability("unicodeKeyboard", true);				//使用unicodeKeyboard的编码方式来发送字符串
        capabilities.setCapability("resetKeyboard", true);					//隐藏键盘，和unicodeKeyboard结合可以输入中文，特殊字符，英文、数字的混合等。
        
        return capabilities;
    }
}
