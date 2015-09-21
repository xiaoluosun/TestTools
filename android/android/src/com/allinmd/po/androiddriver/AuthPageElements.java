package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.allinmd.util.ReadWriteTxtFile;
import com.allinmd.util.Utils;

public class AuthPageElements {
	
	// 跳过认证按钮
	public static final String SKIP_AUTH = "com.allin.social:id/iv_error_close";
	
	// last name
	public static final String LAST_NAME = "com.allin.social:id/et_unth_name";
	
	// first name
	public static final String FIRST_NAME = "com.allin.social:id/et_unth_surname";
	
	// 医院
	public static final String HOSPITAL = "com.allin.social:id/rl_auth_hospital";
	
	//搜索输入框
	public static final String SEARCH_HOSPITAL = "com.allin.social:id/et_search_hospital";
	
	//选择医院
	public static final String HOSPITAL_LIST = "com.allin.social:id/tv_dialog_listview_item";
	
	// 职称
	public static final String JOB = "com.allin.social:id/rl_auth_job";
	
	// 选择职称
	public static final String CB_RESIDENT = "com.allin.social:id/cb_resident";
	
	// 保存职称\保存专业
	public static final String DIALOG_SAVE = "com.allin.social:id/btn_dialog_save";
	
	// 专业
	public static final String MAJOR = "com.allin.social:id/rl_auth_major";
	
	// 选择专业
	public static final String CHECKBOX = "com.allin.social:id/checkbox";
	
	// 医师资格证
	public static final String IDENTITY_PAPERS = "com.allin.social:id/rb_identity_papers";
	
	// 医师学历证
	public static final String EDUCATION_PAPERS = "com.allin.social:id/rb_education_papers";
	
	// 医师学位证
	public static final String DEGREE_PAPERS = "com.allin.social:id/rb_degree_papers";
	
	// 医师资格证号/医师学历证号/医师学位证号
	public static final String UNTH_PHYNUMBER = "com.allin.social:id/et_unth_phynumber";
	
	// 上传图片按钮
	public static final String UNTH_PHYPHOTO = "com.allin.social:id/im_update_phyPhoto";
	
	// 上传图片按钮 (xpath)
	public static final String UNLOAD_CONFIM = "//*[contains(@text,'从手机相册选取')]";
	
	// 选择图片(xpath)
	public static final String SELECT_PHOTO = "//android.widget.RelativeLayout[contains(@index,1)]";
	
	// 下一步（认证按钮）
	public static final String AUTH_BTN = "com.allin.social:id/btn_login_allmd";
	
	// 选择关注的专业(xpath)
	public static final String SELECT_MAJOR = "//*[contains(@text,'全部')]";
	
	// 选择关注的专业——点击下一步
	public static final String SELECT_MAJOR_NEXT_BTN = "com.allin.social:id/btn_att_major_next";
	
	// 取消上传头像按钮
	public static final String CLOSE_UPLOAD_PHOTO = "com.allin.social:id/iv_error_close";
	
	private static AndroidDriver driver;
    
    public AuthPageElements(AndroidDriver driver){  
    	AuthPageElements.driver = driver; 
    	new IndexPageElements(driver);
    }
       
    /**
     * 判断"跳过认证"按钮是否存在
     * @return 
     */
    public static boolean skipAuthIsExist() {
    	return Utils.isElementExist(driver, By.id(SKIP_AUTH));
    }
    
    /**
     * 医师认证Flow
     */
	public static void doctorCer() {
		WebElement last_name = driver.findElement(By.id(LAST_NAME));
		last_name.sendKeys(ReadWriteTxtFile.getLastName());
		
		WebElement first_name = driver.findElement(By.id(FIRST_NAME));
		first_name.sendKeys(ReadWriteTxtFile.getFirstName());
		
		// 选择医院
		WebElement hospital = driver.findElement(By.id(HOSPITAL));
		hospital.click();
		
		WebElement search_hospital = driver.findElement(By.id(SEARCH_HOSPITAL));
		search_hospital.sendKeys("医院");
		
		WebElement hospital_list = driver.findElement(By.id(HOSPITAL_LIST));
		hospital_list.click();
		
		//选择职称
		WebElement job = driver.findElement(By.id(JOB));		
		job.click();
		
		WebElement cb_resident = driver.findElement(By.id(CB_RESIDENT));
		cb_resident.click();
		
		WebElement dialog_save = driver.findElement(By.id(DIALOG_SAVE));
		dialog_save.click();
		
		//选择专业
		WebElement major = driver.findElement(By.id(MAJOR));
		major.click();
		
		WebElement checkbox = driver.findElement(By.id(CHECKBOX));
		checkbox.click();
		
		WebElement dialog_save2 = driver.findElement(By.id(DIALOG_SAVE));
		dialog_save2.click();
		
		//医师资格证号及上传照片
		WebElement identity_papers = driver.findElement(By.id(IDENTITY_PAPERS));
		identity_papers.click();
		
		WebElement unth_phynumber = driver.findElement(By.id(UNTH_PHYNUMBER));
		unth_phynumber.sendKeys("cs741258963");
		
		WebElement unth_phyPhoto = driver.findElement(By.id(UNTH_PHYPHOTO));
		unth_phyPhoto.click();
		
		WebElement upload_confim = driver.findElement(By.xpath(UNLOAD_CONFIM));
		upload_confim.click();
		
		WebElement select_photo = driver.findElement(By.xpath(SELECT_PHOTO));
		select_photo.click();
		
/*		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();
		
		TouchAction action = new TouchAction((MobileDriver) driver);
		action.press(height / 2, width / 2);
*/		
		Utils.sleep(4);
		WebElement auth_btn = driver.findElement(By.id(AUTH_BTN));
		auth_btn.click();
				
		// 避免没有点中auth_btn, 如果存在就一直点
		boolean status = false;
    	while (status == false) {
    		if (Utils.isElementExist(driver, By.id(AUTH_BTN))) {	
    			auth_btn.click();
    		} else {
    			status = true;
    			break;
    		}
    	}
    	
    	selectMajor();
    	uploadPhoto();
    	
    	IndexPageElements.knowMessage();
	}
	
	/**
	 * 关注专业
	 */
	public static void selectMajor() {

		//选择关注的专业
		WebElement select_major = driver.findElement(By.xpath(SELECT_MAJOR));
		select_major.click();
		
		WebElement select_major_next_btn = driver.findElement(By.id(SELECT_MAJOR_NEXT_BTN));
		select_major_next_btn.click();
	}
	
	/**
	 * 上传头像
	 */
	public static void uploadPhoto() {
	
		//跳过上传头像
		WebElement close_upload_photo = driver.findElement(By.id(CLOSE_UPLOAD_PHOTO));
		close_upload_photo.click();
	}
}
