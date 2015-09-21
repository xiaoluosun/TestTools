package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GetConn {
    //参数
	private static Connection conn=null;
	private static Statement stms=null;
	//获得连接
	public static Connection getCon(){
		
		//解析Parse_xml
		Parse_xml parse=new Parse_xml();
		String pathProSys=System.getProperty("user.dir");
		String pathProUser="\\data\\sqrz\\db_param.xml";
		String path=pathProSys+pathProUser;
		parse.readXml(path);
		try {
			Class.forName(parse.driverName);
			conn=DriverManager.getConnection(parse.url, parse.user, parse.password);
			stms=conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭连接
	public static  void closeConn(){
		if(stms!=null){
			try {
				stms.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
