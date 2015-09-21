package com.test;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.HttpException;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

import pub.test.ChanDate;
import pub.test.ChildMenu;
import pub.test.ExpoExcel;
import pub.test.IpsMenu;
import pub.test.IpsModel;
import pub.test.IpsRequest;
import pub.test.LoginInfo;
import pub.test.LoginReq;
import pub.test.ReqInstan;
import pub.test.SqlInfo;
import util.SendMail;


public class SimuReq {
	
	private ArrayList<IpsModel> models;		//登录模块的model
	private ArrayList<IpsModel> modReqs;	//所有请求队列的model
	private String domain;					//域名
	private WebConversation wc;				//建立一个WebConversation实例
	private WebRequest req;					//向指定的URL发出请求，获取响应
	private ArrayList<IpsMenu> iMenus;		// 菜单
	private SqlInfo	sqlInfo;				// sql连接串
	private String orgcode;					// 用户所属机构
	private ArrayList<String> gpsnos;		// 设备号
//	private ExpoExcel expExcel;				// 导出请求数据
//	private ReqInstan reqInstan;
	
	
	/*
	 * *
	 * @param 要转换的毫秒数
	 * @return 该毫秒数转换为 * seconds * mss 后的格式 
	 *
	 *
	 */
	private String formatDuring(long mss){
	    long seconds = mss / 1000;
	    long mills = mss % 1000;
	    return seconds + " seconds " + mills + "mills";
	}
	
	/*
	 * *
	 * 根据菜单和请求列表的匹配情况，模拟请求队列中的请求
	 * 
	 */
	
	
	public void matchAndRequest(ReqInstan reqInstan, ExpoExcel expExcel){
		for(int i=0 ; i< iMenus.size(); i++){
			IpsMenu menu = new IpsMenu();
			menu = iMenus.get(i);
			int cSize = iMenus.get(i).getcMenu().size();
			if(cSize == 0)
			{
				continue;
			}
			reqInstan.setMenuOne(menu.getLevelOne());
			
			for(int ii = 0; ii<cSize; ii++){
				ChildMenu cMenu = new ChildMenu();
				cMenu = menu.getcMenu().get(ii);
				String cMenuUrl = cMenu.getUrl();
				String cMenuName = cMenu.getName();
				
//				System.out.println(cMenuUrl);
				for(int j=0; j<modReqs.size(); j++){
					IpsModel model = new IpsModel();
					model = modReqs.get(j);
//					System.out.println(model.getModelName());
					if(cMenuUrl.equals(model.getModelName())){
						System.out.println(cMenuName + ":  hit====" + cMenuUrl);
						reqInstan.setMenuTwo(cMenuName);
						reqInstan.setModel(cMenuUrl);
						modelRequest(model, reqInstan, expExcel);
						break;
					}
					
				}
			}				
		}
	}
	
	private Boolean isDateType(String[] arr, String key ){
		Boolean isDate = false;
			for(int i=0; i<arr.length; i++){
				if(arr[i].equals(key)){
					isDate = true;
					break;
			}
		}
		
		return isDate;
	}
	
	/*
	 * *
	 * 根据传入的模块，拼接请求地址，给请求地址添加post参数，完成请求并记录到sheet表格中
	 * 
	 */
	
