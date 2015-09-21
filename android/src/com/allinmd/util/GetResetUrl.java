package com.allinmd.util;

import org.junit.Test;
import java.io.IOException;
import java.util.Properties;  
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*; 
import javax.mail.search.AndTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

/**
 * 从收件箱筛选系统发送的重置密码邮件，
 * 分析正文得到链接
 * @author sun
 *
 */
public class GetResetUrl extends Authenticator{ 
	private StringBuffer content; 							//邮件正文
	private StringBuffer strBuffer;		                    //重置密码链接
	
	public GetResetUrl(){
		content = new StringBuffer();
		strBuffer = new StringBuffer();
	}
	
	/**  
	 * 使用 java.mail.search 包中的 SearchTerm 类  
	 * 在接收邮件时进行组合搜索、过滤  
	 */ 
    public void searchMail() throws Exception {  	
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";   
        Properties props = System.getProperties();  
        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);  		//SSL方式连接邮件服务器
        props.setProperty("mail.pop3.socketFactory.fallback", "false");  
        props.setProperty("mail.pop3.port", "995");  							//指定SSL收件服务器接口
        props.setProperty("mail.pop3.socketFactory.port", "995"); 				//指定SSL收件服务器接口
        Session session = Session.getDefaultInstance(props, null);        
        URLName url = new URLName("pop3", "outlook.office365.com", 995, null, "sunmingchang@allinmd.cn", "mingchang199106-");  
        Store store = session.getStore(url);  						    //利用Session对象获得Store对象，并连接pop3服务器  
        store.connect();                   
        Folder folder = store.getFolder("inbox");  						//获得邮箱内的邮件夹Folder对象，以"读-写"打开          
        try{
	        folder.open(Folder.READ_WRITE);  	                         	
	        SearchTerm st = new AndTerm(  									//搜索符合条件的mail
	                new FromStringTerm("services@dr-ing.cn"),  
	                new SubjectTerm("您在唯医手机版网站的找回密码帮助"));  
	          
	        Message [] messages = (Message[]) folder.search(st);	        //得到符合条件的所有邮件，只取最近的一条。
	        Message message = messages[messages.length - 1];				
            getMailTextContent(message, content);
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
	        folder.close(true);  
	        store.close(); 
        }
    }
	
    /**
     * 用正则从邮件正文匹配重置密码链接
     * 并去除链接html字符"amp;"
     * return url
     * @throws Exception 
     */
    @Test
	public void getUrl() throws Exception {
		searchMail();
		String pattern = "(http://)(www|m).allinmd.cn/[a-n]{4,5}/customer/reset/password/input/\\?itemId=[0-9]{4}&amp;resetSite=[0-9]&amp;validCode=[0-9a-z]{32}"; 
		Pattern patcher1 = Pattern.compile(pattern); 
		Matcher matcher1 = patcher1.matcher(content);
		if(matcher1.find()) { 
			String str = matcher1.group(0); 						//得到链接
			Pattern patcher2 = Pattern.compile("amp."); 			//第一次去除amp;
			Matcher matcher2 = patcher2.matcher(str);
			StringBuffer sb = new StringBuffer();
			if(matcher2.find()) { 
				matcher2.appendReplacement(sb,"");
			}
			matcher2.appendTail(sb);	
			Pattern patcher3 = Pattern.compile("amp."); 			//第二次去除amp;
			Matcher matcher3 = patcher3.matcher(sb.toString());
			if(matcher3.find()) { 
				matcher3.appendReplacement(strBuffer,"");
			}
			matcher3.appendTail(strBuffer);
		}
		System.out.println(strBuffer.toString());		
	}
	
    /**
     * 获得邮件文本内容
     * @param part 邮件体
     * @param content 存储邮件文本内容的字符串
     * @throws MessagingException
     * @throws IOException
     */
	public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException { 
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断 
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0; 
        if (part.isMimeType("text/*") && !isContainTextAttach) { 
            content.append(part.getContent().toString()); 
        } else if (part.isMimeType("multipart/*")) { 
            Multipart multipart = (Multipart) part.getContent(); 
            int partCount = multipart.getCount(); 
            for (int i = 0; i < partCount; i++) { 
                BodyPart bodyPart = multipart.getBodyPart(i); 
                getMailTextContent(bodyPart,content); 
            } 
        } 
    }
} 