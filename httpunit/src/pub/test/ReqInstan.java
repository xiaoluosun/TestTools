package pub.test;

public class ReqInstan {
	private String menuOne;
	private String menuTwo;
	private String model;
	private String request;
	private String code;
	private String responseCode;
	private String responseMessage;
	private String result;
	private String times;
	private String remark;
	private String username;
	private int num;
	private int nums;
	
	public ReqInstan(){
		menuOne = "";
		menuTwo = "";
		model = "";
		request = "";
		code = "";
		responseCode = "";
		responseMessage = "";
		result = "";
		times = "";
		remark = "";
	}

	public String getMenuOne() {
		return menuOne;
	}

	public void setMenuOne(String menuOne) {
		this.menuOne = menuOne;
	}

	public String getMenuTwo() {
		return menuTwo;
	}

	public void setMenuTwo(String menuTwo) {
		this.menuTwo = menuTwo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public int getNum() {
		return num;
	}
	
	/*
	 * *
	 * 设置导出表格sheet页的数量 
	 * 
	 * 
	 */

	public void setNum(int num) {
		this.num = num;
	}
	
	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}
	
	public String getUser() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}	
}
