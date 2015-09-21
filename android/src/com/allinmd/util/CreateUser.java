package com.allinmd.util;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class CreateUser {
	private static WebConversation webConversation = new WebConversation();
	
	public static void CreateEmailUser(String username, String password) {
		String url = "http://www.allinmd.cn/call/web/user/userRegist/?paramJson={\"account\":\"" + 
				username + "\",\"type\":\"email\",\"passwd\":\"" + 
				password + "\",\"ref\":\"\"}";
		
		WebRequest request = new GetMethodWebRequest(url);
		try {
			WebResponse resp = webConversation.getResponse(request);
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("responseObject"));
			JSONObject json = jsonobject.getJSONObject(0);
			Object code = json.get("responseMessage");
			System.out.println(code);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void f() {
		CreateEmailUser("allintest111@test.com", "111111");
	}
}
