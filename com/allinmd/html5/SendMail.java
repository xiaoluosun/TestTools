package com.allinmd.html5;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMail{    
    private String to; 							//收件人邮箱地址    
    private String from; 						//发件人邮箱地址    
    private String smtpServer; 					//SMTP服务器地址    
    private String username ;					//登录SMTP服务器的用户名
    private String password ;					//登录SMTP服务器的密码
    private String subject; 					//邮件主题
    private String content; 				    //邮件正文    
    List<String> attachments = new ArrayList<String>();				//记录所有附件文件的集合
    
    public SendMail(String to, String from, String smtpServer, String username, String password,
    		String subject, String content){ 
        this.to = to;
        this.from = from;
        this.smtpServer = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }   
    public String transferChinese(String strText) {				//把邮件主题转换为中文
        try{      
            strText = MimeUtility.encodeText(
                new String(strText.getBytes(), "GB2312"), "GB2312", "B");
        }
        catch(Exception e){        
            e.printStackTrace();
        }
        return strText;
    }
    
    public void attachfile(String fname){						//增加附件，将附件文件名添加的List集合中
        attachments.add(fname);
    }
    
    public boolean send(){										//发送邮件      
        Properties props = new Properties();					//创建邮件Session所需的Properties对象
        props.put("mail.smtp.host" , smtpServer);
        props.put("mail.smtp.auth" , "true");       
        Session session = Session.getDefaultInstance(props		//创建Session对象           
            , new Authenticator() {								//以匿名内部类的形式创建登录服务器的认证对象           
                public PasswordAuthentication getPasswordAuthentication(){                
                    return new PasswordAuthentication(username,password); 
                }
            });
        try {            
            MimeMessage msg = new MimeMessage(session);						//构造MimeMessage并设置相关属性值            
            msg.setFrom(new InternetAddress(from));							//设置发件人            
            InternetAddress[] addresses = {new InternetAddress(to)};		//设置收件人
            msg.setRecipients(Message.RecipientType.TO , addresses);
            
            subject = transferChinese(subject);								//设置邮件主题
            msg.setSubject(subject);    
            
            Multipart mp = new MimeMultipart();								//构造Multipart            
            MimeBodyPart mbpContent = new MimeBodyPart();					//向Multipart添加正文
            mbpContent.setText(content);            
            mp.addBodyPart(mbpContent);										//将BodyPart添加到MultiPart中

            for(String efile : attachments) {								//向Multipart添加附件                															            																
                MimeBodyPart mbpFile = new MimeBodyPart();                	//遍历附件列表，将所有文件添加到邮件消息里
                FileDataSource fds = new FileDataSource(efile);				//以文件名创建FileDataSource对象                
                mbpFile.setDataHandler(new DataHandler(fds));				//处理附件
                mbpFile.setFileName(fds.getName());                
                mp.addBodyPart(mbpFile);									//将BodyPart添加到MultiPart中
            }           
            attachments.clear();											//清空附件列表            
            msg.setContent(mp);												//向Multipart添加MimeMessage            
            msg.setSentDate(new Date());									//设置发送日期            
            Transport.send(msg);											//发送邮件
        }
        catch (MessagingException mex){
            mex.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        SendMail sendMail = new SendMail("sunmingchang@allinmd.cn", "xiaoluosun@sina.com", "smtp.sina.com", "xiaoluosun@sina.com", 
        		"mingchang199106-", "测试邮件标题！", "你好这是一个带多附件的测试邮件！");
        //添加附件
        sendMail.attachfile("C:\\Users\\sun\\Desktop\\Temp\\picture\\DesktopPhotos\\26b77029ebe2e26d763ab3926a045f6f.jpg");
        if (sendMail.send()){
            System.out.println("---邮件发送成功---");
        } else {
        	System.out.println("---发送失败---");
        }
    }
}
