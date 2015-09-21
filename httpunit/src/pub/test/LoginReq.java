package pub.test;

import java.util.ArrayList;

import com.test.Dom4jXml;

/**
 * 登录请求类，由登录的url，和登录用户信息构成，其中登录用户信息支持数组形式。
 *
 *
 */
public class LoginReq {
	private String url;	
	private ArrayList<LoginInfo> infos ;
	
	public LoginReq(){
		url = "";
		infos = new ArrayList<LoginInfo>();
	}
	
	/**
	 * 加载登录时的请求信息到数组models中
	 *
	 *
	 */
	
	public void loadloginReq(ArrayList<IpsModel> models, String path){		

		Dom4jXml.parserReqXml(path,  models);
				

	}
	
	/**
	 * 加载用户拥有的模块的请求信息到数组models中
	 * 
	 * 
	 */
	
	public void loadModelReqs(ArrayList<IpsModel> models, String postPath){		
		postPath  = "\\data\\standard\\posts_queue.xml";		
		Dom4jXml.parserModelXml(postPath,  models);	
				
	}
	
	public void  loadUrl(){		
			String path  = "\\data\\zptzt\\loginInfo.xml";		
			url = Dom4jXml.parserLogXml(path);
	}
	
	/**
	 * 加载登录信息到数组infos中
	 * 
	 * 
	 */
	
	public void loadInfos(String path){
		Dom4jXml.parserInfosXml(path, infos);
	}
	
	public String getDomain(){
		return this.url;
	}
	
	/**
	 * 返回请求用户数组信息 
	 * 
	 */
	
	public ArrayList<LoginInfo> getInfos() {
		return infos;
	}

	public void setInfos(ArrayList<LoginInfo> infos) {
		this.infos = infos;
	}

}
