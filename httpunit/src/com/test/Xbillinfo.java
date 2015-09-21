package com.test;

import org.testng.annotations.Test;

import pub.test.ConnectInfo;

public class Xbillinfo extends SimuReqBase {
	private ConnectInfo conn; 	//接口配置信息
	private String bills;		//运单信息，json形式
	private int iEnv;			//环境,1:55/2:demo/3:g7
	
	public Xbillinfo(){
		conn = new ConnectInfo();
		bills = "";
		iEnv = 1;		
	}
  @Test
  public void f() {
	  
	  String pathRelaUser = "";
		String connPath = "";
		
		iEnv = 2;
		
		switch(iEnv){
		case 1:
			connPath = "\\data\\ztkd\\ips2.api.xbillinfo_55.xml";
			//读取连接信息
			readXmlSqlConn(conn, connPath);
			pathRelaUser = "\\data\\ztkd\\xbillinfo_55.xml";
			
		  	break;
		case 2:
			connPath = "\\data\\ztkd\\ips2.api.xbillinfo_demo.xml";				
			//读取连接信息
			readXmlSqlConn(conn, connPath);
			pathRelaUser = "\\data\\ztkd\\xbillinfo_demo.xml";
			
			break;
		case 3:
			connPath = "\\data\\ztkd\\ips2.api.orderaddr_cre1.xml";				
			//读取连接信息
			readXmlSqlConn(conn, connPath);				
			pathRelaUser = "\\data\\ztkd\\orderaddr_cre1.xml";
			break;
		  default:
			  break;		
		}		

		//读取接口字段信息
		//readXbill(pathRelaUser);
		
		bills = Dom4jXml.parserXml(pathRelaUser);
		
//		System.out.println(bills);
		
		//模拟接口调用，并输出返回值
		resGet(bills, conn);
  }
}
