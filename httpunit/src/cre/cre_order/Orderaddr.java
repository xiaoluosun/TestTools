package cre.cre_order;

import org.testng.annotations.Test;

import pub.test.ConnectInfo;
import cre.cre_order.AutoTimeOrder;

public class Orderaddr extends ReadXmlInfo {
	private ConnectInfo conn; 	//接口配置信息
	private String jsons;		//地址运单信息信息，json形式
	private int iEnv;			//环境,1:55/2:demo/3:g7
	
	public Orderaddr(){
		conn = new ConnectInfo();
		jsons = "";
		iEnv = 1;
	}
  @Test
  public void f() {
	  String connPath = "";
	  String pathRelaUser = "";
	  
	  iEnv = 1;
	  switch(iEnv){
	  case 1:
		  	connPath = "\\data\\zptzt\\ips2.api.orderaddr_55.xml";				
			//读取连接信息
			readXmlSqlConn(conn, connPath);				
			pathRelaUser = "\\data\\zptzt\\orderaddr_55.xml";
			
		  	break;
	  case 2:
		  	connPath = "\\data\\zptzt\\ips2.api.orderaddr_55.xml";				
			//读取连接信息
			readXmlSqlConn(conn, connPath);				
			pathRelaUser = "\\data\\zptzt\\orderaddr_55.xml";
			break;
	  case 3:
		  	connPath = "\\data\\zptzt\\ips2.api.orderaddr_55.xml";				
			//读取连接信息
			readXmlSqlConn(conn, connPath);				
			pathRelaUser = "\\data\\zptzt\\orderaddr_55.xml";
			break;
	  default:
		  break;		  
	  }
	
	AutoTimeOrder at = new AutoTimeOrder();
	at.autoTime();
	//读取接口字段信息		
	jsons =  at.parserXml(pathRelaUser);
	
	//模拟接口调用，并输出返回值
	resGet(jsons, conn);
  }
}
