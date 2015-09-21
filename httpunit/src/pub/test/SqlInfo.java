package pub.test;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SqlInfo {
	private String sqlDriver;
	private String url;
	private String user;
	private String pwd;
	
	public SqlInfo(){
		sqlDriver = "";
		url = "";
		user = "";
		pwd = "";
	}
	
	public void ReadSqlInfo(String fileName){
		String path = System.getProperty("user.dir");
    	path +=fileName;
    	File inputXml=new File(path);
        SAXReader saxReader = new SAXReader();    
        try {    
            Document document = saxReader.read(inputXml);   //把文件读入到文档 
            Element root=document.getRootElement();   	//获取文档根节点 
            
            Element employees  = (Element)root.elements().get(0);
            
            for(Iterator<?> i = employees.elementIterator(); i.hasNext();){
            	
                Element employee = (Element) i.next();   
//                System.out.println(employee.getName());
                if(employee.getName().equals("mysqlDriver")){
                	sqlDriver = employee.getText();
                }
                else if(employee.getName().equals("url")){
                	url = employee.getText();
                }
                else if(employee.getName().equals("mysqlUser")){
                	user = employee.getText();
                }
                else if(employee.getName().equals("mysqlPassword")){
                	pwd = employee.getText();
                }
                else{
                	System.out.println("sql 配置文件错误");
                }
            }
            
           
        } catch (DocumentException e) {    
            System.out.println(e.getMessage());    
        } 


	}

	public String getSqlDriver() {
		return sqlDriver;
	}

	public void setSqlDriver(String sqlDriver) {
		this.sqlDriver = sqlDriver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public static void main(String[] args){
		String pathName = "\\data\\sqlInfo\\sqlInfo_standard_55.xml";
		
		SqlInfo sqlInfo = new SqlInfo();
		
		sqlInfo.ReadSqlInfo(pathName);
		System.out.println(sqlInfo.getSqlDriver());
		System.out.println(sqlInfo.getUrl());
		System.out.println(sqlInfo.getUser());
		System.out.println(sqlInfo.getPwd());
	}
	
}
