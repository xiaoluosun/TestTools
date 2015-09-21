package com.sun.demo;


import java.lang.Character.UnicodeBlock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GenCommon {
	
	//随机获取车牌号
	public static String carNum(){
		String carHead = getCarHead();
		String carChar = getUpperChar();
		String carTail = getCarTail();
		StringBuffer carNum = new StringBuffer(carHead + carChar + carTail);
		return carNum.toString();
	}
	
	private static String getCarHead(){
		String[] carHead = { "Y", "J", 	"G", "K", "B",
				"L", "X","S",	"H", "N"};		
		int index = (int) (Math.random() * carHead.length); 
		return carHead[index];
	}
	
	private static String getUpperChar(){
		String[] strChar = {"A",  "B",  "C",  "D",  "E",
				"F",  "G",  "H",  "I",  "J",  "K",  "L",  
				"M",  "N",  "O",  "P",  "Q",  "R",  "S",  
				"T",   "U",   "V",   "W",   "X",   "Y",   
				"Z"};
		int index = (int) (Math.random() * strChar.length);
		return strChar[index];
	}
	//返回一个5位数的字符串(00000-99999)
	private static String getCarTail(){				
		int r = (int) (Math.random() * 100000);
		String temp = Integer.toString(r);
		r = temp.length();
		switch(r){
		case 1:
			temp = "0000"+temp;
			 break;
		case 2:
			temp = "000"+temp;
			 break;
		case 3:
			temp = "00"+temp;
			 break;
		case 4:
			temp = "0"+temp;
			 break;
		default:
			break;		
		}
		return temp;
	}
	
	//返回一个ic卡编号
	public static String getICCard(){				
		String temp="";  
	    Date dt = new Date(); 
	    //出卡日期
	    SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");  
	    temp=sdf.format(dt);
	    //序号4位+2位随机数，目前先按6位随机数处理
	    String rand = "";
	    for(int i = 0; i<6; i++){
	    	int it = (int)(Math.random()*10);
	    	rand = rand +it;
	    }
	    temp = temp + rand;
	    int sum = sumString(temp);
	    int t = sum%10;
	    temp = temp + t;
	    
		return temp;
	}
	
	//计算字符串的和
	private static int sumString(String str){
		int sum = 0;
		char[] charArray = str.toCharArray();
		for(int i = 0 ; i<charArray.length; i++){
			int t = charArray[i]-48;
			sum = sum + t;
		}
		return sum;
	}
	
	//获取下n年日期
	public static String getSpecDate(int n){		
		String temp="";  
	    Date dt = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    temp=sdf.format(dt); 		
		String[] b = temp.split("-");		
		int c = Integer.parseInt(b[0]) + n;		
		temp = c+"-"+b[1]+"-"+b[2];		
	    return temp;   
	}
	
	//获取当前日期
	public static String getDate(){		
		String temp="";  
	    Date dt = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    temp=sdf.format(dt);  
	    return temp;   
	}
	
	//获取当前时间,包含日期
	public static String getCurTime(){		
		String temp="";  
	    Date dt = new Date();  
	    //最后的aa表示“上午”或“下午”,HH表示24小时制    如果换成hh表示12小时制  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    temp=sdf.format(dt);  
	    return temp;   
	}
	
	//获取当前时间不包含日期
	public static String getTime(){		
		String temp="";  
	    Date dt = new Date();  
	    //最后的aa表示“上午”或“下午”,HH表示24小时制    如果换成hh表示12小时制  
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
	    temp=sdf.format(dt);  
	    return temp;   
	}
	
	//获取一个10-20间的整数
	public static String getSmallInt(){				
		int r = (int) (Math.random() * 10) + 10;
		String temp = Integer.toString(r);
		return temp;
	}
	
	//随机获取一个主驾人名
	public static String getMaindriver(){
		String[] names={
				"YXP", "SXG", "YL","KY", "BX"
		};
		int index = (int) (Math.random() * 5);
		return names[index];
	}
	
	//随机获取一个副驾人名
	public static String getAssisdriver(){
		String[] names={
				"MS", "BR", "SY","XZ", "NT"
		};
		int index = (int) (Math.random() * 5);
		return names[index];
	}
	
	//获取自定义字符串
	public static String getCustomStr(){
		String rel = "customer";
		int index = (int) (Math.random() * 10);
		rel = rel + index;
		return rel;
	}
	
	//随机获取一个11为号码
	public static String getPhone(){		
		String phone = "1";		
		for(int i = 0; i<10; i++){
			int index = (int) (Math.random() * 10);
			phone=phone+index;
		}
		return phone;
	}
	
	//从10个固定身份证中随机获取一个
	public static String getIdcard(){		
		String[] idcards ={"110101199001011958","110101199001013072",
				"110101199001013195", "110101199001014817", "110101199001019570",	
				"110101199001012037", "110101199001016679","110101199001016775",
				"110101199001012918", "110101199001012758"};
		int i = (int) (Math.random() * 10);
		return idcards[i];
	}
	
	//随机获取一个联系电话
	public static String getFamilytel(){		
		String tel ="010-";
		for(int i = 0; i<8; i++){
			int re = (int) (Math.random() * 10);
			tel+=re;
		}
		
		return tel;
	}
	
	/*
	 * utf-8编码转换成unicode编码
	 * 
	 * */
    public  static String utf8ToUnicode(String inStr) {
        char[] myBuffer = inStr.toCharArray();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
            UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
            if (ub == UnicodeBlock.BASIC_LATIN) {
                sb.append(myBuffer[i]);
            } else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                int j = (int) myBuffer[i] - 65248;
                sb.append((char) j);
            } else {
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u" + hexS;
                sb.append(unicode.toLowerCase());
            }
        }
        return sb.toString();
    }
    
    /**
     * utf-8 转换成 unicode
     * @author fanhui
     * 2007-3-15
     * @param inStr
     * @return
     */
    
    public static String utf8ToUnicode2(String inStr) {
        char[] myBuffer = inStr.toCharArray();
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
         UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
            if(ub == UnicodeBlock.BASIC_LATIN){
             //半角英文数字及字符
             sb.append(myBuffer[i]);
            }else if(ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
             //全角英文数字及字符
             int j = (int) myBuffer[i] - 65248;
             sb.append((char)j);
            }else{
             //全半角汉字
             short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u"+hexS;
             sb.append(unicode.toLowerCase());
            }
        }
        return sb.toString();
    }
    
    private static boolean isNeedConvert(char para) {
        return ((para & (0x00FF)) != para);
    }

    
    public static String GBK2Unicode(String str) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char chr1 = (char) str.charAt(i);
 
            if (!isNeedConvert(chr1)) {
                result.append(chr1);
                continue;
            }
 
            result.append("\\u" + Integer.toHexString((int) chr1));
        }
 
        return result.toString();
    }
    
    public static String GUID(){
    	// 创建 GUID 对象
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        String uid = uuid.toString();
        // 转换为大写
        uid = uid.toUpperCase();
        // 替换 -
        uid = uid.replaceAll("-", "");
    	return uid;
    }
    
    public static void main(String[] args) {

	}


	
}
