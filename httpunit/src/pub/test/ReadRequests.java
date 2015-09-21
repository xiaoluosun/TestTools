package pub.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadRequests {
	/*
     * 读取登录xml文件的请求信息
     * @param fileName
     */ 
    public static void readReqXml(String fileName, Map<String, String> reqs)  
    {  	
    	Map<String, List> requests = new HashMap<String, List>();
    	RunRegData rda = new RunRegData();
    	String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
    	Element eReqs = null;
        SAXReader saxReader = new SAXReader();    
        String modTexts = null;
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element employees=document.getRootElement();   	//获取文档根节点         
         
            for(Iterator<?> i = employees.elementIterator(); i.hasNext();){
                Element employe = (Element) i.next();     
            	
	            for(Iterator<?> x = employe.elementIterator();x.hasNext();){	
	                Element employee = (Element) x.next(); 
	                if(employee.getName()=="model"){
	                	modTexts = employee.getText();	                
	                	List<Element> reqlists = employee.elements();
	                	Element req = null;
	                	if(reqlists.size() == 2){
	                		req = (Element) reqlists.get(1);	                		
	                	} else if(reqlists.size() == 1) {
	                		eReqs = (Element) reqlists.get(0);	                		
	                	}
	                	ArrayList al = new ArrayList();
	                	ArrayList als = new ArrayList();
	                	ArrayList po = new ArrayList();
	                	ArrayList pos = new ArrayList();
	                	try{	                		
	                		if(eReqs.getName() == "request"){	                		
		            			List<Element> elist = eReqs.elements();
		            			for(int k = 0,h = 0; k <elist.size(); k++){		            				
		            				Element list = (Element)elist.get(k);
		            			    if(list.getName() != "post"){
		            			    	al.add(list.getName()+"="+list.getText());
		            			    }
		            			}
			                	List<Element> enodes = eReqs.elements();
				               	for(int j = 0; j<enodes.size(); j++){
				            		Element enode = (Element)enodes.get(j);
				            		if(enode.getName() == "post"){
				            			List<Element> paramLi = enode.elements();
				            			for(int k = 0; k <paramLi.size(); k++){
				            				Element param = (Element)paramLi.get(k);		            				
				            				po.add(param.getName()+"="+param.getText());		            				
				            			}				            			
				            		}			            		
				               	}				               	
		                	}
		                	if(req.getName() == "request"){
		                		List<Element> elists = req.elements();
		                		for(int h = 0,k = 0; h < elists.size(); h++){
		                			Element lists = (Element)elists.get(h);	
		            				if(lists.getName() != "post"){
		            					als.add(lists.getName()+"="+lists.getText());
		            				} 	
		                		}
			                	List<Element> nodes = req.elements();
				               	for(int j = 0; j<nodes.size(); j++){
				            		Element node = (Element)nodes.get(j);
				            		if(node.getName() == "post"){
				            			List<Element> paramsLi = node.elements();
				            			for(int k = 0; k <paramsLi.size(); k++){
				            				Element params = (Element)paramsLi.get(k);		            				
				            				pos.add(params.getName()+"="+params.getText());		            				
				            			}
				            		}		                	
				               	}					               	
		                	}		                	
	                	} catch (NullPointerException n){	                		
	                	}  
	                	Map<String, List> request = new HashMap<String, List>();
	                	al.addAll(po);
	                    al.addAll(als);   
	                    al.addAll(pos);	 
	                    request.put(modTexts.trim(), al);                	
	                   	for(int y = 0; y < request.size(); y++){
	                   		requests.putAll(request);	                   		
	                   	}
	                }                 	
	            }                       
            }   
	     }catch (DocumentException e) {    		
            System.out.println(e.getMessage());           
        }   
        rda.setData(requests); 
    }  
} 

