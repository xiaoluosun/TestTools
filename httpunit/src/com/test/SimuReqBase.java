package com.test;

import java.io.File;
import java.io.IOException;




import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

import pub.test.ConnectInfo;
import pub.test.GenCommon;
import pub.test.MD5Util;

public class SimuReqBase {
	
	/*
	 * 根据接口文档中的算法进行加密，生成签名
	 * 
	 * */
	protected String Encrypt(ConnectInfo conn, String times, String objJson) {
		String param1 = conn.getApp_secret() 
						+ "app_key" + conn.getApp_key()
						+ "data" + objJson
						+ "method" + conn.getMethod()
						+ "timestamp" + times 
						+ conn.getApp_secret();

			byte[] bparam1 = param1.getBytes();

			return MD5Util.getMD5(bparam1);
		}
	
	/**
	 * 根据接口文档中的算法进行加密，生成签名
	 * 
	 * */
	protected String WebEncrypt(ConnectInfo conn, String times, String objJson) {
		String param1 = conn.getApp_secret() 
						+ "app_key" + conn.getApp_key()
					    + objJson
						+ "method" + conn.getMethod()
						+ "timestamp" + times 
						+ conn.getApp_secret();
		
		return MD5Util.MD5(param1);
	}
	
	// 转换成unicode并返回
	public String objToJSON(Object obj) {
		String res = "";
		res = "[" + GenCommon.GBK2Unicode(JSONObject.fromObject(obj).toString()) + "]";
		return res;
	}
	
	
	
