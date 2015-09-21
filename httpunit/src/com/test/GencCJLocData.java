package com.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pub.test.GenCommon;
import pub.test.SqlInfo;

public class GencCJLocData {
	private Map<String,String>   shippers;	// 调度单信息
	private Map<String,String>   wayBills;	// 运单信息
	private SqlInfo	sqlInfo;				// sql连接串
	
	public GencCJLocData(){
		sqlInfo = new SqlInfo();
		shippers = new HashMap<String,String>();
		// 主键ID
		shippers.put("id", GenCommon.GUID());
		// 同步中间库id，需要手动更改
		String sysc_id = "4527";
		String shipment_no = "141204001";
		shippers.put("sync_id", sysc_id);
		// 调度单号，需要手动更改
		shippers.put("shipment_no", shipment_no);
		// 车牌号，可能会手动更改
		shippers.put("gps_car_no", "京A00000");
		// 车牌号，可能会手动更改
		shippers.put("license", "京A00000");
		// 同步业务中心代码
		shippers.put("bus_code", "23");
		// 同步业务中心名称
		shippers.put("bus_name", "长久物流郑州业务中心");
		// 承运商编码
		shippers.put("carr_code", "100304");
		// 承运商名称
		shippers.put("carr_name", "天津长宏运输有限公司");		
		// 司机名称
		shippers.put("driver_name", "刘辉");
		// 司机电话
		shippers.put("driver_telphone", "123456");		
		// 调度单状态(ONWAY|FINISH)
		shippers.put("shipment_status", "ONWAY");
		// 状态更新时间
		String now = getCurTime("yyyy-MM-dd HH:mm:ss");
		shippers.put("s_update_time", now);
		// 调度单下单时间
		shippers.put("bill_date", now);
		// 调度单计划出发时间
		shippers.put("plan_leave_time", now);
		// 调度单发车地点
		shippers.put("from_loc_name", "轨迹起点");
		shippers.put("out_fence_name", "轨迹起点");
		// 调度单预计到达时间
		String toTime = getPlanTime(now, 30);
		shippers.put("plan_arrive_time", toTime);
		// 调度单到达地点
		shippers.put("to_loc_name", "轨迹终点");
		shippers.put("in_fence_name", "轨迹终点");
		// 创建时间
		shippers.put("create_time", now);
		// 修改时间
		shippers.put("update_time", now);
		// 同步状态
		shippers.put("sync_status", "0");
		
		
		wayBills = new HashMap<String,String>();
		// 主键id
		wayBills.put("id", GenCommon.GUID());
		// 同步统计库运单表id
		wayBills.put("sync_id", sysc_id);
		// 调度单号
		wayBills.put("shipment_no", shipment_no);
		// 运单号
		wayBills.put("pod_no", shipment_no + "-" + getCurTime("yyMMddHHmmssSSS"));
		// 创建时间
		wayBills.put("create_time", now);
		// 修改时间
		wayBills.put("update_time", now);
		// 运单状态
		wayBills.put("pod_status", "OPEN");
		// 运单状态更新时间
		wayBills.put("supdate_time", now);
		// 运单发车地
		wayBills.put("from_loc_name", "轨迹起点");
		// 运单到达地
		wayBills.put("to_loc_name", "轨迹终点");
		// 运单到达地
		wayBills.put("in_fence_name", "轨迹终点");
		// 运单计划到达时间
		String wayBilltoTime = getPlanTime(now, 25);
		wayBills.put("plan_arrive_time", wayBilltoTime);
		// 委托方
		wayBills.put("consignor_nm", "芜湖奇瑞汽车物流有限公司");
	}
	
	
	/**
	 * 获取当前时间
	 * @param String format 要转化的时间格式，例如"yyyy-MM-dd HH:mm:ss", "yyMMddHHmmssSSS"
	 * 
	 * 
	 */
	public String getCurTime(String format){
		Date date =  new Date();
		SimpleDateFormat now = new SimpleDateFormat(format);
		
		return now.format(date);
	}
	
	/**
	 * 获取计划到达时间
	 * @param String now,某个时间
	 * @param int interval,计划耗时,单位是分钟
	 * 
	 * @return String 计划到达时间
	 * 
	 */
	public  String getPlanTime(String now, int interval){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String toTime = "";
		try {
		    Date myDate = formatter.parse(now);
		    Calendar c = Calendar.getInstance();
		    c.setTime(myDate);
		    c.add(Calendar.MINUTE, interval);
		    myDate = c.getTime();
		    toTime = formatter.format(myDate);
		} catch (ParseException e1) {
		    e1.printStackTrace();
		}
		
		return toTime;
	}
	
	/**
	 * 
	 * 读取sql配置文件
	 * @param flag 配置文件路径标志
	 * 
	 * 
	 */
	
	public void InitSqlInfo(int flag){
		String path ="" ;
		switch(flag){
			case 1:
				path = "\\data\\sqlInfo\\sqlInfo_foton_100.xml";
				break;
			default:
				break;
		}
		
		sqlInfo.ReadSqlInfo(path);
	}
	
