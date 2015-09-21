package com.sun.demo;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientLogin {
	public static String loginStatus;
	private static HttpClient client = new HttpClient();
	private static GetMethod get = new GetMethod();
	private static PostMethod post;
	
	/**
	 * 模拟登录系统
	 * @param username
	 * @param password
	 * @param type
	 * @return
	 */
	public static String httpLogin(String username, String password, String type, String uri) {
		post = new PostMethod("http://" + uri +"/call/passport/securitycheck");
		NameValuePair name = new NameValuePair("j_username", "website;" + username +";" + password +";" + type);
		NameValuePair pass = new NameValuePair("j_password", password);
		NameValuePair re = new NameValuePair("rememberMe", "false");
		post.setRequestBody( new NameValuePair[]{name, pass, re});
		try {
			client.executeMethod(post);
//			System.out.println(post);
			JSONObject jsonObject = JSONObject.fromObject(post.getResponseBodyAsString());
//			System.out.println(jsonObject);
			JSONArray jsonobject = JSONArray.fromObject(jsonObject.get("responseObject"));
			JSONObject json = jsonobject.getJSONObject(0);
			loginStatus = json.get("responseStatus").toString();
//			System.out.println(loginStatus);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return loginStatus;
	}
	
	/**
	 * 登录后续的操作
	 * @param url
	 * @return
	 */
	public static GetMethod createUrl(String url) {
		post.releaseConnection();
		get = new GetMethod(url);
		try {
			client.executeMethod(get);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return get;
	}
}
