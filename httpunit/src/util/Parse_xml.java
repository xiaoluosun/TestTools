package util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Parse_xml {
    //参数
	public  String url;
	public  String user;
	public  String password;
	public  String driverName;
	
	//读取xml
	public void readXml(String fileName){
		SAXReader reader=new SAXReader();
		File file=new File(fileName);
		try {
			Document doc=reader.read(file);
			Element rootEle=doc.getRootElement();
			List<Element> list=rootEle.elements();
			parse(list);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//解析
	private void parse(List<Element> list){
		Iterator<Element> it=list.iterator();
		if(it.hasNext()){
			Element ele=it.next();
			url=ele.elementText("url");
			user=ele.elementText("user");
			password=ele.elementText("password");
			driverName=ele.elementText("driverName");
		}
		else{
			System.out.println("没有对应的参数");
		}		
	}
}
