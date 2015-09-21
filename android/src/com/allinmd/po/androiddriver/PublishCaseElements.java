package com.allinmd.po.androiddriver;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

@Listeners({com.runtime.listener.AssertionListener.class})
public class PublishCaseElements {
	
	// 发布病例按钮
	public static String pub_case_btn = "com.allin.social:id/ll_publish_case";
	
	// 选择专业
	public static String select_major = "com.allin.social:id/ck_att_major_name";
	
	// 选择专业后下一步
	public static String select_major_next = "com.allin.social:id/btn_att_major_next";
	
	// 病例标题
	public static String case_title_input = "com.allin.social:id/et_cases_title";
	
	// 选择性别：女
	public static String selcect_women = "com.allin.social:id/rb_select_women";
	
	// 输入年龄：年
	public static String age_year_input = "com.allin.social:id/et_cases_age_year";
	
	// 输入主诉
	public static String main_suit_input = "com.allin.social:id/et_patient_main_suit";
	
	// 输入讨论
	public static String case_talk_input = "com.allin.social:id/et_cases_talk";
	
	// 上传术前图片
	public static String preoperative_pic = "com.allin.social:id/ll_preoperative_pic"; 
	
	// 选择需要上传的图片
	public static final String SELECT_PIC1 = "//android.widget.RelativeLayout[contains(@index, '1')]";
	
	// 选择需要上传的图片
	public static final String SELECT_PIC2 = "//android.widget.RelativeLayout[contains(@index, '2')]";
	
	// 选择图片后下一步按钮
	public static final String SELECT_PIC_NEXT = "com.allin.social:id/cases_select_photo_complete";
	
	// 发布按钮
	public static String pub_case = "com.allin.social:id/btn_publish_cases";
	
	// 终端页病例标题
	public static String terminal_case_name = "com.allin.social:id/tv_case_name";
	
	// 终端页编辑病例按钮
	public static String edit_case_btn = "com.allin.social:id/tv_add_attention";
	
	// 编辑页面删除病例按钮
	public static String delete_case_btn = "com.allin.social:id/delect_publish_cases";
	
	// 终端页年龄字段
	public static String terminal_age = "com.allin.social:id/tv_age";	
	
	// 删除病例后，全文页提示文字
	public static String delete_case_message = "com.allin.social:id/tv_content_deleted_style";
	
	private static AndroidDriver driver;
    
    public PublishCaseElements(AndroidDriver driver){  
    	PublishCaseElements.driver = driver; 
    	new MainMenuPageElements(driver);
    	new IndexPageElements(driver);
    }
	
    /**
     * 发布病例，并根据终端页信息验证是否发布成功。
     * @param case_title
     * @param age
     * @param main_suit
     * @param case_talk
     * @return 
     */
	public static String pubCase(String case_title, String age, String main_suit, String case_talk) {
		WebElement ePubCaseBtn = driver.findElement(By.id(pub_case_btn));
		ePubCaseBtn.click();
		
		WebElement eSelectMajor = driver.findElement(By.id(select_major));
		eSelectMajor.click();
		
		WebElement eSelectMajorNext = driver.findElement(By.id(select_major_next));
		eSelectMajorNext.click();
		
		WebElement eCaseTitle = driver.findElement(By.id(case_title_input));
		eCaseTitle.sendKeys(case_title);
		
		WebElement eSelectWomen = driver.findElement(By.id(selcect_women));
		eSelectWomen.click();
		
		WebElement eAgeYear = driver.findElement(By.id(age_year_input));
		eAgeYear.sendKeys(age);
		
		WebElement eMainSuit = driver.findElement(By.id(main_suit_input));
		eMainSuit.clear();
		eMainSuit.sendKeys(main_suit);
		
		WebElement eCaseTalk = driver.findElement(By.id(case_talk_input));
		eCaseTalk.clear();
		eCaseTalk.sendKeys(case_talk);
		
		WebElement eLoadPic = driver.findElement(By.id(preoperative_pic));
		eLoadPic.click();
		
		WebElement eSelectPic1 = driver.findElement(By.xpath(SELECT_PIC1));
		eSelectPic1.click();
		
		WebElement eSelectPic2 = driver.findElement(By.xpath(SELECT_PIC2));
		eSelectPic2.click();
		
		WebElement eSelectPicNext = driver.findElement(By.id(SELECT_PIC_NEXT));
		eSelectPicNext.click();
		
		eSelectPicNext.click();
		
		boolean flag = Utils.swipe(driver, By.id(pub_case), 2);
		boolean ePubCaseAttribute;		
		if (flag) {
			ePubCaseAttribute = Boolean.parseBoolean(driver.findElement(By.id(pub_case)).getAttribute("clickable"));			
			if (ePubCaseAttribute) {
				WebElement ePubCase = driver.findElement(By.id(pub_case));
				ePubCase.click();
			} else {
				Assertion.assertEquals(true, ePubCaseAttribute, "未触发发布按钮，是置灰状态!");
			}
		} else {
			Assertion.assertEquals(true, flag, "发布按钮未找到，请检查后再试！");
		}
		
		String terminalCaseName = "";
		boolean findCaseTitle = Utils.isElementExist(driver, By.id(terminal_case_name));
		int num = 0;
		if (findCaseTitle == false) {
			while (findCaseTitle == false && num < 10) {
				findCaseTitle = Utils.isElementExist(driver, By.id(terminal_case_name));
				System.out.println(num);
				if (findCaseTitle) {
					terminalCaseName = Utils.getText(driver, By.id(terminal_case_name));
				} else {
					num ++;
				}
			}
		} else {
			terminalCaseName = Utils.getText(driver, By.id(terminal_case_name));
		}
		
		return terminalCaseName;
	}
	
