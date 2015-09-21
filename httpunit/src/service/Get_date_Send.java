package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import util.SendMail;
import dao.GetData;

public class Get_date_Send {
	List<String> li = new ArrayList<String>();
	List<String> lis = new ArrayList<String>();
	List<String> lis_alert=new ArrayList<String>();
	Calendar cal = Calendar.getInstance();

	// 获得数据
	public void get_data(Calendar cal) {
		long time_before = 0;
		long time_after = 0;
		if(cal.get(Calendar.HOUR_OF_DAY)==9){
			time_before = (long) (cal.getTimeInMillis() - 16*60 * 60 * 1000);
			time_after = (long) (cal.getTimeInMillis() + 1 * 60 * 1000);
		}
		else{
			time_before = (long) (cal.getTimeInMillis() - 2*60 * 60 * 1000);
			time_after = (long) (cal.getTimeInMillis() + 1 * 60 * 1000);
		}
		cal.setTimeInMillis(time_before);
		Date date_before = cal.getTime();
		cal.setTimeInMillis(time_after);
		Date date_after = cal.getTime();
		GetData data = new GetData();
		// 调用方法，获得数据
		lis = data.query(date_before, date_after);
		lis_alert=data.queryAlertNum(date_before, date_after);
		
	}

	// 送邮件
	public void send_data() {
		SendMail send = new SendMail();
		send.setHost("smtp.263.net");
		send.setUserName("xxx");
		send.setPassWord("xxx");
		//send.setTo("xxx,xxx");//正式时注释本行，开启下行
		send.setTo("");
		send.setFrom("");
		send.setSubject("狮桥物流温控异常数据汇总");
		li.add("<br><br><br>");
		 li.addAll(lis);
		 li.add("<br><br><br><br>");
		 li.addAll(lis_alert);
		// System.out.println("li长度为："+li.size());
		StringBuffer stb = new StringBuffer();
		for (int i = 0; i < li.size(); i++) {
			stb.append(li.get(i));
		}
		send.setContent(new String(stb));
		System.out.println("发送成功返回true,发送失败返回false：" + send.sendMail());
	}

	@Test
	public void f() {
		//get_temperature();
		Calendar cal = Calendar.getInstance();
		get_data(cal);
		if(lis.size()>6||lis_alert.size()>6){
			System.out.println("查询到了异常数据，邮件已发送！");
			send_data();
		}
		else{
			System.out.println("没有查询到异常数据，故不发送邮件！");
		}
	}
}
