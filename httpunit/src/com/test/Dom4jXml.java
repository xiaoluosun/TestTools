package com.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import pub.test.IpsModel;
import pub.test.IpsRequest;
import pub.test.LoginInfo;

public class Dom4jXml {
	/**
     * 读取xml文件
     * @param fileName string	文件路径
     */ 
    public static String parserXml(String fileName)  
    {    
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
    	String data = "[";
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element employees=document.getRootElement();   	//获取文档根节点 
//            System.out.println(employees);
            int count =0;
            
            for(Iterator<?> i = employees.elementIterator(); i.hasNext();){
            	
                Element employee = (Element) i.next();   
//                System.out.println(employee);
                Map<String, String> temp = new LinkedHashMap<String, String>(); 
	              for(Iterator<?> j = employee.elementIterator(); j.hasNext();){    
	                  Element node=(Element) j.next();                  
	                  String key = node.getName();
	                  String value = node.getText();
	                  temp.put(key, value);
	              }
	              JSONObject obj1 = JSONObject.fromObject( temp );
	              if(count>0){
	            	  data +=",";		              
	              }
	              data += obj1.toString();
                  count ++;
            }
            
           
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        } 
        data +="]";
//        System.out.println(data);
        return data;
           
    }
    
    /**
     * 读取xml文件
     * @param fileName string	文件路径
     * @param nums	int	一次请求传送的数据，如果只传一个请写"1"，多个的话根据实际情况填写
     * @param params ArrayList<String> 需要确定哪些字段不能重复，在程序里面要处理
     * , int nums, ArrayList<String> params
     */ 
    public static String parserXmlNew(String fileName, int nums,  ArrayList<String> params)  
    {    
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
    	String data = "[";
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element order=document.getRootElement();   	//获取文档根节点
            Map<String, String> temp = new LinkedHashMap<String, String>();
            
            // 遍历xml字段名和字段值，保存到临时映射表
            for(Iterator<?> i = order.elementIterator(); i.hasNext();){
            	
                Element field = (Element) i.next();
                System.out.println(field.getName());
                System.out.println(field.getText());
                temp.put(field.getName(), field.getText());
            }
            
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");//可以方便地修改日期格式


            String time = dateFormat.format( now );
            System.out.println(time);
            
            // 根据传入的记录数，做循环
            for(int j=0; j<nums; j++){
            	for (Iterator it =  temp.keySet().iterator();it.hasNext();)
                {
                	Object key = it.next();
                	System.out.println(key.toString());
                	System.out.println( temp.get(key));
                	
                	if(params.contains(key)){                		
                		// 截取字符串更换最后的序号
                		if(j>0){
                			String value = temp.get(key);
                			int index = value.indexOf(time);
                			if(index>=0){
                				String valuetemp = value.substring(0, index + time.length());
                				valuetemp += j;
                				temp.put(key.toString(),valuetemp);
                			}
                		}
                		else{
                			temp.put(key.toString(),temp.get(key)+ time +j);
                		}
//                		System.out.println(temp.get(key)+ time +j);
                	}
                	
                }
            	JSONObject obj1 = JSONObject.fromObject( temp );
	              if(j>0){
	            	  data +=",";		              
	              }
	              data += obj1.toString();
            }            
            
           
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        } 
        data +="]";