	/**
	 * 编辑病例
	 * @param age
	 * @return
	 */
	public static String editCase(String age) {
		WebElement eEditBtn = driver.findElement(By.id(edit_case_btn));
		eEditBtn.click();
		
		WebElement eAgeYear = driver.findElement(By.id(age_year_input));
		eAgeYear.clear();
		eAgeYear.sendKeys(age);
		
		boolean flag = Utils.swipe(driver, By.id(pub_case), 2);	
		if (flag) {	
			WebElement eEditCase = driver.findElement(By.id(pub_case));
			eEditCase.click();
		} else {
			Assertion.assertEquals(true, flag, "发布按钮未找到，请检查后再试！");
		}
		
		String terminalAge = "";
		boolean findCaseAge = Utils.isElementExist(driver, By.id(terminal_age));
		int num = 0;
		if (findCaseAge == false) {
			while (findCaseAge == false && num < 10) {
				findCaseAge = Utils.isElementExist(driver, By.id(terminal_age));
				System.out.println(num);
				if (findCaseAge) {
					terminalAge = Utils.getText(driver, By.id(terminal_age));
				} else {
					num ++;
				}
			}
		} else {
			terminalAge = Utils.getText(driver, By.id(terminal_age));
		}
		
		return terminalAge;
	}
	
	/**
	 * 删除病例
	 * @return
	 */
	public static boolean deleteCase() {
		WebElement eEditBtn = driver.findElement(By.id(edit_case_btn));
		eEditBtn.click();
		
		Utils.sleep(5);
		boolean flag = Utils.swipe(driver, By.id(delete_case_btn), 5);	
		if (flag) {	
			WebElement eDeleteCase = driver.findElement(By.id(delete_case_btn));
			eDeleteCase.click();
		} else {
			Assertion.assertEquals(true, flag, "删除按钮未找到，请检查后再试！");
		}
		
		boolean deleteCaseContent = Utils.isElementExist(driver, By.id(delete_case_message));
		int num = 0;
		while (deleteCaseContent == false && num < 10) {
			System.out.println(num);
			deleteCaseContent = Utils.isElementExist(driver, By.id(delete_case_message));
			num ++;
		}
				
		return deleteCaseContent;
	}
	
	/**
	 * 到个人中心——发布的病例查看刚发布的病例
	 * @return
	 */
	public static WebElement checkCase() {		
		WebElement eMainMenu = driver.findElement(By.className(IndexPageElements.MAIN_MENU));
		eMainMenu.click();
		
		WebElement ePersonalCerter = driver.findElement(By.id(MainMenuPageElements.PERSONAL_CERTER));
		ePersonalCerter.click();
		
		WebElement eReleaseCase = driver.findElement(By.id(PerCenterPageElements.RELEASE_CASE));
		eReleaseCase.click();
		
		WebElement eReleaseCaseTitle = driver.findElement(By.id(PerCenterPageElements.RELEASE_CASE_TITLE));
		
		return eReleaseCaseTitle;
	}
}
