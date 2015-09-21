package sqrz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ImportOrder {
	
  @Test
  public void f() {	  
		OpExcel op = new OpExcel();
		op.connMysql();
		
//		System.setProperty("webdriver.firefox.bin","D:\\FireFox\\firefox.exe");
		WebDriver driver = new FirefoxDriver();
//		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://sqrz55.ips.cn");
		driver.manage().window().maximize();
		
		try {			
			Thread.sleep(1500);
			WebElement username = driver.findElement(By.id("username"));
			username.sendKeys("sqrz_admin");
			WebElement password = driver.findElement(By.id("password"));
			password.sendKeys("admin123");
			WebElement sub = driver.findElement(By.className("login_btn"));
			sub.click();
			
	        Thread.sleep(2000); 
	        if (driver.getTitle().equals("首页 - G7")){
	        	System.out.println("登陆成功！\n");        	
	        	driver.get("http://sqrz55.ips.cn/ordercenter/import.html");
	        	
	        	Thread.sleep(1000);
	        	String path = System.getProperty("user.dir");
	        	driver.findElement(By.xpath("//*[@id='importfile']")).
	            sendKeys(path+"\\data\\sqrz\\运单导入模板.xlsx");
	        	driver.findElement(By.id("btnsubmit")).click();
	        	Thread.sleep(2000);
	            driver.findElement(By.className("ui_state_highlight")).click();
	   
	            String succeed = driver.findElement(By.xpath("//span[@id='success_count']")).getText();     //得到成功导入和失败导入的数目
	            String error = driver.findElement(By.xpath("//span[@id='error_count']")).getText();               
	            if (Integer.parseInt(succeed) >= 0){
	            	System.out.println("成功导入"+succeed+"条数据");
	            	System.out.println("失败"+error+"条数据");
	            }           
	        } else {
	        	System.out.println("登陆失败，请重新登陆！");
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				Thread.sleep(2000);
				driver.quit();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 			
		}
  }
}
