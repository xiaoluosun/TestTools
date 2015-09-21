package cre.bigclient_order;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UpdateExcel  {	
	private static Connection conn;
	private static Statement st;

	public void connMysql(){	//执行查询
		System.out.println("生成运单模板中，请稍等。。。\n");
		conn = getConnection(); // 首先要获取连接，即连接到数据库		
		try {  
            String sql = "SELECT s.code FROM ips_store_set AS s JOIN ips_bigclient_config AS b"
            		+ " ON s.customercode = b.code"
            		+ " WHERE b.name = '测试大客户101'";   //查询的sql语句           
            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量              
            ResultSet rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集             
            
            String code1 = null;
            String code2 = null;
            if (rs.first())		//得到第一行的大客户code
            	code1 = rs.getString("code");
            if (rs.last())		//得到最后一行的大客户code
            	code2 = rs.getString("code");           
            upExcel(code1,code2);
            
            conn.close();   //关闭数据库连接              
        } catch (SQLException e) {  
            System.out.println("插入数据失败" + e.getMessage());  
        }  
	}	
	public static Connection getConnection() {  	//创建数据库连接	
        Connection con = null;  //创建用于连接数据库的Connection对象  
        try {  
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动               
            con = DriverManager.getConnection(  
                    "jdbc:mysql://192.168.2.53:3306/ips_cre", "tester", "tester890__");// 创建数据连接  
              
        } catch (Exception e) {  
            System.out.println("数据库连接失败" + e.getMessage());  
        }  
        return con; //返回所建立的数据库连接  
    } 
	
	public void upExcel(String code1, String code2){
		try {      
			String path = System.getProperty("user.dir");
			String filename = path+"\\data\\cre_hailan\\大客户运单导入模板.xlsx"; 
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filename));
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			XSSFCell cell = sheet.getRow(2).getCell(6);		//定位编码后半部分			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHMMSS");
			Date da = new Date();
			String time = format.format(da);
	    	cell.setCellValue(time);		//生成的单号赋给编码后半部分
	    	
	    	XSSFCell cell1 = sheet.getRow(6).getCell(1);		//定位调出门店代码并赋值
	    	cell1.setCellValue(code1);
	    	
	    	XSSFCell cell2 = sheet.getRow(6).getCell(6);		//定位调入门店代码并赋值
	    	cell2.setCellValue(code2);
	    	
            FileOutputStream out = new FileOutputStream(filename);
            workbook.write(out);            
            out.close();
		} catch (IOException e) {
			e.printStackTrace();			
		}
	}	
}