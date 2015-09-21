package com.allinmd.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

import com.allinmd.util.RandomStr;

public class ReadWriteTxtFile {
	private static String currentPath = System.getProperty("user.dir");
	
	/**
	 * 读取txt文件
	 * @param path
	 * @return
	 */
	private static String ReadTxtLine(String path){
		String lineTxt = "";
		String[] data = null;
        try {
            File file = new File(currentPath + path);
            if(file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                lineTxt = bufferedReader.readLine();
//                while((lineTxt = bufferedReader.readLine()) != null){				//按行读取
//                    System.out.println(lineTxt);
//                }
                read.close();
                data = lineTxt.split(",");
	        } else {
	            System.out.println("找不到指定的文件");
	        }           
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        
        int num = RandomStr.randomNum(0, data.length - 1);
        String name = data[num];
        if (name == null) {
        	name = "李";
        }
        
        return name;
    }
	
	/**
	 * 写入txt
	 * @param username
	 * @param fileName
	 */
	public static void writeTxt(String username, String fileName) {  
		try {  
			FileOutputStream output = new FileOutputStream(fileName, true); 
			username += "\r\n";
			output.write(username.getBytes("UTF-8"));  
			output.close();   
		} catch (Exception e) {  
			e.printStackTrace();  
		}
	}
	
	/**
	 * 返回lastname
	 * @return
	 */
	public static String getLastName() {
        return ReadTxtLine("\\data\\lastname.txt");
	}
	
	/**
	 * 返回firstname
	 * @return
	 */
	public static String getFirstName() {
		return ReadTxtLine("\\data\\firstname.txt");
	}
	
	@Test
	public void f() {
		writeTxt("test@smc.com", "\\data\\username_email_test.txt");
	}
}
