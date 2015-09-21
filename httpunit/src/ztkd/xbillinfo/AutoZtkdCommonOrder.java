package ztkd.xbillinfo;

import org.testng.annotations.Test;
import pub.test.ConnectInfo;


public class AutoZtkdCommonOrder {
	private ConnectInfo conn; 	//接口配置信息
	private int iEnv;			//环境,1:55/2:demo/3:g7
	private String jsons;		//运单信息信息，json形式
	private XmlReadInfo readxml =new XmlReadInfo();  //读取xml(接口配置信息)
	public AutoZtkdCommonOrder(){
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
		  	connPath = "\\data\\ztkd\\ips2.api.xbillinfo_55.xml";	
		  	
		  	//读取连接信息
		  	readxml.readXmlSqlConn(conn, connPath);				
		  	pathRelaUser = "\\data\\ztkd\\xbillinfo_55_common.xml";
		  	
		  	break;
		case 2:
		  	connPath = "";				
			//读取连接信息
		  	readxml.readXmlSqlConn(conn, connPath);				
			pathRelaUser = "";
			break;
		case 3:
		  	connPath = "";				
		  	//读取连接信息
		  	readxml.readXmlSqlConn(conn, connPath);				
			pathRelaUser = "";
			break;
		default:
			break;		  
		}
		AutoOrderByTime at = new AutoOrderByTime();
		at.timeAuto(0);
		//读取接口字段信息		
		jsons =  at.parserXml(pathRelaUser,false);
		readxml.resGet(jsons, conn);
		try {
			Thread.sleep(3000);
			AutoZtkdCustomerOrder order=new AutoZtkdCustomerOrder();
			order.f();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
