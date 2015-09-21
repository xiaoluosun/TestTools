package com.test;

import org.testng.annotations.Test;

import pub.test.ConnectInfo;

public class ZptztSyncdispath extends SimuReqBase {
	private ConnectInfo conn; 	//接口配置信息
	private String jsons;		//地址运单信息信息，json形式
	private Boolean IsDemo;		//是否是demo环境
	
	public ZptztSyncdispath(){
		conn = new ConnectInfo();
		jsons = "";
		IsDemo = true;
		
	}
  @Test
  public void f() {
	  
	  String pathRelaUser = "";
	  if(!IsDemo){
		//连接接口信息位置
		String connPath = "\\data\\zptzt\\ips2.zptzt.syncdispath_55.xml";
		
		//读取连接信息
		readXmlSqlConn(conn, connPath);
		
		pathRelaUser = "\\data\\zptzt\\syncdispath_55.xml";
	  }
	  else{
		//连接接口信息位置
		String connPath = "\\data\\zptzt\\ips2.zptzt.syncdispath_demo.xml";
		
		//读取连接信息
		readXmlSqlConn(conn, connPath);
		
		pathRelaUser = "\\data\\zptzt\\syncdispath_demo.xml";
	  }
		//读取接口字段信息	
		jsons = Dom4jXml.parserXml(pathRelaUser);
		
		//System.out.println(jsons);
		
		//模拟接口调用，并输出返回值
		resGet(jsons, conn);
	
  }
}
