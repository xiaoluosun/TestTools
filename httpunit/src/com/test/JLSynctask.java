package com.test;

import java.util.ArrayList;

import pub.test.ConnectInfo;

public class JLSynctask extends SimuBaseCopy {
	
	private ConnectInfo conn; 	//接口配置信息
	private String jsons;		//地址运单信息信息，json形式
	private int iEnv;			//环境,1:55/2:demo/3:g7
	
	public JLSynctask(){
		conn = new ConnectInfo();
		jsons = "";
		iEnv = 1;
	}
	
	public void pressTest() {
		  String connPath = "";
		  String pathRelaUser = "";
		  
		  iEnv = 1;
		  switch(iEnv){
		  case 1:
			  	connPath = "\\data\\kerry\\ips2.postevent.synctask_53.xml";				
				//读取连接信息
				readXmlSqlConn(conn, connPath);
				pathRelaUser = "\\data\\kerry\\synctask_53.xml";
				
			  	break;
		  case 2:
			  connPath = "\\data\\kerry\\ips2.postevent.synctask_53.xml";				
				//读取连接信息
				readXmlSqlConn(conn, connPath);
				pathRelaUser = "\\data\\kerry\\synctask_53.xml";
				
				break;
		  case 3:
			  	
				break;
		  default:
			  break;		  
		  }
		  
		//连接接口信息位置
		
		  
		//读取接口字段信息
		  ArrayList <String> params = new ArrayList<String>();
		  params.add("taskid");
		  jsons = Dom4jXml.parserXmlNew(pathRelaUser, 1, params);
		
		  System.out.println(jsons);
		
		  resGet(jsons, conn);
	  }
	
	public void funcTest() {
		  String connPath = "";
		  String pathRelaUser = "";
		  
		  iEnv = 2;
		  switch(iEnv){
		  case 1:
			  	connPath = "\\data\\kerry\\ips2.postevent.synctask_53.xml";
				//读取连接信息
				readXmlSqlConn(conn, connPath);
				pathRelaUser = "\\data\\kerry\\synctask_53.xml";
				
			  	break;
		  case 2:
			  	connPath = "\\data\\kerry\\ips2.postevent.synctask_demo.xml";
				//读取连接信息
				readXmlSqlConn(conn, connPath);
				pathRelaUser = "\\data\\kerry\\synctask_demo.xml";
				
				break;
		  case 3:
			  	
				break;
		  default:
			  break;		  
		  }
		  
		//连接接口信息位置
		
		  
		//读取接口字段信息
		  ArrayList <String> params = new ArrayList<String>();
		  jsons = Dom4jXml.parserXmlNew(pathRelaUser, 1, params);
		
		  System.out.println(jsons);
		
		  resGet(jsons, conn);
	  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JLSynctask sycn = new JLSynctask();
		sycn.funcTest();
	}

}
