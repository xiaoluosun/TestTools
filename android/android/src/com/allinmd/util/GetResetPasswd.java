package com.allinmd.util;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * 得到重置密码的手机验证码和邮箱url
 * @author sun
 *
 */
public class GetResetPasswd {
	private static String code;
	private static String url;
	public static int num;
	
    public GetResetPasswd(){
		code = "";
		num = Integer.parseInt(Dom4jXml.getValue("switch"));
    }
    
	/**  
	 * 模拟浏览器get请求
	 * 得到手机验证码
	 * @param info 
	 * @return 
	 */ 
    private static String resGetPhone(String manageUrl, String phone) {	
		WebConversation webConversation = new WebConversation();				//模拟浏览器对象,拥有一个浏览器		 
		String webRequest = manageUrl								//用get方法得到 一个请求对象
							+ "?order=asc&page=1"
							+ "&queryJson={'cellPhone'%3A'"
							+ phone + "'}"
							+ "&rows=1&sort=id";

		WebRequest request = new GetMethodWebRequest(webRequest);		
		WebResponse resp;														//获取响应对象
		try {
			resp = webConversation.getResponse(request);						
//			System.out.println(resp.getText());									//用getText方法获取相应的全部内容，并将获取的内容打印在控制台上			
			
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());					//从json串得到验证码
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("rows"));
			JSONObject json = jsonobject.getJSONObject(0);
			if (json.isEmpty()) {
				code = "404";
			} else {
				code = json.get("smsContent").toString().substring(11, 17);
//				System.out.println(code);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return code;
	}
    
	/**  
	 * 模拟浏览器get请求
	 * 得到邮箱重置密码链接
	 * @param info 
	 * @return 
	 */ 
	private static String resGetEmail(String manageUrl, String email) {	
		WebConversation webConversation = new WebConversation();				//模拟浏览器对象,拥有一个浏览器		 
		String webRequest = manageUrl									//用get方法得到 一个请求对象
							+ "?order=asc&page=1"
							+ "&queryJson={'email'%3A'"
							+ email + "'}"
							+ "&rows=1&sort=id";

		WebRequest request = new GetMethodWebRequest(webRequest);		
		WebResponse resp;														//获取响应对象
		try {
			resp = webConversation.getResponse(request);						
//			System.out.println(resp.getText());									//用getText方法获取相应的全部内容，并将获取的内容打印在控制台上
			
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());					//从json串得到链接
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("rows"));
			JSONObject json = jsonobject.getJSONObject(0);
			if (json.isEmpty()) {
				url = "404";
			} else {
				url = json.get("sendContent").toString();
	//			String pattern = "(http://)(www.|m.)(.allinmd).cn/[a-n]{4,5}/customer/reset/password/input/\\?itemId=[0-9]{4,5}&resetSite=[0-9]&validCode=[0-9a-z]{32}";
				String pattern = "(http://)(www|m).allinmd.cn/[a-n]{4,5}/customer/reset/password/input/\\?itemId=[0-9]{4,5}&resetSite=[0-9]&validCode=[0-9a-z]{32}"; 
				Pattern patcher1 = Pattern.compile(pattern); 
				Matcher matcher1 = patcher1.matcher(url);
				if(matcher1.find()) 
					url = matcher1.group(0); 						//得到链接
	//			System.out.println(url);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return url;
	} 
	
	public static String getEmailUrl(String username) {
		String temp = null;
		switch(num){
			case 1:
				Map<String, String> str1 = Dom4jXml.parserXml("\\data\\reset_passwd_info.xml");
				temp = resGetEmail(str1.get("managerEmailUrl"), username);
				break;
			case 2:	
				Map<String, String> str2 = Dom4jXml.parserXml("\\data\\reset_passwd_info.xml");
				temp = resGetEmail(str2.get("emailurl"), username);
				break;
			default:
				break;
		}
		return temp;
	}
	
	public static String getPhoneCode(String username) {
		String temp = null;
		switch(num){
			case 1:
				Map<String, String> str1 = Dom4jXml.parserXml("\\data\\reset_passwd_info.xml");
				temp = resGetPhone(str1.get("managerPhoneUrl"), username);
				break;
			case 2:	
				Map<String, String> str2 = Dom4jXml.parserXml("\\data\\reset_passwd_info.xml");
				temp = resGetPhone(str2.get("phoneurl"), username);
				break;
			default:
				break;
		}
		return temp;
	}
	
	@Test
	public void f() {
		System.out.println(getEmailUrl("smc13800@163.com"));
	}
}