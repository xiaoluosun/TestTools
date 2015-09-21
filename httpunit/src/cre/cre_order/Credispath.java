package cre.cre_order;

import org.testng.annotations.Test;

import pub.test.ConnectInfo;

public class Credispath extends ReadXmlInfo {
	private ConnectInfo conn; 	//接口配置信息
	private String jsons;		//地址运单信息信息，json形式
	private Boolean IsDemo;		//是否是demo环境
	
	public Credispath(){
		conn = new ConnectInfo();
		jsons = "";
		IsDemo = false;
		
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
	  
	  	AutoTimeOrder at = new AutoTimeOrder();
		//读取接口字段信息	
		jsons = at.parserXml(pathRelaUser);
		
		if(emp.toString().length() == 0){		
			//模拟接口调用，并输出返回值
			resGet(jsons, conn);
		}
		
		Orderaddr oa = new Orderaddr();
		oa.f();
		
  }
}
