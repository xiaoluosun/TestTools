package pub.test;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class PacketsThread implements Runnable {
	
	private ArrayList<String> paths;
	private int count;
	private static final int PORT = 2903;
	private static final String host = "192.168.2.41";
	
	public PacketsThread(ArrayList<String> paths){
		count = 0;
		this.paths = paths;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		String path = this.paths.get(count++);
//		System.out.println(path);
		
		String fileName = path.split("\\\\")[path.split("\\\\").length -1];		
		String imei = fileName.split("\\.")[0];
		
		CvsOper cvsOper = new CvsOper(path);
		ArrayList<GpsData> gpsDatas = cvsOper.getGpsDatas();
		
		System.out.println(gpsDatas.size());
		  
		  //客户端的实现  
		  NioSocketConnector connector = new NioSocketConnector();
		  System.out.println("333");
		  connector.getFilterChain().addLast("codec",  
				  new ProtocolCodecFilter(new PEncoder(), new PDecode()));
		  connector.getFilterChain().addLast("logging", new LoggingFilter());
		  FileUploadClientHandler h = new FileUploadClientHandler();
		  connector.setHandler(h);  
		  //本句需要加上，否则无法调用下面的readFuture来从session中读取到服务端返回的信息。  
		  connector.getSessionConfig().setUseReadOperation(true);  
	  
		  ConnectFuture cf = connector.connect(new InetSocketAddress(host,  
				  PORT));
		  //等待连接成功  
		  cf.awaitUninterruptibly();  
		  IoSession  session = cf.getSession();
			  
		  System.out.println("client send begin");

		  int i = 0;
		  // TODO Auto-generated method stub  
		  
		  while(i < gpsDatas.size()){
			  Date d = new Date();
			  SimpleDateFormat sdf = new SimpleDateFormat ("yyyMMddHHmmss") ;
			  GpsData gpsData = gpsDatas.get(i);
			  String loc = "~2001&GPSDU&2|"+imei+"|";
			  loc=loc + sdf.format(d)
					  +"|"+gpsData.getLongitude()
					  +"|"+gpsData.getLatitude()
					  +"|"+gpsData.getSpeed()
					  +"|"+gpsData.getDirection()
					  +"|{acc:1,gps:1,t1:23,t2:24,t3:25,t4:26,humi:86,door:1}#";
			
			  try {
				  Thread.sleep(10000);
			  } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				  e.printStackTrace();
			  }
			  if(session.isConnected()){
				  System.out.println(loc);
				  session.write(loc);
			  }else{
				  System.out.println("没有连上");
			  }
			  System.out.println(i++);
			  
			}
		  
			session.close(true);
			connector.dispose(true);
			
			cvsOper.writResrotData(path);
			
	}
	
}
