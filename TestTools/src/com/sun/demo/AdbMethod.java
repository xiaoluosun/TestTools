package com.sun.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

public class AdbMethod {
	public static String adbInstall(String apkPath) {
		String temp = null;
		String line = null;
		String command = "cmd /c adb install " + apkPath;
		try {
			Process process = Runtime.getRuntime().exec(command);		
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        while ((line = reader.readLine()) != null) {
	        	if (line.toString().length() >= 1)
	        		if (line.toString().equals("Success"))
	        			temp = "安装成功！";
	        		else if (line.toString().equals("Failure [INSTALL_FAILED_ALREADY_EXISTS]"))
	        			temp = "应用已安装，请先卸载！";
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public static void cmd(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void f() {
		adbInstall("E:\\workspace\\android\\apps\\Allinmd_m_360_v1.0.0_20150814.apk");
	}
}
