package com.allinmd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.Test;

/**
 * 解析移动设备udid和deviceName
 * @author sun
 *
 */
public class DeviceParse {
	public static String udid;
	public static String deviceName;
	
	public DeviceParse() {
		udid = "";
		deviceName = "";
	}
	
	public static void getDeviceInfo() {
		List<String> list = new LinkedList<String>();
        String line = "";  
		String command = "cmd /c adb devices -l";
		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));	 			
	        while ((line = reader.readLine()) != null) {
	        	list.add(line.toString());
	        }
	        process.waitFor();
	     
		} catch (IOException | InterruptedException e) {
			e.getMessage();
		}
		
		int index = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).contains("model:")) {
				index = list.get(i).indexOf("model:");
				String[] split1 = list.get(i).split(" ");
				String[] split2 = list.get(i).substring(index + 6).split(" ");
				udid = split1[0];
				deviceName = split2[0];				
			}		
		}
	}
	
	@Test
	public void f() {
		getDeviceInfo();
		System.out.println("deviceName" + deviceName);
		System.out.println("udid" + udid);
	}
}
