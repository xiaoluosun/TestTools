package pub.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.csvreader.CsvWriter;


public class CvsOper {
	
	private ArrayList<GpsData> gpsDatas;	// gps数据
	private int counter;					// 计数器
	public CvsOper(String fileName){		 
		gpsDatas = CvsOper.readCvs(fileName);
	}
	
	public int  getCounter(){
		return this.counter;
	}
	
	public ArrayList<GpsData> getGpsDatas(){
		return this.gpsDatas;
	}	
	
	
	/**
	 * 读取cvs文件，返回对象数组
	 * 
	 *
	 */
	public static ArrayList<GpsData>  readCvs(String path){
		
		ArrayList<GpsData> gpsDatas = new ArrayList<GpsData>();
		File csv = new File(path); // CSV文件
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(csv),"gbk"));
			// 读取直到最后一行
			String line = "";
			while ((line = br.readLine()) != null) {
				
				GpsData gpsData = new GpsData();
				// 把一行数据分割成多个字段
				StringTokenizer st = new StringTokenizer(line, ",");
				
				int column = 0;

				while (st.hasMoreTokens()) {
				// 每一行的多个字段用TAB隔开表示
					String temp = st.nextToken();		
					if(!String2Double(temp)){
						break;
					}
					column++;
					if(column == 1){
						gpsData.setLatitude(temp);
					}
					else if(column == 2){
						gpsData.setLongitude(temp);
					}
					else if(column == 3){
						gpsData.setSpeed(temp);
					}
					else if(column == 4){
						gpsData.setDirection(temp);
					}
					else{
						System.out.println("这怎么可能呢？");
					}					
				}
				
				if(!gpsData.getLatitude().equals("")){
					gpsDatas.add(gpsData);
				}
			}
				br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// 捕获BufferedReader对象关闭时的异常
			e.printStackTrace(); 
		} catch(NumberFormatException e){
			
			e.printStackTrace();
		}
		return gpsDatas;
	}
	
	/**
	 * 
	 * 判断字符串是否可以转换成double类型的变量
	 * 
	 */
	
	private static Boolean String2Double(String str){
		Boolean flag = true;
		try{
			Double.valueOf(str);
		}
		catch(NumberFormatException e){
			flag =  false;
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 把gps数据逆序写入CSV文件
	 * 
	 */
	
	public void writResrotData(String fileName){    	
		try {			
			
			CsvWriter wr =new CsvWriter(fileName,',',Charset.forName("GBK"));
			
			wr.write("纬度");
			wr.write("经度");
			wr.write("速度");
			wr.write("方向");
			wr.endRecord();
			for(int i = gpsDatas.size()-1; i>=0; i--){
				GpsData gpsdat = gpsDatas.get(i);
				wr.write(gpsdat.getLatitude());
				wr.write(gpsdat.getLongitude());
				wr.write(gpsdat.getSpeed());
				wr.write(gpsdat.getDirection());
				wr.endRecord();
			}
			
			wr.flush();
			wr.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

}
