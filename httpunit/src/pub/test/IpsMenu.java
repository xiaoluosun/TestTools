package pub.test;

import java.util.ArrayList;



public class IpsMenu {
	private String levelOne;			// 一级菜单名称
	private String sourceid;			// 一级菜单id
	private ArrayList<ChildMenu> cMenu;	// 二级菜单
	
	public IpsMenu(){
		levelOne = "";
		sourceid = "";
		cMenu = new ArrayList<ChildMenu>();
	}
	
	public String getLevelOne() {
		return levelOne;
	}
	public void setLevelOne(String levelOne) {
		this.levelOne = levelOne;
	}
	public ArrayList<ChildMenu> getcMenu() {
		return cMenu;
	}
	public void setcMenu(ArrayList<ChildMenu> cMenu) {
		this.cMenu = cMenu;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}	
	
	
	
}
