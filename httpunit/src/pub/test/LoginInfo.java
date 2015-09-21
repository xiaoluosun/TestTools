package pub.test;

public class LoginInfo {
	private String url;
	private String username;
	private String password;
	
	public LoginInfo(){
		url = "";
		username = "";
		password = "";
	}
	
	/*
	 * *
	 * 获取登录信息中的url
	 * 
	 * 
	 */
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/*
	 * *
	 * 获取用户名信息 
	 * 
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

}