	/**
	 * 
	 * 获取调度单表信息 
	 * 
	 * 
	 */
	private Map<String,String> getShippers(){
		return shippers;
	}
	
	/**
	 * 
	 * 获取运单表信息 
	 * 
	 * 
	 */
	private Map<String,String> getWaybills(){
		return wayBills;
	}
	
	/**
	 * 
	 * 插入调度单表和运单表
	 * 
	 * 
	 * 
	 */
	public void fillData(){
		
		Map<String,String> shippers = getShippers();
		Map<String,String> waybills = getWaybills();
		
		try
		{		  
		  // 加载驱动程序
		  Class.forName(sqlInfo.getSqlDriver());
		  // 连续数据库
		  java.sql.Connection conn = DriverManager.getConnection(sqlInfo.getUrl(), sqlInfo.getUser(), sqlInfo.getPwd());
		
		  if(!conn.isClosed());
		  Statement st = conn.createStatement();
		  // 要执行的SQL语句
		  String ss="insert into changjiu_shipper ("
		  		+ "id, "
		  		+ "sync_id,"
		  		+ "bus_code,"
		  		+ "bus_name,"
		  		+ "carr_code,"
		  		+ "carr_name,"
		  		+ "gps_car_no,"
		  		+ "license,"
		  		+ "driver_name,"
		  		+ "driver_telphone,"
		  		+ "shipment_no,"
		  		+ "shipment_status,"
		  		+ "s_update_time,"
		  		+ "bill_date,"
		  		+ "plan_leave_time,"
		  		+ "from_loc_name,"
		  		+ "plan_arrive_time,"
		  		+ "to_loc_name,"
		  		+ "create_time,"
		  		+ "update_time,"
		  		+ "out_fence_name,"
		  		+ "in_fence_name,"
		  		+ "sync_status)"
		  		+ "values(\'" 
		  		+shippers.get("id")+"\',\'"
		  		+shippers.get("sync_id")+"\',\'"
		  		+shippers.get("bus_code")+"\',\'"
		  		+shippers.get("bus_name")+"\',\'"
		  		+shippers.get("carr_code")+"\',\'"
		  		+shippers.get("carr_name")+"\',\'"
		  		+shippers.get("gps_car_no")+"\',\'"
		  		+shippers.get("license")+"\',\'"
		  		+shippers.get("driver_name")+"\',\'"
		  		+shippers.get("driver_telphone")+"\',\'"
		  		+shippers.get("shipment_no")+"\',\'"
		  		+shippers.get("shipment_status")+"\',\'"
		  		+shippers.get("s_update_time")+"\',\'"
		  		+shippers.get("bill_date")+"\',\'"
		  		+shippers.get("plan_leave_time")+"\',\'"
		  		+shippers.get("from_loc_name")+"\',\'"
		  		+shippers.get("plan_arrive_time")+"\',\'"
		  		+shippers.get("to_loc_name")+"\',\'"
		  		+shippers.get("create_time")+"\',\'"
		  		+shippers.get("update_time")+"\',\'"
		  		+shippers.get("out_fence_name")+"\',\'"
		  		+shippers.get("in_fence_name")+"\',\'"
		  		+shippers.get("sync_status")+"\')";
		  st.executeUpdate(ss);
//		  st.close();
		  
		  ss="insert into changjiu_waybill ("
			  		+ "id, "
			  		+ "sync_id,"
			  		+ "shipment_no,"
			  		+ "pod_no,"
			  		+ "create_time,"
			  		+ "update_time,"
			  		+ "pod_status,"
			  		+ "supdate_time,"
			  		+ "from_loc_name,"
			  		+ "to_loc_name,"
			  		+ "in_fence_name,"
			  		+ "plan_arrive_time,"
			  		+ "consignor_nm)"
			  		+ "values(\'" 
			  		+waybills.get("id")+"\',\'"
			  		+waybills.get("sync_id")+"\',\'"
			  		+waybills.get("shipment_no")+"\',\'"
			  		+waybills.get("pod_no")+"\',\'"
			  		+waybills.get("create_time")+"\',\'"
			  		+waybills.get("update_time")+"\',\'"
			  		+waybills.get("pod_status")+"\',\'"
			  		+waybills.get("supdate_time")+"\',\'"
			  		+waybills.get("from_loc_name")+"\',\'"
			  		+waybills.get("to_loc_name")+"\',\'"
			  		+waybills.get("in_fence_name")+"\',\'"
			  		+waybills.get("plan_arrive_time")+"\',\'"
			  		+waybills.get("consignor_nm")+"\')";
			  st.executeUpdate(ss);
			  st.close();
		  conn.close();
		}
		 catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) {
		
		GencCJLocData cj = new GencCJLocData();
		cj.InitSqlInfo(1);
		cj.fillData();
		
	}
}
