package pub.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 
 * 定义 FileUploadClientHandler来处理消息接收事件
 * 
 */
public class FileUploadClientHandler extends IoHandlerAdapter  {
	
	/**
	 *
	 * 当会话开始时被触发
	 *
	 */
	@Override 
	public void sessionOpened(IoSession session) throws Exception {  
		System.out.println("client open");  
	}  
	 
	/**
	 * 
	 * 当会话关闭时被触发 
	 * 
	 * 
	 */
	@Override 
	public void sessionClosed(IoSession session) throws Exception {  
		System.out.println("client session close");  
	}  	  
	 
	 /**
	  *
	  * 显示接收到的消息
	  * 
	  */
	 @Override 
	 public void messageReceived(IoSession session, Object message)  
	 throws Exception {  
		 System.out.println("thr result is");
	 } 
	 
	 public void sessionIdle(IoSession session, Object message)  
	 throws Exception {  
		 System.out.println("session is idle");
	 } 
	 
	 public void sessionClosed(IoSession session, Object message)  
	 throws Exception {  
		 System.out.println("session is closed");
	 } 
}