	private void modelRequest(IpsModel model, ReqInstan reqInstan, ExpoExcel expExcel){
		
		ArrayList<IpsRequest> iList = model.getIpsReqs();
		Map <String, String> params = new HashMap<String, String>();
		String[] arrTimes = {"starttime", "endtime", "from", "to", "createdate1",
				"createdate2","search_stime", "search_etime", "startdate",
				"enddate", "fromdate", "todate", "pickdate1", "pickdate2",
				"datefrom", "dateto", "senddate1", "senddate2",  "addtime1",
				"addtime2", "begintime", "start_time", "end_time"};
		
//		System.out.println(iList.size());
		
		for(int ii = 0; ii<iList.size(); ii++ ){
			
			IpsRequest ipsR = iList.get(ii);
			String reqUrl = "";
			reqUrl = model.getReqHead(domain, ii);
			
			System.out.println("reqUrl====" + reqUrl);
			reqInstan.setRequest(reqUrl);
			
			params = ipsR.getPostArgs();						
			Iterator<?> it = params.entrySet().iterator();						
			req = new PostMethodWebRequest(reqUrl);
			
			while (it.hasNext()) {
				//给post请求添加参数
		        Map.Entry entry = (Map.Entry) it.next();  
		        String key  = (String)entry.getKey();
		        String value = "";
		        if(isDateType(arrTimes, key)){
		        	value = ChanDate.caculateTime((String)entry.getValue());
		        }
		        else{
		        	if(key.equals("gpsnos")){
		        		value = gpsnos.toString();
		        	}
		        	else if(key.equals("orgcode")){
		        		value = orgcode;
		        	}
		        	else{
		        		value = (String)entry.getValue();
		        	}	        	
		        }
		        System.out.println(key + "======" + value);
		        req.setParameter(key, value);
		    }
			
			//获取响应对象
			WebResponse resp;
			try {
				long sysDateBefore = System.currentTimeMillis();
				resp = wc.getResponse(req);
				long sysDateAfter = System.currentTimeMillis();
				long during = sysDateAfter - sysDateBefore;
				reqInstan.setTimes(formatDuring(during));
				
				
				//用getText方法获取相应的全部内容
				//用System.out.println将获取的内容打印在控制台上
//				System.out.println("resp out == " + resp.getText());
				System.out.println(resp.getText().length());
				String res = "[" + resp.getText() + "]";
				
				System.out.println("code===" + resp.getResponseCode());
				reqInstan.setResponseCode(String.valueOf(resp.getResponseCode()));
				System.out.println("message===" + resp.getResponseMessage());
				reqInstan.setResponseMessage(resp.getResponseMessage());

				JSONArray outs = JSONArray.fromObject(res);
//				System.out.println(outs.size());
				for (int j = 0; j < outs.size(); j++) {
					JSONObject jsonout = outs.getJSONObject(j);
					
					System.out.println("code:==" + jsonout.getString("code"));
					reqInstan.setCode(jsonout.getString("code"));
					System.out.println("data:==" + jsonout.getString("data"));
					int resLength = jsonout.getString("data").length()>100? 100:jsonout.getString("data").length();
					reqInstan.setResult(jsonout.getString("data").substring(1, resLength));
					String inner = "[" + jsonout.getString("data") + "]";

					JSONArray inners = JSONArray.fromObject(inner);
//						System.out.println("inners==" + inners.toString());
//					for(int jj=0; jj<inners.size(); jj++){
//						JSONObject leveOne = inners.getJSONObject(jj);
//						for(int k = 0; k< iMenus.size(); k++){
//							String temp = iMenus.get(k).getSourceid();
//							System.out.println(temp);
//							JSONObject menu = (JSONObject)leveOne.get(temp);
//							if(menu!=null){
////									System.out.println(menu);
//								
//								JSONArray nodes = JSONArray.fromObject(menu.get("nodes"));
//								if(nodes.size()>0){
//									for(int kk = 0; kk < nodes.size(); kk++){
//										ChildMenu cMenu = new ChildMenu();
//										JSONObject node = nodes.getJSONObject(kk);
//										System.out.println(node.getString("url"));
//									}
//								}
//							}										
//							
////								System.out.println(menu);
//						}
//					}
//					
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(HttpException e){
				e.printStackTrace();
				reqInstan.setResponseMessage(e.getResponseMessage());
				e.getResponseCode();
				int code = e.getResponseCode();
				reqInstan.setResponseCode(Integer.toString(code));
			} catch(JSONException e){
				e.printStackTrace();
				reqInstan.setResponseMessage(e.getMessage());
				reqInstan.setResponseCode("unknown");
			}
			
			expExcel.setValue(reqInstan);
		}	
		
	}
	
	public void getGpsnos(String orgcode){
		try
		{		  
		  // 加载驱动程序
		  Class.forName(sqlInfo.getSqlDriver());
		  // 连续数据库
		  java.sql.Connection conn = DriverManager.getConnection(sqlInfo.getUrl(), sqlInfo.getUser(), sqlInfo.getPwd());
		
		  if(!conn.isClosed());
	  
			// 要执行的SQL语句
			String sql = "SELECT gpsno "
					+ " from ips_truck "
					+ " where "
					+ " orgcode  like '" + orgcode 
					+ "%' limit 20;";
			java.sql.Statement statement = conn.createStatement();
			// 结果集
			ResultSet rs = statement.executeQuery(sql);			
			
			while(rs.next()) {
				String gpsno = rs.getString("gpsno");
				if(gpsno.equals("0")){
					continue;
				}
				gpsnos.add(gpsno);
			}
			
			rs.close();
			conn.close();
		}
		 catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * *
	 * 获取用户的所属机构的orgcode
	 * 
	 * 
	 */
	
	public void getOrgcode(String userName){
		try
		{		  
		  // 加载驱动程序
		  Class.forName(sqlInfo.getSqlDriver());
		  // 连续数据库
		  java.sql.Connection conn = DriverManager.getConnection(sqlInfo.getUrl(), sqlInfo.getUser(), sqlInfo.getPwd());
		
		  if(!conn.isClosed());
		  
		  if(userName.equals("cre_admin")){
			  userName +="@cre";
		  }
		  if(userName.equals("cre_elt")){
			  userName +="@elt";
		  }
	  
			// 要执行的SQL语句
			String sql = "SELECT orgcode "
					+ " from ips_user "
					+ " where "
					+ " username = '" + userName 
					+ "';";
			java.sql.Statement statement = conn.createStatement();
			// 结果集
			ResultSet rs = statement.executeQuery(sql);			
			
			while(rs.next()) {
				orgcode = rs.getString("orgcode");
			}
			
			rs.close();
			conn.close();
		}
		 catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * *
	 * 通过登录的用户名信息去数据库获取功能菜单节点数据 
	 * 
	 * 
	 */
	
	public void getNodeids(String userName){
		try
		{		  
		  // 加载驱动程序
		  Class.forName(sqlInfo.getSqlDriver());
		  // 连续数据库
		  java.sql.Connection conn = DriverManager.getConnection(sqlInfo.getUrl(), sqlInfo.getUser(), sqlInfo.getPwd());
		
		  if(!conn.isClosed());
		  
		  if(userName.equals("cre_admin")){
			  userName += "@cre";
		  }
		  
		  if(userName.equals("cre_elt")){
			  userName += "@elt";
		  }
	  
			// 要执行的SQL语句
			String sql = "SELECT s.id, s.`name`, s.nodeids, s.nodenum "
					+ " from ips_source s, "
					+ " ips_role_source rs, "
					+ " ips_user u"
					+ " where u.username = '" + userName 
					+ "' and rs.roleid = u.roleids"
					+ " and s.id = rs.sid"
					+ " and s.parentid = 0";
			java.sql.Statement statement = conn.createStatement();

			// 结果集
			ResultSet rs = statement.executeQuery(sql);			
			
			while(rs.next()) {
				IpsMenu menu = new IpsMenu();
				menu.setLevelOne(rs.getString("name"));
				menu.setSourceid(rs.getString("id"));
				iMenus.add(menu);
			}		
			
			rs.close();
			conn.close();
		}
		 catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public SimuReq(){
		wc = new WebConversation();
		models = new ArrayList<IpsModel>();
		modReqs = new ArrayList<IpsModel>();
		domain = "";
		req = null;
		orgcode = "";
		gpsnos = new ArrayList<String>();
		iMenus = new ArrayList<IpsMenu>();
		sqlInfo = new SqlInfo();
//		expExcel = new ExpoExcel();
//		reqInstan = new ReqInstan();
	}
	
	
	
	/*
	 * 读取sql配置文件
	 * 
	 * flag,环境标记         3:取配通/E路通正式 ; 4:ztkddemo环境,因为正是环境无法连接到数据库
	 *               5:狮桥融资55环境，因为demo和正是都在阿里云上
	 * 
	 * */
	public void InitSqlInfo(int flag){
		String path ="" ;
		switch(flag){
			case 1:
				path = "\\data\\sqlInfo\\sqlInfo_standard_55.xml";
				break;
			case 2:
				path = "\\data\\sqlInfo\\sqlInfo_standard_demo.xml";
				break;
			case 3:
				path = "\\data\\sqlInfo\\sqlInfo_cre.xml";
				break;
			case 4:
				path = "\\data\\sqlInfo\\sqlInfo_ztkddemo.xml";
				break;
			case 5:
				path = "\\data\\sqlInfo\\sqlInfo_sqrz_55.xml";
				break;
			default:
				break;
		}
		
		sqlInfo.ReadSqlInfo(path);
//		System.out.println(sqlInfo.getSqlDriver());
	}
	
	/*
	 * *
	 * 模拟用户登录模块的请求
	 * 
	 */
	
	public void simuLoginRequest(String name, String pwd){
		
		String reqUrl = "";
		Map <String, String> params = new HashMap<String, String>();
		
		System.out.println(models.size());
		
		for(int i = 0; i<models.size(); i++){
			IpsModel model = new IpsModel();
			model = models.get(i);
			if(model.getModelName().equals("login/index.html")){
				reqUrl = model.getReqHead(domain, i);
				params = model.getIpsReqs().get(0).getPostArgs();
				Iterator it = params.entrySet().iterator();
				
				req = new PostMethodWebRequest(reqUrl);
				while (it.hasNext()) {
					//给post请求添加参数
			        Map.Entry entry = (Map.Entry) it.next();  
			        String key  = (String)entry.getKey();	  
			        String value = (String)entry.getValue();
			        req.setParameter(key, value);
			    }
				
				// 需要用登录信息覆盖请求参数
				
				req.setParameter("username", name);
				req.setParameter("password", pwd);
				
				//获取响应对象
				WebResponse resp;
				try {
					resp = wc.getResponse(req);				

					String res = "[" + resp.getText() + "]";

					JSONArray outs = JSONArray.fromObject(res);
					for (int j = 0; j < outs.size(); j++) {
						JSONObject jsonout = outs.getJSONObject(j);

						String inner = "[" + jsonout.get("data") + "]";
						JSONArray inners = JSONArray.fromObject(inner);
						for (int k = 0; k < inners.size(); k++) {
							JSONObject jsoninner = inners.getJSONObject(j);
						}
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		
		
	}
	
	/*
	 * *
	 * 给二级菜单 的url和name赋值，并且add到列表容器中
	 * 
	 */
	
	public void getConfMenu(){
		String reqUrl = "";
		Map <String, String> params = new HashMap<String, String>();
		
		System.out.println(models.size());		

		for(int i = 0; i<models.size(); i++){
			IpsModel model = new IpsModel();
			model = models.get(i);
			if(model.getModelName().equals("getconfigleftmenu")){
				reqUrl = model.getReqHead(domain, i);
				ArrayList<IpsRequest> iList = model.getIpsReqs();
				System.out.println(iList.size());
				for(int ii = 0; ii<iList.size(); ii++ ){
					if(ii == 0){
						IpsRequest ipsR = iList.get(ii);
						
						params = ipsR.getPostArgs();						
						Iterator it = params.entrySet().iterator();						
						req = new PostMethodWebRequest(reqUrl);
						
						while (it.hasNext()) {
							//给post请求添加参数
					        Map.Entry entry = (Map.Entry) it.next();  
					        String key  = (String)entry.getKey();	  
					        String value = (String)entry.getValue();
					        req.setParameter(key, value);	        
					    }
						
						//获取响应对象
						WebResponse resp;
						try {
							resp = wc.getResponse(req);
							
							String res = "[" + resp.getText() + "]";

							JSONArray outs = JSONArray.fromObject(res);
							System.out.println(outs.size());
							for (int j = 0; j < outs.size(); j++) {
								JSONObject jsonout = outs.getJSONObject(j);

								String inner = "[" + jsonout.getString("data") + "]";

								JSONArray inners = JSONArray.fromObject(inner);
//								System.out.println("inners==" + inners.toString());
								for(int jj=0; jj<inners.size(); jj++){
									JSONObject leveOne = inners.getJSONObject(jj);
									for(int k = 0; k< iMenus.size(); k++){
										String temp = iMenus.get(k).getSourceid();
//										System.out.println(temp);
										JSONObject menu = (JSONObject)leveOne.get(temp);
										if(menu!=null){
											System.out.println(menu);
											
											JSONArray nodes = JSONArray.fromObject(menu.get("nodes"));
											if(nodes.size()>0){
												for(int kk = 0; kk < nodes.size(); kk++){
													ChildMenu cMenu = new ChildMenu();
													JSONObject node = nodes.getJSONObject(kk);
													cMenu.setUrl(node.getString("url"));
													cMenu.setName(node.getString("name"));
													iMenus.get(k).getcMenu().add(cMenu);
												}
											}
										}										
										
//										System.out.println(menu);
									}
								}
								
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}					
					
				}		
				
			}
		}	
	}
	
 
  public void cre() {	  
	  
	  	InitSqlInfo(3);	  				// 读取数据库连接串信息，便于后续的数据库操作
	  	
	  	String path = "\\data\\zptzt\\loginInfo.xml";
	  	String postPath = "\\data\\standard\\posts_queue.xml";
	  	LoginReq lReq = new LoginReq();
	  	lReq.loadInfos(path);			// 加载用户登录信息
	  	models.clear();
	  	modReqs.clear();
	  	lReq.loadloginReq(models, path);// 加载用户登录时的请求模块信息
	  	lReq.loadModelReqs(modReqs, postPath);	// 加载配置文件中的所有模块请求，便于跟用户的模块信息相匹配
	  	
	  	ReqInstan reqInstan = new ReqInstan();
	  	ExpoExcel expExcel = new ExpoExcel();
	  	
	  	ArrayList<LoginInfo> infos = lReq.getInfos();
	  	reqInstan.setNum(infos.size());	// 设置sheet页的数量
	  	
	  	for(int i=0; i<infos.size(); i++){
	  		LoginInfo info = infos.get(i);
	  		domain = info.getUrl();		// 加载用户的登录的url
		  	
//		  	System.out.println(modReqs.size());

		  	String userName = info.getUsername();
		  	
		  	iMenus.clear();		//每次循环都会add数据，为保证iMenus是干净的数组，需要清空上次的数组信息
		  	  	
		  	getNodeids(userName);
		  	
		  	reqInstan.setUser(userName);	// 用用户名信息来命名表格的sheet名
		  	reqInstan.setNums(i);			// 设置待写入的sheet序号，从0开始
		  	
		  	expExcel.dataFile(reqInstan, i, userName);		// 写入表格名称和头部信息
		  	
		  	getOrgcode(userName);			// 获取用户的所属机构
		  	
		  	getGpsnos(orgcode);	  			// 根据orgcode获取20个设备号，便于对这些设备进行状态的请求
		  	
		  	simuLoginRequest(userName, info.getPassword());
		  	
		  	getConfMenu();
		  	
//		  	System.out.println("iMenus.size()====" + iMenus.size());
//		  	for(int ii = 0; ii<iMenus.size(); ii++){
//		  		System.out.println(iMenus.get(ii).getLevelOne());
//		  		System.out.println(iMenus.get(ii).getSourceid());
//		  	}
		  	
		  	matchAndRequest(reqInstan, expExcel);
		  	
		  	System.out.println("第"+ i +"次finished!!");
		  	
		  	wc = null;		// 结束当前对话，为下次会话做初始化准备
		  	wc = new WebConversation();
		  	
	  	}	  
	  	expExcel.close();
	  	
  	}
  
   /**
    * 发送带附件的邮件 
    * 暂时发送给test.list@huoyunren.com
    * 
    */
  
  	public void sendMail(){
  		String pathPro = System.getProperty("user.dir");		
		pathPro += "\\out";		
		
		ArrayList<String> paths = new ArrayList<String>();
		File dir = new File(pathPro);
		File file[] = dir.listFiles();
		
		String time = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		time = format.format(date);
		
		// 获取带发送的附件
		for (int i = 0; i < file.length; i++) {
			// 过滤svn文件
		    if(file[i].toString().indexOf(".svn")>=0){
		    	continue;
		    }
		    if(file[i].toString().indexOf(time)>=0){
		    	paths.add(file[i].toString());
		    }
		    
		} 
		
		SendMail send = new SendMail();
		send.setHost("smtp.263.net");
		send.setFrom("xxx");
		send.setPassWord("xx");
		send.setUserName("xxx");		
		send.setTo("xxx");
		send.setSubject("每日冒烟流程");
		send.setContent("冒烟流程结果见附件,正常情况有4个附件");
		System.out.println("发送成功返回true,发送失败返回false：" + send.sendMail(paths));
  	}
  

	public void elt(){
		InitSqlInfo(3);	  				// 读取数据库连接串信息，便于后续的数据库操作
		
		String path = "\\data\\elt\\loginInfo.xml";
		String postPath = "\\data\\standard\\posts_queue.xml";
		LoginReq lReq = new LoginReq();
		lReq.loadInfos(path);				// 加载用户登录信息
		models.clear();
		modReqs.clear();
		lReq.loadloginReq(models, path);	// 加载用户登录时的请求模块信息
		
		lReq.loadModelReqs(modReqs, postPath);	// 加载配置文件中的所有模块请求，便于跟用户的模块信息相匹配
		  	
		ArrayList<LoginInfo> infos = lReq.getInfos();
		
		ReqInstan reqInstan = new ReqInstan();
		ExpoExcel expExcel = new ExpoExcel();
		
		reqInstan.setNum(infos.size());	// 设置sheet页的数量
		  	
		for(int i=0; i<infos.size(); i++){
			LoginInfo info = infos.get(i);
			domain = info.getUrl();		// 加载用户的登录的url
			  	
	//		  	System.out.println(modReqs.size());
	
			String userName = info.getUsername();
			  	
			iMenus.clear();		//每次循环都会add数据，为保证iMenus是干净的数组，需要清空上次的数组信息
			  	  	
			getNodeids(userName);
			  	
			reqInstan.setUser(userName);	// 用用户名信息来命名表格的sheet名
			reqInstan.setNums(i);			// 设置待写入的sheet序号，从0开始
			  	
			expExcel.dataFile(reqInstan, i, userName);		// 写入表格名称和头部信息
			  	
			getOrgcode(userName);			// 获取用户的所属机构
			  	
			getGpsnos(orgcode);	  			// 根据orgcode获取20个设备号，便于对这些设备进行状态的请求
			  	
			simuLoginRequest(userName, info.getPassword());
			  	
			getConfMenu();
			  	
	//		  	System.out.println("iMenus.size()====" + iMenus.size());
	//		  	for(int ii = 0; ii<iMenus.size(); ii++){
	//		  		System.out.println(iMenus.get(ii).getLevelOne());
	//		  		System.out.println(iMenus.get(ii).getSourceid());
	//		  	}
			  	
			matchAndRequest(reqInstan, expExcel);
			  	
			System.out.println("第"+ i +"次finished!!");
			  	
			wc = null;		// 结束当前对话，为下次会话做初始化准备
			wc = new WebConversation();
			  	
		}	  
		expExcel.close();
	}
	
	public void ztkd(){
		InitSqlInfo(4);	  				// 读取数据库连接串信息，便于后续的数据库操作
		
		String path = "\\data\\ztkd\\loginInfo.xml";
		String postPath = "\\data\\standard\\posts_queue.xml";
		LoginReq lReq = new LoginReq();
		lReq.loadInfos(path);				// 加载用户登录信息
		models.clear();
		modReqs.clear();
		lReq.loadloginReq(models, path);	// 加载用户登录时的请求模块信息
		
		lReq.loadModelReqs(modReqs, postPath);	// 加载配置文件中的所有模块请求，便于跟用户的模块信息相匹配
		  	
		ArrayList<LoginInfo> infos = lReq.getInfos();
		
		ReqInstan reqInstan = new ReqInstan();
		ExpoExcel expExcel = new ExpoExcel();
		
		reqInstan.setNum(infos.size());	// 设置sheet页的数量
		  	
		for(int i=0; i<infos.size(); i++){
			LoginInfo info = infos.get(i);
			domain = info.getUrl();		// 加载用户的登录的url
			  	
	//		  	System.out.println(modReqs.size());
	
			String userName = info.getUsername();
			  	
			iMenus.clear();		//每次循环都会add数据，为保证iMenus是干净的数组，需要清空上次的数组信息
			  	  	
			getNodeids(userName);
			  	
			reqInstan.setUser(userName);	// 用用户名信息来命名表格的sheet名
			reqInstan.setNums(i);			// 设置待写入的sheet序号，从0开始
			  	
			expExcel.dataFile(reqInstan, i, userName);		// 写入表格名称和头部信息
			  	
			getOrgcode(userName);			// 获取用户的所属机构
			  	
			getGpsnos(orgcode);	  			// 根据orgcode获取20个设备号，便于对这些设备进行状态的请求
			  	
			simuLoginRequest(userName, info.getPassword());
			  	
			getConfMenu();
			  	
	//		  	System.out.println("iMenus.size()====" + iMenus.size());
	//		  	for(int ii = 0; ii<iMenus.size(); ii++){
	//		  		System.out.println(iMenus.get(ii).getLevelOne());
	//		  		System.out.println(iMenus.get(ii).getSourceid());
	//		  	}
			  	
			matchAndRequest(reqInstan, expExcel);
			  	
			System.out.println("第"+ i +"次finished!!");
			  	
			wc = null;		// 结束当前对话，为下次会话做初始化准备
			wc = new WebConversation();
			  	
		}	  
		expExcel.close();
	}
	

	public void sqrz(){
		InitSqlInfo(5);	  				// 读取数据库连接串信息，便于后续的数据库操作
		
		String path = "\\data\\sqrz\\loginInfo.xml";
		String postPath = "\\data\\standard\\posts_queue.xml";
		LoginReq lReq = new LoginReq();
		lReq.loadInfos(path);				// 加载用户登录信息
		models.clear();
		modReqs.clear();
		lReq.loadloginReq(models, path);	// 加载用户登录时的请求模块信息
		
		lReq.loadModelReqs(modReqs, postPath);	// 加载配置文件中的所有模块请求，便于跟用户的模块信息相匹配
		  	
		ArrayList<LoginInfo> infos = lReq.getInfos();
		
		ReqInstan reqInstan = new ReqInstan();		
		ExpoExcel expExcel = new ExpoExcel();
		
		reqInstan.setNum(infos.size());	// 设置sheet页的数量
		  	
		for(int i=0; i<infos.size(); i++){
			LoginInfo info = infos.get(i);
			domain = info.getUrl();		// 加载用户的登录的url
			  	
	//		  	System.out.println(modReqs.size());
	
			String userName = info.getUsername();
			  	
			iMenus.clear();		//每次循环都会add数据，为保证iMenus是干净的数组，需要清空上次的数组信息
			  	  	
			getNodeids(userName);
			  	
			reqInstan.setUser(userName);	// 用用户名信息来命名表格的sheet名
			reqInstan.setNums(i);			// 设置待写入的sheet序号，从0开始
			  	
			expExcel.dataFile(reqInstan, i, userName);		// 写入表格名称和头部信息
			  	
			getOrgcode(userName);			// 获取用户的所属机构
			  	
			getGpsnos(orgcode);	  			// 根据orgcode获取20个设备号，便于对这些设备进行状态的请求
			  	
			simuLoginRequest(userName, info.getPassword());
			  	
			getConfMenu();
			  	
	//		  	System.out.println("iMenus.size()====" + iMenus.size());
	//		  	for(int ii = 0; ii<iMenus.size(); ii++){
	//		  		System.out.println(iMenus.get(ii).getLevelOne());
	//		  		System.out.println(iMenus.get(ii).getSourceid());
	//		  	}
			  	
			matchAndRequest(reqInstan, expExcel);
			  	
			System.out.println("第"+ i +"次finished!!");
			  	
			wc = null;		// 结束当前对话，为下次会话做初始化准备
			wc = new WebConversation();
			  	
		}	  
		expExcel.close();
	}
	
	public void standard(){
		InitSqlInfo(2);	  				// 读取数据库连接串信息，便于后续的数据库操作
		
		String path = "\\data\\standard\\loginInfo.xml";
		String postPath = "\\data\\standard\\posts_queue.xml";
		LoginReq lReq = new LoginReq();
		lReq.loadInfos(path);				// 加载用户登录信息
		models.clear();
		modReqs.clear();
		lReq.loadloginReq(models, path);	// 加载用户登录时的请求模块信息
		
		lReq.loadModelReqs(modReqs, postPath);	// 加载配置文件中的所有模块请求，便于跟用户的模块信息相匹配
		  	
		ArrayList<LoginInfo> infos = lReq.getInfos();
		
		ReqInstan reqInstan = new ReqInstan();		
		ExpoExcel expExcel = new ExpoExcel();
		
		reqInstan.setNum(infos.size());	// 设置sheet页的数量
		  	
		for(int i=0; i<infos.size(); i++){
			LoginInfo info = infos.get(i);
			domain = info.getUrl();		// 加载用户的登录的url
			  	
	//		  	System.out.println(modReqs.size());
	
			String userName = info.getUsername();
			  	
			iMenus.clear();		//每次循环都会add数据，为保证iMenus是干净的数组，需要清空上次的数组信息
			  	  	
			getNodeids(userName);
			  	
			reqInstan.setUser(userName);	// 用用户名信息来命名表格的sheet名
			reqInstan.setNums(i);			// 设置待写入的sheet序号，从0开始
			  	
			expExcel.dataFile(reqInstan, i, userName);		// 写入表格名称和头部信息
			  	
			getOrgcode(userName);			// 获取用户的所属机构
			  	
			getGpsnos(orgcode);	  			// 根据orgcode获取20个设备号，便于对这些设备进行状态的请求
			  	
			simuLoginRequest(userName, info.getPassword());
			  	
			getConfMenu();
			  	
	//		  	System.out.println("iMenus.size()====" + iMenus.size());
	//		  	for(int ii = 0; ii<iMenus.size(); ii++){
	//		  		System.out.println(iMenus.get(ii).getLevelOne());
	//		  		System.out.println(iMenus.get(ii).getSourceid());
	//		  	}
			  	
			matchAndRequest(reqInstan, expExcel);
			  	
			System.out.println("第"+ i +"次finished!!");
			  	
			wc = null;		// 结束当前对话，为下次会话做初始化准备
			wc = new WebConversation();
			  	
		}	  
		expExcel.close();
	}
	
	@Test
	public void runSend(){
//		sqrz();
//		ztkd();
//		elt();
//		cre();
//		sendMail();
		standard();
	}
}
