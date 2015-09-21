package com.test;


import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import pub.test.ConnectInfo;

public class GetoutInfo extends SimuReqBase {
	private ConnectInfo conn; 				//接口配置信息
	private LinkedHashMap<String, String> jsons;		//地址运单信息信息，json形式
	private int iEnv;			//环境,1:55/2:demo/3:g7
	
	public GetoutInfo(){
		conn = new ConnectInfo();
		jsons = new LinkedHashMap<String, String>();
		iEnv = 1;
	}
  @Test
  public void f() {
	  
	  String connPath = "";
	  String pathRelaUser = "";
	  String openUrl= "";
	  
	  iEnv = 3;
	  switch(iEnv){
	  case 1:
		  	connPath = "\\data\\changjiu\\ips2.api.getoutinfo_55.xml";				
			//读取连接信息
			readXmlSqlConn(conn, connPath);
			openUrl = readXmlOpenUrl(connPath);
			pathRelaUser = "\\data\\changjiu\\getoutinfo_55.xml";
			
		  	break;
	  case 2:
		  	connPath = "\\data\\changjiu\\ips2.api.getoutinfo_55.xml";				
			//读取连接信息
			readXmlSqlConn(conn, connPath);				
			pathRelaUser = "\\data\\changjiu\\getoutinfo_55.xml";
			break;
	  case 3:
		  	connPath = "\\data\\standard\\ips2.api.getoutinfo_g7.xml";
			//读取连接信息
			readXmlSqlConn(conn, connPath);				
			pathRelaUser = "\\data\\standard\\getoutinfo_g7.xml";
			break;
	  default:
		  break;		  
	  }
	  
	//读取接口字段信息	
	jsons = Dom4jXml.getListMap(pathRelaUser);


	printPageUrl(jsons, conn, openUrl);
	
  }
}
