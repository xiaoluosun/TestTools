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

public class PubTopic {
	public static String status;
	public static String topicName;	
	private static WebConversation webConversation = new WebConversation();
	private static String createTopicId(String uri) {
		String caseId = "";			
		try {
			JSONObject jsonObject = JSONObject.fromObject(HttpClientLogin.createUrl("http://" + uri +"/call/topic/baseinfo/create/").getResponseBodyAsString());
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("responseObject"));
			JSONObject json = jsonobject.getJSONObject(0);
			caseId = json.get("responsePk").toString();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		return caseId;
	}
	
	@Test
	public static void createTopic(String uri) {
		String param = null;
		String url = null;	
		topicName = RandomStr.createTitleName();
		param = "?paramJson={\"topicId\":\"" 								
							+ createTopicId(uri) + "\",\"isValid\":1,\"topicName\":\"" + topicName
							+ "\",\"topicType\":\"1\",\"topicDiscuss\":\"这里是讨论，此处省略一万字\",\"tagList\":\"2_关节,7_脊柱\"}";
		
		String strBill = GenCommon.GBK2Unicode(param);
		url = "http://" + uri +"/call/topic/baseinfo/update/" + strBill;  
//		System.out.println(uri);
		WebRequest request = new GetMethodWebRequest(url);
		
		try {
			WebResponse resp = webConversation.getResponse(request);
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());			
//			System.out.println(jsonObject);
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("responseObject"));
			JSONObject json = jsonobject.getJSONObject(0);
			status = json.get("responseStatus").toString();
			
		} catch (IOException | SAXException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}