package com.allinmd.html5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomNum {
	private String Num;
	
	public RandomNum(){
		Num = "";
	}
	
	/**
     * 用当前日期生成唯一序列号
     * 格式：年+月+日+小时+分钟      201504211133
     */ 
	public void random(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Calendar cal = Calendar.getInstance();
		String times = format.format(cal.getTime()).substring(0,17);
		Pattern pattern = Pattern.compile("[^0-9]");  
		Matcher matcher = pattern.matcher(times); 
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sbr,"");
		}
		matcher.appendTail(sbr);
		Num = sbr.toString();	

		System.out.println(Num);
	}
	
	/**
     * 生成4位随机数
     * 1-9999
     */ 	
	public static int randomNum(){		
		int max = 10000;
		int min = 1;
		return new Random().nextInt(max - min) + min;
		
	}
}
