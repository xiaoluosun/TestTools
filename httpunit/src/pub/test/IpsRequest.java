package pub.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IpsRequest {
	private String t;		//请求数据返回类型
	private String m;		//模块
	private String f;		//方法
	private String opurl;	//请求的页面
	private Map<String, String> postArgs;	//请求传入的参数
	
	public IpsRequest(){
		t = "";
		m = "";
		f = "";
		opurl = "";
		postArgs = new HashMap<String, String>();
	}
	
	
	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getOpurl() {
		return opurl;
	}

	public void setOpurl(String opurl) {
		this.opurl = opurl;
	}

	public Map<String, String> getPostArgs() {
		return postArgs;
	}

	public void setPostArgs(Map<String, String> postArgs) {
		this.postArgs = postArgs;
	}
	
	// 传入域名，拼接好请求的头部并返回
	public String reqHead(String domain){
		
		String urls = "http://" + domain + "/inside.php?";
		
		urls += "t=" + t;
		urls += "&m=" + m;
		urls += "&f=" + f;
		urls += "&opurl=" + opurl;
		return urls;
	}

}
