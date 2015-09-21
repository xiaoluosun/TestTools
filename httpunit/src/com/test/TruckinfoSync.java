package com.test;

import org.testng.annotations.Test;

import pub.test.ConnectInfo;

public class TruckinfoSync extends SimuReqBase {
	private ConnectInfo conn; 	//接口配置信息
	private String jsons;		//地址运单信息信息，json形式
	public TruckinfoSync(){
		conn = new ConnectInfo();
		jsons = "";
	}
	
  @Test
  public void f() {
	//连接接口信息位置
	String connPath = "\\data\\sqrz\\ips2.api.truckinfo_55.xml";
	
	//读取连接信息
	readXmlSqlConn(conn, connPath);
	
	String pathRelaUser = "\\data\\sqrz\\truckinfo_55.xml";

	//读取接口字段信息	
	jsons = Dom4jXml.parserXml(pathRelaUser);
	
	//System.out.println(jsons);
	
	//模拟接口调用，并输出返回值
	resGet(jsons, conn);
  }
}
