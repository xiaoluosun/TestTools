package com.test;


import java.io.IOException;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class TestPost {
	
	public void test() throws IOException{
		
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
		
		req = new PostMethodWebRequest(
				"http://ips2.ips.cn/inside.php?t=json&m=truck&f=searchnew&opurl=/truck/index.html");
		req.setParameter("page_no", "1");
		req.setParameter("page_size", "20");
		req.setParameter("qop", "Eq");
		req.setParameter("sortname", "undefined");
		req.setParameter("sortname", "carnum");
		req.setParameter("sortorder", "undefined");
		req.setParameter("sortorder", "desc");
		
		
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
	
	@Test
	public void f() {
		try{
			test();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
