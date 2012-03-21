package boss;

import java.util.ArrayList;

public class Boss {
	private String name;
	private int idleTime;
	private String icon;
	private String image;
	private ArrayList<Integer> uids;

	public Boss(String name, int idleTime, String icon, String image,ArrayList<Integer> uids) {
		this.name = name;
		this.idleTime = idleTime;
		this.icon = icon;
		this.image = image;
		this.uids = uids;
	}
	
	public String getName() {
		return name;
	}
	
	public int getIdleTime() {
		return idleTime;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getImage() {
		return image;
	}
	
	public ArrayList<Integer> getUids() {
		return uids;
	}
}
