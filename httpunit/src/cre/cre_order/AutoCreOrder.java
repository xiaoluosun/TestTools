package cre.cre_order;

import org.testng.annotations.Test;

import pub.test.ConnectInfo;
import cre.cre_order.AutoTimeOrder;

public class AutoCreOrder extends ReadXmlInfo{	
	private ConnectInfo conn; 	//接口配置信息
	private int iEnv;			//环境,1:55/2:demo/3:g7
	private String jsons;		//运单信息信息，json形式
	
	public AutoCreOrder(){
		conn = new ConnectInfo();
		iEnv = 1;
		jsons = "";
	}
	@Test
	public void readXml(){
		String connPath = "";
		String pathRelaUser = "";
	  
		iEnv = 1;
		switch(iEnv){
		case 1:
		  	connPath = "\\data\\zptzt\\ips2.zptzt.syncorder_55.xml";				
		  	//读取连接信息
		  	readXmlSqlConn(conn, connPath);				
		  	pathRelaUser = "\\data\\zptzt\\order_55.xml";
			
		  	break;
		case 2:
		  	connPath = "\\data\\ips2.api.orderaddr_55.xml";				
			//读取连接信息
			readXmlSqlConn(conn, connPath);				
			pathRelaUser = "\\data\\orderaddr_55.xml";
			break;
		case 3:
		  	connPath = "\\data\\ips2.api.orderaddr_55.xml";				
		  	//读取连接信息
		  	readXmlSqlConn(conn, connPath);				
			pathRelaUser = "\\data\\orderaddr_55.xml";
			break;
		default:
			break;		  
		}
		AutoTimeOrder at = new AutoTimeOrder();
		at.autoTime();
		//读取接口字段信息		
		jsons =  at.parserXml(pathRelaUser);
		
		resGet(jsons, conn);
		
		Credispath cd = new Credispath();
		cd.f();	
		
	}
}