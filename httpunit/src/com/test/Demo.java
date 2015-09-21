package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import pub.test.GenCommon;


public class Demo {	
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String pathPro = System.getProperty("user.dir");
		
		System.out.println(pathPro);
		
		
		
		
		
		
		File file = new File("E:\\e.txt");
		Writer writer;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(file));
			try {
				String str2 = "中文测试";
				String str;
				try {
					str = new String( str2.toString().getBytes( "GBK" ), "GBK");
					System.out.println(str2.toString().getBytes( "utf-8" ));
					System.out.println(GenCommon.GBK2Unicode(str));
					writer.write(str);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
