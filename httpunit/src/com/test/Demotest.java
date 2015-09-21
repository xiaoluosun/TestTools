package com.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pub.test.ConnectInfo;
import pub.test.GenCommon;
import pub.test.XbillInfo;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class Demotest extends SimuReqBase {
	private ConnectInfo conn; 	//接口配置信息
	private XbillInfo xbill; 	//运单信息
	private String bills;		//运单信息，json形式
	private int iEnv;			//环境,1:55/2:demo/3:g7

	public Demotest() {
		conn = new ConnectInfo();
		xbill = new XbillInfo();
		bills = "";
		iEnv = 1;
	}	
	 

	// 读取配置文件中的运单信息
	public void readXbill(String bPahth) {
		String pathPro = System.getProperty("user.dir");
		
		// 数据完整路径
		String strfilePath = pathPro + bPahth;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = dbf.newDocumentBuilder();
			Document doc;
			// 获取到xml文件
			File f = new File(strfilePath);
			doc = builder.parse(f);
			// 下面开始读取
			Element root = doc.getDocumentElement(); // 获取根元素

			NodeList infoList = root.getElementsByTagName("BillInfo");
			// 读取连接串信息
			

			// 下面开始读取
			Element sqlList = (Element) infoList.item(0);
			

			
			NodeList nl = sqlList.getElementsByTagName("b_zxpsenddate");
			Element e = (Element) nl.item(0);
			Node t = e.getFirstChild();
			xbill.setB_zxpsenddate(t.getNodeValue());

			nl = root.getElementsByTagName("b_orgcode");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_orgcode(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxppcode");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxppcode(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxpbilltype");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpbilltype(t.getNodeValue());


			nl = root.getElementsByTagName("b_zxpissuedcount");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpissuedcount(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxpcargoname");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpcargoname(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxpconsigneename");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpconsigneename(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxpissuedweight");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpissuedweight(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxptime2");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxptime2(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxpconsigneemobile");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpconsigneemobile(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxpconsigneeaddr");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpconsigneeaddr(t.getNodeValue());

			 nl = root.getElementsByTagName("b_zxpstring2");
			 e = (Element) nl.item(0);
			 t = e.getFirstChild();
			 xbill.setB_zxpstring2(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxpcount1");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpcount1(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxppostmanname1");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxppostmanname1(t.getNodeValue());

			nl = root.getElementsByTagName("b_zxpissuedvolume");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			xbill.setB_zxpissuedvolume(t.getNodeValue());
			

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//get请求并显示返回
	public void resGetXbill(String strobj) {
		//获取当前时间
		String curTime = GenCommon.getCurTime();
		
		//运单信息对应的json串
//		String strBill = objToJSON(xbill);
		
		String strBill = GenCommon.GBK2Unicode(strobj);
		
		String sign = Encrypt(conn, curTime, strBill);

		//模拟浏览器对象,拥有一个浏览器
		WebConversation webConversation = new WebConversation();
		//用get方法得到 一个请求对象 
		String webRequest = "http://" + conn.getHostUrl() 
							+ "/rest/index.php?"
							+ "timestamp=" + curTime 
							+ "&app_key=" + conn.getApp_key()
							+ "&method=" + conn.getMethod() 
							+ "&data=" + strBill
							+ "&sign=" + sign.toUpperCase();

		System.out.println(webRequest);

		WebRequest request = new GetMethodWebRequest(webRequest);
		//获取响应对象
		WebResponse resp;
		try {

			resp = webConversation.getResponse(request);
			
			//用getText方法获取相应的全部内容，并将获取的内容打印在控制台上
			System.out.println(resp.getText());			
			
			String res = "[" + resp.getText() + "]";

			JSONArray outs = JSONArray.fromObject(res);
			for (int i = 0; i < outs.size(); i++) {
				JSONObject jsonout = outs.getJSONObject(i);
				System.out.println("code:" + jsonout.get("code"));

				String inner = "[" + jsonout.get("data") + "]";
				JSONArray inners = JSONArray.fromObject(inner);
				for (int j = 0; j < inners.size(); j++) {
					JSONObject jsoninner = inners.getJSONObject(i);
					System.out.println("data:result:"
							+ jsoninner.get("result"));
					System.out.println("data:params:" + jsoninner.get("params"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
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
			connPath = "\\data\\ztkd\\ips2.api.xbillinfo_g7.xml";
			//读取连接信息
			readXmlSqlConn(conn, connPath);
			pathRelaUser = "\\data\\ztkd\\xbillinfo_g7.xml";
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
