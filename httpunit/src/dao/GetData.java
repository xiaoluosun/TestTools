package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.GetConn;

public class GetData {
	
	//参数
	private Connection conn=null;
	private Statement stms=null;
	private ResultSet  rs=null;
	private List<String> list=new ArrayList<String>();
	private SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	//查询温控探头数据为空的数据
	public List<String> query(Date date1,Date date2) {
//		String date1_str="2014-04-01 15:50:00"; //测试
//		String date2_str="2014-04-01 16:10:00"; //测试
		String date1_str=sd1.format(date1);
		System.out.println("起始时间："+date1_str);
		String date2_str=sd1.format(date2);
		System.out.println("截止时间："+date2_str);
		//调用方法获得连接
		try {
			conn=GetConn.getCon();
			stms=conn.createStatement();
     		String sql="select carnum,t1,t2,t3,t4,time,speed from ips_temperature_truck where speed not like '%离线%' and createtime>'"+date1_str+"'and createtime<'"+date2_str+"'and (t1 = '' or t2 = '' or t3 = ''or t4 ='') order by createtime desc";
//		    System.out.println(sql);
			String sql1="select carnum,t1,t2,t3,t4,time,speed from ips_temperature_truck where speed like'%离线%'  and createtime>'"+date1_str+"'and createtime<'"+date2_str+"'";
			//System.out.println(sql);
			rs=stms.executeQuery(sql);
			list.add("<table border='3'>");
			list.add("<tr><th colspan='7'>狮桥物流温控异常数据(t1,t2,t3,t4中至少一个无温控数据)汇总</th></tr>");
			list.add("<tr><th colspan='7'>查询的是("+date1_str+"~"+date2_str+")时间段的数据</th></tr>");
			list.add("<tr><th>车牌号</th><th>探头T1（℃）</th><th>探头T2（℃）</th><th>探头T3（℃）</th><th>探头T4（℃）</th><th>GPS时间</th><th>状态</th></tr>");
			while(rs.next()){
		    	list.add("<tr><th>"+rs.getString(1)+"</th><th>"+rs.getString(2)+"</th><th>"+rs.getString(3)+"</th><th>"+rs.getString(4)+"</th><th>"+rs.getString(5)+"</th><th>"+rs.getString(6)+"</th><th>"+rs.getString(7)+"</th></tr>");
		    }
		    if(list.size()<=4){
		    	list.add("<tr><th colspan='7'>未找到异常温控数据,请关注下次查询结果</th></tr>");
		    }

		    //System.out.println("list长度为："+list.size());
		    list.add("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			GetConn.closeConn();//关闭连接 
		}

		return list;
	}
	
	//查询温控探头t1-t2,t2-t3任意两者大于5的数据
	public List<String> queryAlertNum(Date date1,Date date2) {
		String date1_str=sd1.format(date1);
		System.out.println("起始时间："+date1_str);
		String date2_str=sd1.format(date2);
		System.out.println("截止时间："+date2_str);		
		//String date1_str="2014-08-07 10:07:38"; //测试
	    //String date2_str="2014-08-08 16:10:00";//测试
	    List<String> list_alert =new ArrayList<String>();
		try {
			conn=GetConn.getCon();
			stms=conn.createStatement();
			String sql="SELECT carnum,t1,t2,t3,t4,time,speed,round(abs(t1-t2),1)as t1与t2温差,round(abs(t2-t3),1) as t2与t3温差  FROM ips_temperature_truck where  createtime>'"
			           +date1_str+"'and createtime<'"+date2_str+"'and speed not like '%离线%' and (t1-t2>5 or t1-t2<-5 or t2-t3>5 or  t2-t3<-5)";
            //System.out.println(sql);
            rs=stms.executeQuery(sql);
            list_alert.add("<table border='3'>");
            list_alert.add("<tr><th colspan='9'>狮桥物流温控报警数据(t1-t2,t2-t3温差大于5度--表格红色字体)汇总</th></tr>");
            list_alert.add("<tr><th colspan='9'>查询的是("+date1_str+"~"+date2_str+")时间段的数据</th></tr>");
            list_alert.add("<tr><th>车牌号</th><th>探头T1（℃）</th><th>探头T2（℃）</th><th>探头T3（℃）</th><th>探头T4（℃）</th><th>GPS时间</th><th>状态</th><th>t1与t2温差</th><th>t2与t3温差</th></tr>");
            while(rs.next()){
            	String num1Color="";
            	String num2Color="";
            	String alertNum1=rs.getString(8);
            	String alertNum2=rs.getString(9);
            if(Double.parseDouble(alertNum1)>5){
             	num1Color="<font color='red'>";
            }	
            if(Double.parseDouble(alertNum2)>5){
            	num2Color="<font color='red'>";
            }
//            	System.out.println(rs.getString(1)+" \t"+rs.getString(2)+"\t"+"\t"+rs.getString(3)+"\t"+"\t"+rs.getString(4)+"\t"+"\t"+rs.getString(5)+"\t"+"\t"+rs.getString(6)+"\t"+"\t"+rs.getString(7)+"\t"
//            			+"\t"+rs.getString(8)+"\t"+"\t"+rs.getString(9)+"\t"+"\t"+rs.getString(10)+"\t");
            list_alert.add("<tr><th>"+rs.getString(1)+"</th><th>"+rs.getString(2)+"</th><th>"+rs.getString(3)+"</th><th>"+rs.getString(4)+"</th><th>"+rs.getString(5)+"</th><th>"+rs.getString(6)+"</th><th>"+rs.getString(7)+"</th><th>"+num1Color+rs.getString(8)+"</th>"
            		+ "<th>"+num2Color+rs.getString(9)+"</th></tr>");
            }
           // System.out.println(list_alert.size());
		    if(list_alert.size()<=4){
		    	list_alert.add("<tr><th colspan='9'>未找到温控报警数据,请关注下次查询结果</th></tr>");
		    }
		   // System.out.println("list_alert长度为："+list_alert.size());
            list_alert.add("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list_alert;
	}
	
//	public static void main(String[] agrs){
//		new GetData().queryAlertNum();
//	}
}