	// 读取接口的连接信息
	public void readXmlSqlConn(ConnectInfo conn, String path) {
		String pathPro = System.getProperty("user.dir");
		
		// 数据完整路径
		String strfilePath = pathPro + path;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = dbf.newDocumentBuilder();
			Document doc;
			// 获取到xml文件
			File f = new File(strfilePath);
			doc = builder.parse(f);
			//下面开始读取
			Element root = doc.getDocumentElement(); //获取根元素

			NodeList infoList = root.getElementsByTagName("ConnInfo");
			// 读取连接串信息

			// 下面开始读取
			Element sqlList = (Element) infoList.item(0);
			
			NodeList nl = sqlList.getElementsByTagName("hostUrl");
			Element e = (Element) nl.item(0);
			Node t = e.getFirstChild();
			conn.setHostUrl(t.getNodeValue());
			
			nl = root.getElementsByTagName("app_key");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			conn.setApp_key(t.getNodeValue());

			nl = root.getElementsByTagName("app_secret");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			conn.setApp_secret(t.getNodeValue());
			
			nl = root.getElementsByTagName("method");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			conn.setMethod(t.getNodeValue());			
			

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
	

	
	// 读取接口的连接信息
		public String readXmlOpenUrl(String path) {
			String pathPro = System.getProperty("user.dir");
			
			String openUrl = "";
			
			// 数据完整路径
			String strfilePath = pathPro + path;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			try {
				builder = dbf.newDocumentBuilder();
				Document doc;
				// 获取到xml文件
				File f = new File(strfilePath);
				doc = builder.parse(f);
				//下面开始读取
				Element root = doc.getDocumentElement(); //获取根元素

				NodeList infoList = root.getElementsByTagName("ConnInfo");
				// 读取连接串信息

				// 下面开始读取
				Element sqlList = (Element) infoList.item(0);
				
				NodeList nl = sqlList.getElementsByTagName("openUrl");
				Element e = (Element) nl.item(0);
				Node t = e.getFirstChild();
				openUrl = t.getNodeValue();			
				

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
			
			return openUrl;
		}
	
	//get请求并显示返回
		public void resGet(String strobj, ConnectInfo conn) {
			//获取当前时间
			String curTime = GenCommon.getCurTime();
			
			
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
				
//				String res = "[" + resp.getText() + "]";
//
//				JSONArray outs = JSONArray.fromObject(res);
//				for (int i = 0; i < outs.size(); i++) {
//					JSONObject jsonout = outs.getJSONObject(i);
//					System.out.println("code:" + jsonout.get("code"));
//
//					String inner = "[" + jsonout.get("data") + "]";
//					JSONArray inners = JSONArray.fromObject(inner);
//					for (int j = 0; j < inners.size(); j++) {
//						JSONObject jsoninner = inners.getJSONObject(i);
//						System.out.println("data:result:"
//								+ jsoninner.get("result"));
//						System.out.println("data:params:" + jsoninner.get("params"));
//					}
//				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		} 
		
		private String removeUrl(JSONObject jsonout){
			Iterator<String> keys=jsonout.keys();  
	        JSONObject jo=null;  
	        Object o; 
	        String result = "";
	        String key;  
	        while(keys.hasNext()){	        	
	            key=keys.next();
	            result += key + "=";
	            o=jsonout.get(key);  
	            if(o instanceof JSONObject){  
	                System.out.println("参数传入有误"); 
	            }else{  
//	                System.out.println(key + ":" + o);
	                result += o + "&";
	            }  
	        } 
			return result;
		}
		
		// 生成打开页面的url
		public void printPageUrl(LinkedHashMap<String, String> params, ConnectInfo conn, String url) {
			
			
			//获取当前时间
			String curTime = GenCommon.getCurTime();			
			
			String strParm = "";
			String reqParams = "";
			Iterator iter = params.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				strParm += key;
				reqParams += key + "=";
				Object val = entry.getValue();
				strParm += val;
				reqParams += val + "&";
			}
//			
			String sign = WebEncrypt(conn, curTime, strParm);
			
			System.out.println(sign);
//
//
			String webRequest = url
								+ "?timestamp=" + curTime 
								+ "&app_key=" + conn.getApp_key()
								+ "&method=" + conn.getMethod() 
								+ "&" + reqParams
								+ "sign=" + sign.toUpperCase();

			System.out.println(webRequest);

			
		}
		
		// 生成打开页面的url
				public void printG7SUrl(LinkedHashMap<String, String> params, ConnectInfo conn, String url) {
					
					
					//获取当前时间
					String curTime = GenCommon.getCurTime();
					
//					curTime = "2014-11-17 15:23:58";
					
					String strParm = "data";
					
					JSONObject jsonObj = JSONObject.fromObject( params );
					
					strParm += GenCommon.GBK2Unicode(jsonObj.toString());
					
//					System.out.println(strParm);
					
					String sign = WebEncrypt(conn, curTime, strParm);
					
					System.out.println(sign);
		//
					String webRequest = url
										+ "?timestamp=" + curTime 
										+ "&app_key=" + conn.getApp_key()
										+ "&method=" + conn.getMethod() 
										+ "&data=" + GenCommon.GBK2Unicode(jsonObj.toString())
										+ "&sign=" + sign.toUpperCase();

					System.out.println(webRequest);

					
				}
		
		//get请求并显示返回
			public void resg7sGet(String strobj, ConnectInfo conn) {
				//获取当前时间
				String curTime = GenCommon.getCurTime();
				
				
				String strBill = GenCommon.GBK2Unicode(strobj);
				
				String sign = Encrypt(conn, curTime, strBill);

				//模拟浏览器对象,拥有一个浏览器
				WebConversation webConversation = new WebConversation();
				//用get方法得到 一个请求对象 
				String webRequest = "http://" + conn.getHostUrl() 
									+ "/interface/index.php?"
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
	
	
	// post请求例子
		public void postTest() {
			//建立一个WebConversation实例
			WebConversation wc = new WebConversation();
			//向指定的URL发出请求，获取响应
			WebRequest req = new PostMethodWebRequest(
					"http://ips2.ips.cn/inside.php?t=json&m=login&f=getallcode&opurl=/login/index.html");

			//给post请求添加参数
			req.setParameter("cookietime", "0");
			req.setParameter("isgetcode", "1");
			req.setParameter("loginflag", "1");
			req.setParameter("password", "123456");
			req.setParameter("username", "cs926");
			///获取响应对象
			WebResponse resp;
			try {
				resp = wc.getResponse(req);
				//用getText方法获取相应的全部内容
				//用System.out.println将获取的内容打印在控制台上
				System.out.println(resp.getText());

				String res = "[" + resp.getText() + "]";

				JSONArray outs = JSONArray.fromObject(res);
				for (int i = 0; i < outs.size(); i++) {
					JSONObject jsonout = outs.getJSONObject(i);
					System.out.println("code:" + jsonout.get("code"));
					System.out.println("data:" + jsonout.get("data"));

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args){

			
			SimuReqBase sb =  new SimuReqBase();
			LinkedHashMap<String, String> params = new  LinkedHashMap<String, String>();
			params.put("carnum", "轨A00001");
			ConnectInfo conn = new ConnectInfo();
			conn.setApp_key("zd_api");
			conn.setApp_secret("759de9c0f8083a902d98f97f41bd3a1f");
			conn.setMethod( "truck.webapi.follow");
			String url = "";
			sb.printG7SUrl(params, conn, url);
			
		}
}
