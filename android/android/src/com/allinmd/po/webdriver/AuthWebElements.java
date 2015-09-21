package com.allinmd.po.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.allinmd.po.androiddriver.AuthPageElements;
import com.allinmd.util.ReadWriteTxtFile;
import com.allinmd.util.Utils;

public class AuthWebElements {
	
	// 跳过认证按钮
	@FindBy(id = AuthPageElements.SKIP_AUTH)
	public static WebElement skip_auth;
	
	// last name
	@FindBy(id = AuthPageElements.LAST_NAME)
	public static WebElement last_name;
	
	// first name
	@FindBy(id = AuthPageElements.FIRST_NAME)
	public static WebElement first_name;
	
	// 医院
	@FindBy(id = AuthPageElements.HOSPITAL)
	public static WebElement hospital;
	
	//搜索输入框
	@FindBy(id = AuthPageElements.SEARCH_HOSPITAL)
	public static WebElement search_hospital;
	
	//选择医院
	@FindBy(id = AuthPageElements.HOSPITAL_LIST)
	public static WebElement hospital_list;
	
	// 职称
	@FindBy(id = AuthPageElements.JOB)
	public static WebElement job;
	
	// 选择职称
	@FindBy(id = AuthPageElements.CB_RESIDENT)
	public static WebElement cb_resident;
	
	// 保存职称\保存专业
	@FindBy(id = AuthPageElements.DIALOG_SAVE)
	public static WebElement dialog_save;
	
	// 专业
	@FindBy(id = AuthPageElements.MAJOR)
	public static WebElement major;
	
	// 选择专业
	@FindBy(id = AuthPageElements.CHECKBOX)
	public static WebElement checkbox;
	
	// 医师资格证
	@FindBy(id = AuthPageElements.IDENTITY_PAPERS)
	public static WebElement identity_papers;
	
	// 医师学历证
	@FindBy(id = AuthPageElements.EDUCATION_PAPERS)
	public static WebElement education_papers;
	
	// 医师学位证
	@FindBy(id = AuthPageElements.DEGREE_PAPERS)
	public static WebElement degree_papers;
	
	// 医师资格证号/医师学历证号/医师学位证号
	@FindBy(id = AuthPageElements.UNTH_PHYNUMBER)
	public static WebElement unth_phynumber;
	
	// 上传图片按钮
	@FindBy(id = AuthPageElements.UNTH_PHYPHOTO)
	public static WebElement unth_phyPhoto;
	
	// 上传图片按钮
	@FindBy(xpath = AuthPageElements.UNLOAD_CONFIM)
	public static WebElement upload_confim;
	
	// 选择图片
	@FindBy(xpath = AuthPageElements.SELECT_PHOTO)
	public static WebElement select_photo;
	
	// 下一步（认证按钮）
	@FindBy(id = AuthPageElements.AUTH_BTN)
	public static WebElement auth_btn;
	
	// 选择关注的专业
	@FindBy(xpath = AuthPageElements.SELECT_MAJOR)
	public static WebElement select_major;
	
	// 选择关注的专业——点击下一步
	@FindBy(id = AuthPageElements.SELECT_MAJOR_NEXT_BTN)
	public static WebElement select_major_next_btn;
	
	// 取消上传头像按钮
	@FindBy(id = AuthPageElements.CLOSE_UPLOAD_PHOTO)
	public static WebElement close_upload_photo;
	
	private static WebDriver driver;
    
    public AuthWebElements(WebDriver driver){  
    	AuthWebElements.driver = driver; 
    	PageFactory.initElements(driver, IndexWebElements.class);
    }
       
    /**
     * 判断"跳过认证"按钮是否存在
     * @return 
     */
    public static boolean skipAuthIsExist() {
    	return Utils.isElementExist(driver, By.id(AuthPageElements.SKIP_AUTH));
    }
    
    /**
     * 医师认证Flow
     */
	public static void doctorCer() {
		last_name.sendKeys(ReadWriteTxtFile.getLastName());
		first_name.sendKeys(ReadWriteTxtFile.getFirstName());
		
		// 选择医院
		hospital.click();
		search_hospital.sendKeys("医院");
		hospital_list.click();
		
		//选择职称
		job.click();
		cb_resident.click();
		dialog_save.click();
		
		//选择专业
		major.click();
		checkbox.click();
		dialog_save.click();
		
		//医师资格证号及上传照片
		identity_papers.click();
		unth_phynumber.sendKeys("cs741258963");
		
		unth_phyPhoto.click();
		upload_confim.click();
		
		select_photo.click();
		
/*		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();
		
		TouchAction action = new TouchAction((MobileDriver) driver);
		action.press(height / 2, width / 2);
*/		
		Utils.sleep(4);
		auth_btn.click();
				
		// 避免没有点中auth_btn, 如果存在就一直点
		boolean status = false;
    	while (status == false) {
    		if (Utils.isElementExist(driver, By.id(AuthPageElements.AUTH_BTN))) {	
    			auth_btn.click();
    		} else {
    			status = true;
    			break;
    		}
    	}
    	
    	selectMajor();
    	uploadPhoto();
    	
    	IndexWebElements.knowMessage();
	}
	
	/**
	 * 关注专业
	 */
	public static void selectMajor() {

		//选择关注的专业
		select_major.click();
		select_major_next_btn.click();
	}
	
	/**
	 * 上传头像
	 */
	public static void uploadPhoto() {
	
		//跳过上传头像
		close_upload_photo.click();
	}
}
