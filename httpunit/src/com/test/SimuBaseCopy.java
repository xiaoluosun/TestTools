package com.test;

import java.io.File;
import java.io.IOException;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;





import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

import pub.test.ConnectInfo;
import pub.test.GenCommon;
import pub.test.MD5Util;


public class SimuBaseCopy {
	
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
	 * 
	 */
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
		
		File inputXml=new File(strfilePath);
		
		SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element root=document.getRootElement();   	//获取文档根节点 
            
            Element connElem=root.element("ConnInfo");// "ConnInfo"是节点名 
            
            Element urlElem=connElem.element("hostUrl");// "hostUrl"是节点名
            conn.setHostUrl(urlElem.getText());
            
            Element keyElem=connElem.element("app_key");// "hostUrl"是节点名
            conn.setApp_key(keyElem.getText());
            
            Element secrElem=connElem.element("app_secret");// "hostUrl"是节点名
            conn.setApp_secret(secrElem.getText());
            
            Element methodElem=connElem.element("method");// "hostUrl"是节点名
            conn.setMethod(methodElem.getText());
           
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        } 
		
	}
	

	
	// 读取接口的连接信息
		public String readXmlOpenUrl(String path) {
			String pathPro = System.getProperty("user.dir");
			
			// 数据完整路径
			String strfilePath = pathPro + path;
			
			File inputXml=new File(strfilePath);
			
			SAXReader saxReader = new SAXReader();
			String url = "";
	        try {    
	            Document document = saxReader.read(inputXml);   //把文件读入到文档 
	            Element root=document.getRootElement();   	//获取文档根节点 
	            
	            Element urlElet=root.element("hostUrl");// "hostUrl"是节点名 
	            url = urlElet.getText();
	           
	        } catch (DocumentException e) {    
	            System.out.println(e.getMessage());    
	        } 
	        
	        return url;
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
				
				System.out.println("=========" + webRequest.length());

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
		
		/*
		 * *
		 * @param 要转换的毫秒数
		 * @return 该毫秒数转换为 * seconds * mss 后的格式 
		 *
		 *
		 */
		public String formatDuring(long mss){
		    long seconds = mss / 1000;
		    long mills = mss % 1000;
		    return seconds + " seconds " + mills + "mills";
		}
}
