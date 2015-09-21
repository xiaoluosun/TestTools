package cre.bigclient_order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ImportOrder {
	
  @Test
  public void f() {	  
	  UpdateExcel op = new UpdateExcel();
		op.connMysql();
		
//		System.setProperty("webdriver.firefox.bin","D:\\FireFox\\firefox.exe");
		WebDriver driver = new FirefoxDriver();
//		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://cre55.ips.cn/login/index.html");
		driver.manage().window().maximize();
		
		try {			
			Thread.sleep(1500);
			WebElement username = driver.findElement(By.id("username"));
			username.sendKeys("cre_admin");
			WebElement password = driver.findElement(By.id("password"));
			password.sendKeys("123456");
			WebElement sub = driver.findElement(By.className("login_btn"));
			sub.click();
	        
			Thread.sleep(2000);
	        driver.get("http://cre55.ips.cn/zptorder/import_client.html");
	        Thread.sleep(1500);
	        if (driver.getTitle().equals("大客户导入 - G7")){
	        	System.out.println("登陆成功！\n");        	
	        	
	        	Select select = new Select(driver.findElement(By.id("names")));
	        	select.selectByValue("测试大客户101");
	        	
	        	Thread.sleep(1000);
	        	String path = System.getProperty("user.dir");
	        	driver.findElement(By.xpath("//*[@id='importfile']")).
	            sendKeys(path+"\\data\\cre_hailan\\大客户运单导入模板.xlsx");
	        	driver.findElement(By.id("btnsubmit")).click();
	        	Thread.sleep(2000);
	        	
	        	String currentWindow = driver.getWindowHandle();
				driver.switchTo().window(currentWindow);
	            driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/div/input")).click();
	   
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
