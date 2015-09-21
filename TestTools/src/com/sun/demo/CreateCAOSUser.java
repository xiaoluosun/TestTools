package com.sun.demo;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class CreateCAOSUser {
	private static WebConversation webConversation = new WebConversation();
	public static Object createEmailUser(String username, String password) {
		Object temp = "";
		
		String method = "http://www.caos-china.org/call/caos/customer/baseinfo/regist_save/";
		String data = "?paramJson={\"account\":\"" + 
				username + "\",\"contactEmail\":\"" + username + "\",\"contactMobile\":\"13800138000\",\"initPasswd\":\"" +
				password + "\",\"lastName\":\"李\",\"firstName\":\"元霸\",\"sexId\":\"1\",\"siteId\":3,\"idCard\":\"440600196103154400\""
						+ ",\"certificatesId\":\"8\",\"certificates\":\"医生执业证号\",\"areasExpertiseId\":\"4\",\"areasExpertise\":\"四肢矫形\","
						+ "\"certificatesCode\":\"4406001961031544002312\",\"company\":\"北京大学第三医院\",\"medicalTitleId\":\"3\",\"medicalTitle\""
						+ ":\"副主任医师\",\"phoneCode\":\"\",\"phone\":\"\",\"phoneExtension\":\"\",\"provinceId\":\"110000\",\"province\":\"北京市\""
						+ ",\"cityId\":\"110100\",\"city\":\"北京市\",\"districtId\":\"110101\",\"district\":\"东城区\",\"detailAddress\":\"天安门东大街2号院\","
						+ "\"zipCode\":\"100000\"}";
		
		String strBill = GenCommon.GBK2Unicode(data);
		String url = method + strBill;
		WebRequest request = new GetMethodWebRequest(url);
		try {
			WebResponse resp = webConversation.getResponse(request);
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("responseObject"));
			JSONObject json = jsonobject.getJSONObject(0);
			Object status = json.get("responseStatus");
			if (status.toString().equals("true")) {
				temp = json.get("responseMessage");
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
}
