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

public class PubCase {
	public static String status;
	public static String caseName;
	private static WebConversation webConversation = new WebConversation();
	private static String createCaseId(String uri) {
		String caseId = "";			
		try {
			JSONObject jsonObject = JSONObject.fromObject(HttpClientLogin.createUrl("http://" + uri +"/call/case_baseinfo/create/").getResponseBodyAsString());
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("responseObject"));
			JSONObject json = jsonobject.getJSONObject(0);
			caseId = json.get("responsePk").toString();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		return caseId;
	}
	
	@Test
	public static void createCase(String uri) {
		String param = null;
		String url = null;		
		caseName = RandomStr.createTitleName();
		param = "?paramJson={\"caseId\":\"" 								
							+ createCaseId(uri) + "\",\"caseName\":\"" + caseName
							+ "\",\"age\":\"56\",\"ageMonth\":\"\",\"ageDay\":\"\",\"mainNarrate\":"
							+ "\"这里是主诉，此处省略一万字\",\"illnessHistory\":\"\",\"professionalChecking\":\"\",\"discussion\":"
							+ "\"这里是讨论，此处省略一万字\",\"tagList\":\"2_关节,7_脊柱\",\"areasExpertise\":\"2_关节,7_脊柱\",\"sexId\":\"1\",\"caseAttachmentParam\":\"\"}";
		
		String strBill = GenCommon.GBK2Unicode(param);
		url = "http://" + uri +"/call/case_baseinfo/update/" + strBill; 		
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