package ztkd.xbillinfo;

import pub.test.ConnectInfo;

public class AutoZtkdCustomerOrder {

	private ConnectInfo conn; 	//接口配置信息
	private String jsons;		//地址运单信息信息，json形式
	private Boolean IsDemo;		//是否是demo环境
	private XmlReadInfo readxml =new XmlReadInfo(); //读取xml(接口配置信息)
	
	public AutoZtkdCustomerOrder(){
		conn = new ConnectInfo();
		jsons = "";
		IsDemo = false;
		
	}

  public void f() {
	  
	  String pathRelaUser = "";
	  if(!IsDemo){
		//连接接口信息位置
		String connPath = "\\data\\ztkd\\ips2.api.xbillinfo_55.xml";
		
		//读取连接信息
		readxml.readXmlSqlConn(conn, connPath);
		
		pathRelaUser = "\\data\\ztkd\\xbillinfo_55_customer.xml";
	  }
	  else{
		//连接接口信息位置
		String connPath = "";
		
		//读取连接信息
		readxml.readXmlSqlConn(conn, connPath);
		
		pathRelaUser = "";
	  }
	  
	  AutoOrderByTime at = new AutoOrderByTime();
	  at.timeAuto(10);
		//读取接口字段信息	
		jsons = at.parserXml(pathRelaUser,true);
		readxml.resGet(jsons, conn);
  }
}
