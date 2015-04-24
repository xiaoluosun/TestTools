package com.allinmd.html5;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jXml {	
    private static Map<String, String> temp = new LinkedHashMap<String, String>(); 
	private static Map<String, String> regInfo = new LinkedHashMap<String, String>();
	private static String regInfoPath;
	private static int num;
    
    public Dom4jXml(){
		regInfoPath = "";
		num = 1;
    }
    
    public Map<String, String> infoPath(){
    	switch(num){
    		case 1:				  	
    			regInfoPath = "\\data\\html5\\register_test_info.xml";					//注册账号数据
    			regInfo = parserXml(regInfoPath);
    			break;
    			
    		case 2:				  	
    			regInfoPath = "\\data\\html5\\register_certifi_test_info.xml";			//注册&认证的数据
    			regInfo = parserXml(regInfoPath);
    			break;
    			
    		default:
    			break;
    	}
		return regInfo;   		
    }

	/**
	 * 读取xml文件
	 * @param fileName string	文件路径
	 */ 
    public Map<String, String> parserXml(String regInfoPath) {    
    	String path = System.getProperty("user.dir");
    	path += regInfoPath;
    	File inputXml = new File(path);
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element employees = document.getRootElement();   	//获取文档根节点 
            
            for(Iterator<?> i = employees.elementIterator(); i.hasNext();) {            	
                Element employee = (Element) i.next();   
				for(Iterator<?> j = employee.elementIterator(); j.hasNext();) {    
					Element node = (Element) j.next();                  
					String key = node.getName();
					String value = node.getText();
					temp.put(key, value);
				}
            }           
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        }  
        return temp;
    }
}
