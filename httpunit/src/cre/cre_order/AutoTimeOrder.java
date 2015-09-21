package cre.cre_order;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONObject;

public class AutoTimeOrder {
	private static String ticketNo;
	private static String deliveryDate;
	private static String deliverDate;
	private static String o_pcode;
	private static JSONObject jsontn = new JSONObject(); 
	private static JSONObject jsonop = new JSONObject(); 
	AutoTimeOrder(){
		ticketNo = "ticketNo";
		deliveryDate = "deliveryDate";
		deliverDate = "deliverDate";
		o_pcode = "o_pcode";
	}
	public void autoTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Calendar cal = Calendar.getInstance();
		String temps = "";
		String times = "";
		String orders = "";
		boolean bl = true;
		while (bl == true){
			temps = format.format(cal.getTime()).substring(0,10);
			times = format.format(cal.getTime()).substring(0,19);
			String regEx = "[^0-9]";
			Pattern pattern = Pattern.compile(regEx);  
			Matcher matcher = pattern.matcher(times); 
			StringBuffer sbr = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(sbr,"");
			}
			matcher.appendTail(sbr);
			orders = sbr.toString();	
			
			bl = false;
		}
		JSONObject jsondld = new JSONObject(); 
		JSONObject jsondd = new JSONObject();
		
		jsonop.put(o_pcode, orders);
		jsontn.put(ticketNo, orders);
		jsondld.put(deliveryDate, temps);
		jsondd.put(deliverDate, temps);
		jsontn.putAll(jsondld);
		jsontn.putAll(jsondd);		
	}	
	/**
     * 读取xml文件
     * @param fileName
     */ 
    public String parserXml(String fileName)  
    {    
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
    	String data = "[";
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element employees=document.getRootElement();   	//获取文档根节点 
            
            int count =0;
            for(Iterator<?> i = employees.elementIterator(); i.hasNext();){
            	
                Element employee = (Element) i.next();   
                Map<String, String> temp = new LinkedHashMap<String, String>(); 
	              for(Iterator<?> j = employee.elementIterator(); j.hasNext();){    
	                  Element node=(Element) j.next();                  
	                  String key = node.getName();
	                  String value = node.getText();
	                  temp.put(key, value);
	              }
	              JSONObject obj1 = JSONObject.fromObject( temp );
	              if(obj1.containsKey(o_pcode)){
	            	  obj1.putAll(jsonop);
	              } else {
		              obj1.putAll(jsontn);
	              }
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
        System.out.println(data);
        return data; 
    }
}
