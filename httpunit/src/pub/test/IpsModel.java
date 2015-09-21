package pub.test;

import java.util.ArrayList;

public class IpsModel {
	private String modelName;
	private ArrayList<IpsRequest> ipsReqs;
	
	public IpsModel(){
		modelName = "";
		ipsReqs = new ArrayList<IpsRequest>();
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public ArrayList<IpsRequest> getIpsReqs() {
		return ipsReqs;
	}

	public void setIpsReqs(ArrayList<IpsRequest> ipsReqs) {
		this.ipsReqs = ipsReqs;
	}
	
	public String getReqHead (String domain, int i){
		if(i<0||i>ipsReqs.size()){
			System.out.println("out boundary");
			return "out boundary";
		}
		else{
			IpsRequest req = new IpsRequest();
			req = ipsReqs.get(i);
			String url = req.reqHead(domain);
			return url;			
		}
		
	}	
	
	
}
