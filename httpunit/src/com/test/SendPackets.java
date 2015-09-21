package com.test;


import java.io.File;
import java.util.ArrayList;

import pub.test.PacketsThread;


public class SendPackets  {
	
	/**
	 * 获取目录下的csv文件，以数组的形式返回 
	 * 
	 * 
	 */
	public ArrayList<String> getFileName(){		
		ArrayList<String> paths = new ArrayList();
		String path = System.getProperty("user.dir");
		String fileName = "\\data\\track\\";
		path +=fileName;
		File file = new File(path);
		String[] test;
		test = file.list();
		for(int i = 0 ;i < test.length ;i++)
		{
			if(test[i].substring(test[i].length()-3,test[i].length()).equals("csv"))
			{
				paths.add(path+test[i]);	
			}		
		}

		return paths;
	}

  public static void main(String[] args) {
	  SendPackets sp = new SendPackets();
	  ArrayList<String> paths = sp.getFileName();
	  
	  PacketsThread pt = new PacketsThread(paths);
	  for(int i = 0; i<paths.size(); i++){
		  // 每一个文件开启一个线程去读取并发送数据包
		  String full = paths.get(i);
		  String fileName = full.split("\\\\")[full.split("\\\\").length -1];		
		  String imei = fileName.split("\\.")[0];
		  System.out.println(imei);
		  new Thread(pt, imei).start();
	  }
  }
}
