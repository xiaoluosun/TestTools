package test_temperature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.testng.annotations.Test;

public class PostData {
	private StringBuffer result=new StringBuffer();
	private String line;
	private BufferedReader in=null;
	private PrintWriter print=null;
	

	  public  String post(String url,String param){
		  
		try {
			URL ur=new URL(url);
			URLConnection conn=ur.openConnection();
			conn.setRequestProperty("accept", "*/*");
			//conn.setRequestProperty("connection","keep-Alive");
			conn.setRequestProperty("User-Agent","	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");
		

			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			print=new PrintWriter(conn.getOutputStream());
			print.print(param);
			print.flush();
			in = new BufferedReader(  
		                new InputStreamReader(conn.getInputStream()));  
			while((line=in.readLine())!=null){
				result.append(line);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(print!=null){
				print.close();
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	  }

	  @Test
	  public void f() {
		  String str_get=post("http://sqrz55.ips.cn/service.php","method=ips2.postevent.newtruckstatus");
//		   System.out.println(str_get);
		  String  ss=post("http://sqrz55.ips.cn/service.php","method=ips2.postevent.checktemp");
//          System.out.println(ss);
	  }
}
