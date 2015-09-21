package com.runtime.listener;

import java.io.File;
import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Selenium 监听类
 * @author sun
 *
 */
public class LogEventListener implements WebDriverEventListener {
	
	/**
	 *  异常事件，定义在使用Selenium测试发生异常时需要执行的代码。
	 */
	public void onException(Throwable ex, WebDriver driver) {
		try {
			screenshot((TakesScreenshot)driver, generateRandomFilename(ex));
		} catch (java.nio.channels.ClosedByInterruptException e){
			e.printStackTrace();
		}
		

	}
	/**
	 *  调用TakesScreenshot接口的getScreenshotAs方法对屏幕截图
	 * @param driver
	 * @param filename
	 */
	public void screenshot(TakesScreenshot driver, String filename) throws ClosedByInterruptException {
		String currentPath = System.getProperty("user.dir"); // get current work
		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			System.out.println("保存异常截图:" + currentPath + "\\screenshot\\"
					+ filename);
			FileUtils.copyFile(scrFile, new File(currentPath + "\\screenshot\\" + filename));
		} catch (IOException e) {
			System.out.println("保存异常截图失败！");
			e.printStackTrace();
		}
	}
	/*
    // 生成错误截图
    private void createScreenCaptureJPEG(String filename) {
    	try {
    		BufferedImage img = getScreenAsBufferedImage();
    		File output = new File(filename);
    		ImageIO.write(img, "jpg", output);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    // 得到当前屏幕截图
    private BufferedImage getScreenAsBufferedImage() {
    	BufferedImage img = null;
    	try {
    		Robot r;
    		r = new Robot();
    		Toolkit t = Toolkit.getDefaultToolkit();
    		Rectangle rect = new Rectangle(t.getScreenSize());
    		img = r.createScreenCapture(rect);
    	} catch (AWTException e) {
    		e.printStackTrace();
    	}
    	return img;
	}
    */
    /**
     * 错误截图前，生成图片名字
     * @param ex
     * @return
     */
    private String generateRandomFilename(Throwable ex) {
        Calendar c = Calendar.getInstance();
        String filename = ex.getMessage();
        if (filename != null) {
	        int i = filename.indexOf('\n');
	        filename = filename.substring(0, i).replaceAll("\\s", "_").replaceAll(":", "").replaceAll("/", "_") + ".jpg";
	        filename = "" + c.get(Calendar.YEAR) + 
	        		"-" + c.get(Calendar.MONTH) + 
	        		"-" + c.get(Calendar.DAY_OF_MONTH) + 
	        		"-" + c.get(Calendar.HOUR_OF_DAY) + 
	        		"-" + c.get(Calendar.MINUTE) + 
	        		"-" + c.get(Calendar.SECOND) + 
	        		"-" + filename;
        } else {
        	filename = "" + c.get(Calendar.YEAR) + 
	        		"-" + c.get(Calendar.MONTH) + 
	        		"-" + c.get(Calendar.DAY_OF_MONTH) + 
	        		"-" + c.get(Calendar.HOUR_OF_DAY) + 
	        		"-" + c.get(Calendar.MINUTE) + 
	        		"-" + c.get(Calendar.SECOND)  + ".jpg";
        }
        
        return filename;
    }
    
    // 元素值变更后事件，定义Selenium更改元素的值后需要执行的代码。
	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// 单击元素后事件，定义Selenium在单击元素后需要执行的代码。
	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// 找到元素后事件，定义Selenium在找到元素后需要执行的代码。
	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		
	}
	
	// 浏览器后退后事件，定义浏览器在执行后退操作后需要执行的代码。
	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	
	// 浏览器前进后事件，定义浏览器在执行前进操作后需要执行的代码。
	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	
	// 导航后事件，定义页面在发生跳转后需要执行的代码。
	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// 脚本执行后事件，定义脚本执行后需要执行的代码。
	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// 元素值变更前事件，定义Selenium更改元素的值前需要执行的代码。
	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// 单击元素前事件，定义Selenium在单击元素前需要执行的代码。
	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// 查找元素前事件，定义Selenium在查找元素前需要执行的代码。
	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		
	}
	
	// 浏览器后退前事件，定义浏览器在执行后退操作前需要执行的代码。
	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	
	// 浏览器前进前事件，定义浏览器在执行前进操作前需要执行的代码。
	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	
	// 导航前事件，定义页面在发生跳转前需要执行的代码。
	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// 脚本执行前事件，定义脚本执行前需要执行的代码。
	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
 
}
