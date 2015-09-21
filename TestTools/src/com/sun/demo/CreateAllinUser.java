package com.sun.demo;


import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class CreateAllinUser {
	private static WebConversation webConversation = new WebConversation();
		
	public static Object createEmailUser(String username, String password) {
		Object temp = "";
		String url = "http://www.allinmd.cn/call/web/user/userRegist/?paramJson={\"account\":\"" + 
				username + "\",\"type\":\"email\",\"passwd\":\"" + 
				password + "\",\"ref\":\"\"}";
		WebRequest request = new GetMethodWebRequest(url);
		try {
			WebResponse resp = webConversation.getResponse(request);
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("responseObject"));
			JSONObject json = jsonobject.getJSONObject(0);
			Object status = json.get("responseStatus");
			if (status.toString().equals("true")) {
				temp = "用户新建成功！";
			} else if(status.toString().equals("false")) {
				temp = json.get("responseMessage");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public static Object createPhoneUser(String username, String password) {		
		Object temp = "";
		String url = "http://www.allinmd.cn/call/web/user/userRegist/?paramJson={\"account\":\"" + 
				username + "\",\"type\":\"mobile\",\"passwd\":\"" + 
				password + "\",\"ref\":\"\"}";
		WebRequest request = new GetMethodWebRequest(url);
		try {
			WebResponse resp = webConversation.getResponse(request);
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("responseObject"));
			JSONObject json = jsonobject.getJSONObject(0);
			Object status = json.get("responseStatus");
			
			if (status.toString().equals("true")) {
				temp = "用户新建成功！";
			} else if(status.toString().equals("false")) {
				temp = json.get("responseMessage");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	@Test
	public void f() {
		System.out.println(createEmailUser("xwfypg7023@126.com", "111111"));
	}
}
