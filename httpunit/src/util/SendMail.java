package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;








import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;



/**
 * Title: 使用javamail发送邮件
 */
public class SendMail {
	   private 	String to = "";// 收件人
	   private  String from = "";// 发件人
	   private  String host = "";// smtp主机
	   private  String username = "";
	   private  String password = "";
	   private  String subject = "";// 邮件主题
	   private  String content = "";// 邮件正文
	   

	   /**
		 * 方法说明：默认构造器
		 */
	public SendMail() {
	}
	

	/**
	 * 方法说明：构造器，提供直接的参数传入 
	 */
	public SendMail(String to, String from, String smtpServer, String username,
			String password, String subject, String content) {
		this.to = to;
		this.from = from;
		this.host = smtpServer;
		this.username = username;
		this.password = password;
		this.subject = subject;
		this.content = content;
	}


	/**
	 * 方法说明：设置邮件服务器地址 
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 方法说明：设置登录服务器校验密码 
	 */
	public void setPassWord(String pwd) {
		this.password = pwd;
	}

	/**
	 * 方法说明：设置登录服务器校验用户
	 */
	public void setUserName(String usn) {
		this.username = usn;
	}

	/**
	 * 方法说明：设置邮件发送目的邮箱 
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * 方法说明：设置邮件发送源邮箱 
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 方法说明：设置邮件主题 
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 方法说明：设置邮件内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 方法说明：把主题转换为中文
	 */
/*	public String transferChinese(String strText) {
		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(),
					"GB2312"), "GB2312", "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}*/

	/**
	 * 方法说明：发送邮件
	 * 返回类型：boolean 成功为true，反之为false
	 */
	public boolean sendMail() {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {
			// 构造MimeMessage
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			//subject = transferChinese(subject);
			msg.setSubject(subject);
			// 构造Multipart
			Multipart mp = new MimeMultipart();
			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setContent(content, "text/html;charset=gb2312");
//			mbpContent.setContent(content, "text/html;charset=utf-8");
			// 向MimeMessage添加（Multipart代表正文）
			mp.addBodyPart(mbpContent);
			msg.setContent(mp);
			msg.setSentDate(new Date());
			msg.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 方法说明：发送带附件的邮件
	 * 返回类型：boolean 成功为true，反之为false
	 */
	public boolean sendMail(ArrayList<String> files) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {
			// 构造MimeMessage
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			msg.setSubject(subject);
			// 构造Multipart
			Multipart mp = new MimeMultipart();
			// 向Multipart添加正文
			
			// 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            mp.addBodyPart(contentPart);
			
			for(int i=0; i<files.size(); i++){
				int index = files.get(i).lastIndexOf("\\");
				String fileName = files.get(i).substring(index+1);
				 //添加附件
	            BodyPart messageBodyPart= new MimeBodyPart();
	            DataSource source = new FileDataSource(files.get(i));
	            //添加附件的内容
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            //添加附件的标题
	            
	            messageBodyPart.setFileName(fileName);
	            mp.addBodyPart(messageBodyPart);
			}		
			
//			mp.addBodyPart(mbpContent);
			msg.setContent(mp);
			msg.setSentDate(new Date());
			msg.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
		return true;
	}
}
