package ztkd.xbillinfo;

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

public class AutoOrderByTime {

	//运单实时变动的参数
	private  static String b_zxppcode;//运单号
	private  static String b_zxpsenddate;//运单日期
	private  static String b_zxptime2;//最晚送货时间
	private  static JSONObject jsonAuto = new JSONObject(); //运单中变动的字段（运单号，日期，最晚时间）
	//构造方法
	public AutoOrderByTime(){
		b_zxppcode="b_zxppcode";
		b_zxpsenddate="b_zxpsenddate";
		b_zxptime2="b_zxptime2";
	}
	
	//获取当前日期，生成运单号、运单日期和送货时间
	public void timeAuto(int minute){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Calendar cal = Calendar.getInstance();
		String times = "";
		String orders = "";
		String zxptime2="";
		boolean flag = true;
		while (flag == true){
		//	temps = format.format(cal.getTime()).substring(0,10);
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
			
			flag = false;
		}
		
		//最晚送货时间(向后推一天再加5小时)
		Calendar calz = Calendar.getInstance();
		calz.add(Calendar.DAY_OF_MONTH,1);//当前日期加1天
		calz.add(Calendar.HOUR_OF_DAY,5);//时间加5小时
		calz.add(Calendar.MINUTE,minute);//时间加minute分钟
		zxptime2=format.format(calz.getTime()).substring(0,19);//最晚送货时间
		JSONObject jsondd = new JSONObject(); //运单日期
		JSONObject jsonddt = new JSONObject();//最晚送货时间
		JSONObject jsoncod=new JSONObject();//运单号
		jsondd.put(b_zxpsenddate, times);
		jsonddt.put(b_zxptime2, zxptime2);
		jsoncod.put(b_zxppcode, orders);
		jsonAuto.putAll(jsoncod);
		jsonAuto.putAll(jsondd);
		jsonAuto.putAll(jsonddt);
	}
	
	/**
     * 读取xml文件
     * @param fileName
     */ 
    public String parserXml(String fileName,Boolean flag)  
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
	              JSONObject obj = JSONObject.fromObject(temp );
	              obj.putAll(jsonAuto);
	              if(flag){
	          		JSONObject obj1=new JSONObject();
	        		String time_stamp=String.valueOf(System.currentTimeMillis()).substring(3,13);
	        		obj1.put("PASSENGER_NAME","刘小平a");
	        		obj1.put("PASSENGER_NUMBER","0025");
	        		obj1.put("STATU_CHECK","0");
	        		obj1.put("TIME_CHECK",time_stamp);
	        		obj1.put("PASSENGER_NUMBER_ENCRYPT","幢背贝案繁鞍0025");
	        		String str="["+obj1.toString()+"]";
	        		obj.put("b_zxpstring2", str);
	              }
	              if(count>0){
	            	  data +=",";		              
	              }
	              data += obj.toString();
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
