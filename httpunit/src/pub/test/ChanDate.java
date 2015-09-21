package pub.test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ChanDate {	
	
	public static String caculateTime(String key){
		String temps = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Calendar cal = Calendar.getInstance();
		if(key.equals("today")){
			temps = format.format(cal.getTime()).substring(0, 10);
		}
		else if(key.equals("yestoday")){
			cal.add(Calendar.DATE, -1);
			temps = format.format(cal.getTime()).substring(0, 10);
		}
		else if(key.equals("premonhead")){
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.MONTH, -1);
			temps = format.format(cal.getTime()).substring(0, 10);
		}
		else if(key.equals("premontail")){
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.DATE, -1);
			temps = format.format(cal.getTime()).substring(0, 10);
		}
		else if(key.equals("thismonhead")){
			cal.set(Calendar.DATE, 1);
			temps = format.format(cal.getTime()).substring(0, 10);
		}
		else 
		{					
			cal.add(Calendar.DATE, 1);
			cal.set(Calendar.HOUR_OF_DAY, 00);
			cal.set(Calendar.MINUTE, 00);
			cal.set(Calendar.SECOND, 00);
			Date now = cal.getTime();
			String nows = format.format(now);
			Date date = null;
			double temp =0.0;
			try {
				date = format.parse(nows);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long l = date.getTime();
		  	ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
		  	
			String strs = key.substring(0,key.length()-2);
			try {
				temp = (Double) jse.eval(strs);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long ll = new Double(temp).longValue(); 
			Date dt = new Date(l+(ll*1000));
			String dates = format.format(dt);
			String str = key.substring(key.length()-2);
			if (str.equals("mm")){
				temps = dates.substring(0, 16);
			} else if (str.equals("ss")){
				temps = dates.substring(0, 19);
			} else if (str.equals("dd")){
				temps = dates.substring(0, 10);
			}
		}
		
//		System.out.println(temps);
		return temps;
	}
}
