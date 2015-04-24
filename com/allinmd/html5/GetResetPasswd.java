package com.allinmd.html5;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.allinmd.html5.Dom4jXml;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class GetResetPasswd {
	private static String resInfoPath;
	private Dom4jXml dom;
	private String code;
	private String url;

    public GetResetPasswd(){
		resInfoPath = "";
		dom = new Dom4jXml();
		code = "";
    }
    
    @Test
	public void getInfo(){
    	resInfoPath = "\\data\\html5\\reset_passwd_info.xml";					//重置密码接口info	
    	Map<String, String> resInfo = dom.parserXml(resInfoPath);
		
    	switch(Integer.parseInt(resInfo.get("mode"))){							//1:调用get手机验证码接口                 2:调用get邮箱重置密码链接接口
			case 1:
				resGetPhone(resInfo);
				break;
				
			case 2:	
				resGetEmail(resInfo);
				break;			
    	}
	}	
    
	/**  
	 * 模拟浏览器get请求
	 * 得到手机验证码
	 * @param info 
	 */ 
	public void resGetPhone(Map<String, String> info) {	
		WebConversation webConversation = new WebConversation();				//模拟浏览器对象,拥有一个浏览器		 
		String webRequest = info.get("phoneurl")								//用get方法得到 一个请求对象
							+ "?order=asc&page=1"
							+ "&queryJson={'cellPhone'%3A'"
							+ info.get("phone") + "'}"
							+ "&rows=1&sort=id";

		WebRequest request = new GetMethodWebRequest(webRequest);		
		WebResponse resp;														//获取响应对象
		try {
			resp = webConversation.getResponse(request);						
			System.out.println(resp.getText());									//用getText方法获取相应的全部内容，并将获取的内容打印在控制台上			
			
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());					//从json串得到验证码
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("rows"));
			JSONObject json = jsonobject.getJSONObject(0);
			code = json.get("smsContent").toString().substring(11, 17);
			System.out.println(code);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
    
	/**  
	 * 模拟浏览器get请求
	 * 得到邮箱重置密码链接
	 * @param info 
	 */ 
	public void resGetEmail(Map<String, String> info) {	
		WebConversation webConversation = new WebConversation();				//模拟浏览器对象,拥有一个浏览器		 
		String webRequest = info.get("emailurl")									//用get方法得到 一个请求对象
							+ "?order=asc&page=1"
							+ "&queryJson={'email'%3A'"
							+ info.get("email") + "'}"
							+ "&rows=1&sort=id";

		WebRequest request = new GetMethodWebRequest(webRequest);		
		WebResponse resp;														//获取响应对象
		try {
			resp = webConversation.getResponse(request);						
			System.out.println(resp.getText());									//用getText方法获取相应的全部内容，并将获取的内容打印在控制台上
			
			JSONObject jsonObject = JSONObject.fromObject(resp.getText());					//从json串得到链接
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("rows"));
			JSONObject json = jsonobject.getJSONObject(0);
			url = json.get("sendContent").toString();
			String pattern = "(http://)(www|m).allinmd.cn/[a-n]{4,5}/customer/reset/password/input/\\?itemId=[0-9]{4}&resetSite=[0-9]&validCode=[0-9a-z]{32}"; 
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
	} 
}
