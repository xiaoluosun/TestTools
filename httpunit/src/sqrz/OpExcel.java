package sqrz;

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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OpExcel  {
	private static Connection conn;
	private static Statement st;

	public void connMysql(){	//执行查询
		System.out.println("生成运单模板中，请稍等。。。\n");
		conn = getConnection(); // 首先要获取连接，即连接到数据库		
		try {  
            String sql = "SELECT secondprojectno FROM ips_smallproject_info";  //查询的sql语句               
            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量              
            ResultSet rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集              
            while (rs.next()){
            	upExcel(rs.getString("secondprojectno"));
            }           
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
                    "jdbc:mysql://192.168.2.53:3306/ips_sqrz", "tester", "tester890__");// 创建数据连接  
              
        } catch (Exception e) {  
            System.out.println("数据库连接失败" + e.getMessage());  
        }  
        return con; //返回所建立的数据库连接  
    } 
	public static void upExcel(String secondprojectno){	
		try {      
			String path = System.getProperty("user.dir");
			String filename = path+"\\data\\sqrz\\运单导入模板.xlsx"; 
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filename));
			XSSFSheet sheet = workbook.getSheetAt(0);
			sheet.shiftRows(2, 12, -1);		//移除上次生成的运单数据
			
            int rowNum = sheet.getLastRowNum();  	//定位含有内容的行
                   
            if (rowNum <= 10){ 
            	XSSFRow row = sheet.createRow(rowNum+1);            	
		        XSSFCell cell = row.createCell(0); 
		    	cell.setCellValue(secondprojectno);
		    	
		    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHMMSS");
				Date da = new Date();
				String time = format.format(da);
		    	cell = row.createCell(1);
		    	cell.setCellValue(time);
            }
            FileOutputStream out = new FileOutputStream(filename);
            workbook.write(out);            
            out.close();
		} catch (IOException e) {
			e.printStackTrace();			
		}
	}
}
