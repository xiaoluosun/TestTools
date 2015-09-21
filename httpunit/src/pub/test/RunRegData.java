package pub.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.test.ReadRequests;

public class RunRegData {
	Map<String, List> requests = new HashMap<String, List>();
	public RunRegData(){
		requests = new HashMap<String, List>();
	}
	public void setData(Map<String, List> requests){
		this.requests = requests;
		System.out.println(requests);
	}
	public Map<String, List> getData(){
		return requests;
	}
	public void RegData(){		
		String path  = "\\data\\standard\\posts_queue.xml";		
		ReadRequests.readReqXml(path, null);  
	}
	public static void main(String[] args){
		RunRegData dataReg =  new RunRegData();
		dataReg.RegData();
	}
}