//        System.out.println(data);
        return data;
           
    }
    
    /**
     * 读取xml文件
     * @param fileName
     */ 
    public static LinkedHashMap<String, String> getListMap(String fileName)  
    {    
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
    	LinkedHashMap<String, String> temp = new LinkedHashMap<String, String>();
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element employees=document.getRootElement();   	//获取文档根节点 
//            System.out.println(employees);
            
            for(Iterator<?> i = employees.elementIterator(); i.hasNext();){
            	
                Element employee = (Element) i.next();   
//                System.out.println(employee);
                 
	              for(Iterator<?> j = employee.elementIterator(); j.hasNext();){    
	                  Element node=(Element) j.next();                  
	                  String key = node.getName();
	                  String value = node.getText();
	                  temp.put(key, value);
	              }
            }
            
           
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        } 
//        System.out.println(data);
        return temp;
           
    }
    
    /**
     * 读取登录xml文件的请求信息
     * @param fileName
     */ 
    public static void parserReqXml(String fileName,  ArrayList<IpsModel> models)  
    {    
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element rootNode=document.getRootElement();   	//获取文档根节点            
            
            List<Element> employees = rootNode.elements("model");    
            
            for(int i = 0; i< employees.size(); i++){
            	
            	IpsModel model = new IpsModel();
            	
                Element employee = (Element) employees.get(i);
                String modName =  employee.getText();
                
                //去除回车和换行
                int index = modName.indexOf("\n\t");
                String key = index > 0 ? modName.substring(0, index) : modName;
                
                if(employee.getName().equals("model") ){
                	
                	model.setModelName(key);
                	System.out.println(key);
                	
                	List<Element> reqlists = employee.elements();
                	System.out.println(reqlists.size());
                	for(int kk =0; kk<reqlists.size(); kk++ ){
                		Element req = (Element) reqlists.get(kk);
                    	List<Element> nodes = req.elements();
                    	IpsRequest iReq = new IpsRequest();
                    	for(int j = 0; j<nodes.size(); j++){
                    		
                    		Element node = (Element)nodes.get(j);
                    		if(node.getName() == "t"){
                    			iReq.setT(node.getText());
                    		}
                    		else if(node.getName() == "m"){
                    			iReq.setM(node.getText());
                    		}
                    		else if(node.getName() == "f"){
                    			iReq.setF(node.getText());
                    		}
                    		else if(node.getName() == "opurl"){
                    			iReq.setOpurl(node.getText());
                    		}
                    		else if(node.getName() == "post"){
                    			List<Element> params = node.elements();
                    			Map<String, String> maps = new HashMap<String, String> ();
                    			for(int k = 0; k <params.size(); k++){
                    				Element param = (Element)params.get(k);
                    				maps.put(param.getName(), param.getText());
                    			}
                    			iReq.setPostArgs(maps);
                    		}                		
                    		
                    	}                	
                    	model.getIpsReqs().add(iReq);   
                	}                	            	
                	
                	
                if(model.getModelName().length()>0){
                	models.add(model);
                }                
            }
            }
            
            
           
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        }           
    }
    
    /**
     * 读取请求集合xml文件的请求信息
     * @param fileName
     */ 
    public static void parserModelXml(String fileName,  ArrayList<IpsModel> models)  
    {    
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element rootNode=document.getRootElement();   	//获取文档根节点            
            
//            List<Element> employees = rootNode.elements("model");
            
            List eleList = rootNode.elements();
//            System.out.println(eleList.size());            
            for(int ii = 0; ii < eleList.size(); ii++){
            	Element module = (Element) eleList.get(ii);
            	
            	List employees =  module.elements();
            	
            	for(int i = 0; i< employees.size(); i++){
                	
                	IpsModel model = new IpsModel();
                	
                    Element employee = (Element) employees.get(i);
                    String modName =  employee.getText();
                    
                    //去除回车和换行
                    int index = modName.indexOf("\n\t");
                    String key = index > 0 ? modName.substring(0, index) : modName;
                    
                    if(employee.getName().equals("model") ){
                    	
                    	model.setModelName(key);
//                    	System.out.println(key);
                    	
                    	List<Element> reqlists = employee.elements();
//                    	System.out.println(reqlists.size());
                    	for(int kk =0; kk<reqlists.size(); kk++ ){
                    		Element req = (Element) reqlists.get(kk);
                        	List<Element> nodes = req.elements();
                        	IpsRequest iReq = new IpsRequest();
                        	for(int j = 0; j<nodes.size(); j++){
                        		
                        		Element node = (Element)nodes.get(j);
                        		if(node.getName() == "t"){
                        			iReq.setT(node.getText());
                        		}
                        		else if(node.getName() == "m"){
                        			iReq.setM(node.getText());
                        		}
                        		else if(node.getName() == "f"){
                        			iReq.setF(node.getText());
                        		}
                        		else if(node.getName() == "opurl"){
                        			iReq.setOpurl(node.getText());
                        		}
                        		else if(node.getName() == "post"){
                        			List<Element> params = node.elements();
                        			Map<String, String> maps = new HashMap<String, String> ();
                        			for(int k = 0; k <params.size(); k++){
                        				Element param = (Element)params.get(k);
                        				maps.put(param.getName(), param.getText());
                        			}
                        			iReq.setPostArgs(maps);
                        		}                		
                        		
                        	}                	
                        	model.getIpsReqs().add(iReq);   
                    	}                	            	
                    	
                    }
                    if(model.getModelName().length()>0){
                    	models.add(model);
                    }                
                }
            }           
            
           
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        }           
    }
    
    /**
     * 读取登录xml文件的url
     * @param fileName
     */ 
    public static String parserLogXml(String fileName)  
    {    
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
    	String text = "";
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element employees=document.getRootElement();   	//获取文档根节点 
            
            Element ele = (Element)employees.element("url");
            text = ele.getText();            
         
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        }
        return text;
    }
    
    /**
     * 读取登录xml文件的loginInfo
     * @param fileName
     */ 
    public static void parserInfosXml(String fileName, ArrayList<LoginInfo> infos)  
    {    
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
    	String text = "";
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element employees=document.getRootElement();   	//获取文档根节点 
            
            List eleList = employees.elements("loginInfo");
            System.out.println(eleList.size());
            
            for(int i=0; i<eleList.size(); i++){
            	LoginInfo logInfo = new LoginInfo();
            	Element eInfo = (Element) eleList.get(i);
            	logInfo.setUrl(eInfo.elementText("url"));
            	logInfo.setUsername(eInfo.elementText("username"));
            	logInfo.setPassword(eInfo.elementText("password"));
            	
            	infos.add(logInfo);
            }            
          
         
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        }
    }

}
