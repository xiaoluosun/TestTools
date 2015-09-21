package com.sun.demo;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

    public GetResetPasswd(){
		code = "";
    }
    /*
    @Test
	public void getInfo(){
    	resInfoPath = "\\data\\html5\\reset_passwd_info.xml";					//重置密码接口info	
    	Map<String, String> resInfo = Dom4jXml.parserXml(resInfoPath);		
    	switch(Integer.parseInt(resInfo.get("mode"))){							//1:调用get手机验证码接口                 2:调用get邮箱重置密码链接接口
			case 1:
				resGetPhone(resInfo);
				break;
				
			case 2:	
				resGetEmail(resInfo);
				break;			
    	}
	}	
    */
	/**  
	 * 模拟浏览器get请求
	 * 得到手机验证码
	 * @param info 
	 * @return 
	 * @throws InterruptedException 
	 */ 
	public static String resGetPhone(String phone) {	
		WebConversation webConversation = new WebConversation();				//模拟浏览器对象,拥有一个浏览器	
		String manageUrl = "http://192.168.1.33:8080/allin_manager_platform//logSmsAction!list";
		String webRequest = manageUrl								//用get方法得到 一个请求对象
							+ "?order=asc&page=1"
							+ "&queryJson={'cellPhone'%3A'"
							+ phone + "'}"
							+ "&rows=1&sort=id";
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		WebRequest request = new GetMethodWebRequest(webRequest);		
		WebResponse resp;														//获取响应对象
		try {
			resp = webConversation.getResponse(request);						
//			System.out.println(resp.getText());									//用getText方法获取相应的全部内容，并将获取的内容打印在控制台上			
			
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());					//从json串得到验证码
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("rows"));
			JSONObject json = jsonobject.getJSONObject(0);
			code = json.get("smsContent").toString().substring(11, 17);
//			System.out.println(code);
			
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
	 * @param string 
	 * @param info 
	 * @return 
	 * @throws InterruptedException 
	 */ 
	public static String resGetEmail(String email) {	
		WebConversation webConversation = new WebConversation();				//模拟浏览器对象,拥有一个浏览器	
		String manageUrl = "http://192.168.1.33:8080/allin_manager_platform//logEmailAction!list";
		String webRequest = manageUrl									//用get方法得到 一个请求对象
							+ "?order=asc&page=1"
							+ "&queryJson={'email'%3A'"
							+ email + "'}"
							+ "&rows=1&sort=id";
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		WebRequest request = new GetMethodWebRequest(webRequest);		
		WebResponse resp;														//获取响应对象
		try {
			resp = webConversation.getResponse(request);						
//			System.out.println(resp.getText());									//用getText方法获取相应的全部内容，并将获取的内容打印在控制台上
			
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());					//从json串得到链接
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("rows"));
			JSONObject json = jsonobject.getJSONObject(0);
			url = json.get("sendContent").toString();
//			String pattern = "(http://)(www|m).allinmd.cn/[a-n]{4,5}/customer/reset/password/input/\\?itemId=[0-9]{4}&resetSite=[0-9]&validCode=[0-9a-z]{32}"; 
			String pattern = "(http://)(www.|m.)allinmd.cn/[a-n]{4,5}/customer/reset/password/input/\\?itemId=[0-9]{4,5}&resetSite=[0-9]&validCode=[0-9a-z]{32}";
			Pattern patcher1 = Pattern.compile(pattern); 
			Matcher matcher1 = patcher1.matcher(url);
			if(matcher1.find()) 
				url = matcher1.group(0); 						//得到链接
			System.out.println(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return url;
	} 
	
	public void f() {
//		System.out.println(resGetPhone("http://192.168.1.33:8080/allin_manager_platform//logSmsAction!list", "17700070000"));
//		System.out.println(resGetEmail("test@smc.com"));
		System.out.println(resGetPhone("13046456462"));
	}
}