package com.allinmd.po.webdriver.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.allinmd.po.androiddriver.ListPageElements;

public class ListWebElements {
	
	// 列表第一条资源
	@FindBy(id = ListPageElements.FIRST_RESOURCE)
	public static WebElement first_resource;
	
	// 列表actionbar
	@FindBy(id = ListPageElements.LIST_BAR)
	public static WebElement list_bar;
	
	public static boolean assertListBar(String barName) {
    	boolean status = false;
    	if (list_bar != null) {
    		return list_bar.getText().equals(barName);
    	}
		return status;
	}	
}
