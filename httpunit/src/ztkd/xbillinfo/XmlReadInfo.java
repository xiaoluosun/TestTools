package ztkd.xbillinfo;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pub.test.ConnectInfo;
import pub.test.GenCommon;
import pub.test.MD5Util;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class XmlReadInfo {
	protected static Object emp;
	
	XmlReadInfo(){
		emp = "";
	}
	protected String Encrypt(ConnectInfo conn, String times, String objJson) {
		String param1 = conn.getApp_secret() 
						+ "app_key" + conn.getApp_key()
						+ "data" + objJson
						+ "method" + conn.getMethod()
						+ "timestamp" + times 
						+ conn.getApp_secret();

			byte[] bparam1 = param1.getBytes();

			return MD5Util.getMD5(bparam1);
		}
	public void readXmlSqlConn(ConnectInfo conn, String path) {
		String pathPro = System.getProperty("user.dir");
		
		// 数据完整路径
		String strfilePath = pathPro + path;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = dbf.newDocumentBuilder();
			Document doc;
			// 获取到xml文件
			File f = new File(strfilePath);
			doc = builder.parse(f);
			//下面开始读取
			Element root = doc.getDocumentElement(); //获取根元素

			NodeList infoList = root.getElementsByTagName("ConnInfo");
			// 读取连接串信息

			// 下面开始读取
			Element sqlList = (Element) infoList.item(0);
			
			NodeList nl = sqlList.getElementsByTagName("hostUrl");
			Element e = (Element) nl.item(0);
			Node t = e.getFirstChild();
			conn.setHostUrl(t.getNodeValue());
			
			nl = root.getElementsByTagName("app_key");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			conn.setApp_key(t.getNodeValue());

			nl = root.getElementsByTagName("app_secret");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			conn.setApp_secret(t.getNodeValue());
			
			nl = root.getElementsByTagName("method");
			e = (Element) nl.item(0);
			t = e.getFirstChild();
			conn.setMethod(t.getNodeValue());			
			

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void resGet(String strobj, ConnectInfo conn) {
		//获取当前时间
		String curTime = GenCommon.getCurTime();
		
		
		String strBill = GenCommon.GBK2Unicode(strobj);
		
		String sign = Encrypt(conn, curTime, strBill);

		//模拟浏览器对象,拥有一个浏览器
		WebConversation webConversation = new WebConversation();
		//用get方法得到 一个请求对象 
		String webRequest = "http://" + conn.getHostUrl() 
							+ "/rest/index.php?"
							+ "timestamp=" + curTime 
							+ "&app_key=" + conn.getApp_key()
							+ "&method=" + conn.getMethod() 
							+ "&data=" + strBill
							+ "&sign=" + sign.toUpperCase();

		System.out.println(webRequest);

		WebRequest request = new GetMethodWebRequest(webRequest);
		//获取响应对象
		WebResponse resp;
		try {

			resp = webConversation.getResponse(request);
			
			//用getText方法获取相应的全部内容，并将获取的内容打印在控制台上
			System.out.println(resp.getText());			
			
			String res = "[" + resp.getText() + "]";

			JSONArray outs = JSONArray.fromObject(res);
			for (int i = 0; i < outs.size(); i++) {
				JSONObject jsonout = outs.getJSONObject(i);
				System.out.println("code:" + jsonout.get("code"));

				String inner = "[" + jsonout.get("data") + "]";
				JSONArray inners = JSONArray.fromObject(inner);
				for (int j = 0; j < inners.size(); j++) {
					JSONObject jsoninner = inners.getJSONObject(i);					
					System.out.println("data:result:"
							+ jsoninner.get("result"));
			
					System.out.println("data:params:" + jsoninner.get("params"));
					
					emp = jsoninner.get("result");	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	} 
}
