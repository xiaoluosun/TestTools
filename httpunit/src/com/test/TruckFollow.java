package com.test;

import java.util.LinkedHashMap;

import pub.test.ConnectInfo;

public class TruckFollow extends SimuReqBase{
	
	private ConnectInfo conn; 				//接口配置信息
	private LinkedHashMap<String, String> jsons;		//地址运单信息信息，json形式
	private int iEnv;			//环境,1:55/2:demo/3:g7
	
	public TruckFollow(){
		conn = new ConnectInfo();
		jsons = new LinkedHashMap<String, String>();
		iEnv = 1;
	}
	
	public void test() {
		  
		  String connPath = "";
		  String pathRelaUser = "";
		  String openUrl= "";
		  
		  iEnv = 3;
		  switch(iEnv){
		  case 1:
			  	connPath = "\\data\\zdwl\\truck.webapi.follow_65.xml";				
				//读取连接信息
				readXmlSqlConn(conn, connPath);
				openUrl = readXmlOpenUrl(connPath);
				pathRelaUser = "\\data\\zdwl\\follow_65.xml";
				
			  	break;
		  case 2:
			  	connPath = "\\data\\zdwl\\truck.webapi.follow_65.xml";				
				//读取连接信息
				readXmlSqlConn(conn, connPath);
				openUrl = readXmlOpenUrl(connPath);
				pathRelaUser = "\\data\\zdwl\\follow_65.xml";
				break;
		  case 3:
			  	connPath = "\\data\\zdwl\\truck.webapi.follow_g7s.xml";				
				//读取连接信息
				readXmlSqlConn(conn, connPath);
				openUrl = readXmlOpenUrl(connPath);
				pathRelaUser = "\\data\\zdwl\\follow_g7s.xml";
				break;
		  default:
			  break;		  
		  }
		  
		//读取接口字段信息	
		jsons = Dom4jXml.getListMap(pathRelaUser);
		
		System.out.println(jsons);


		printG7SUrl(jsons, conn, openUrl);
		
	  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TruckFollow truck =  new TruckFollow();
		truck.test();

	}

}
