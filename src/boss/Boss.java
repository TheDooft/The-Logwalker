package boss;

public class Boss {
	private String name;
	private int idleTime;
	private String icon;
	private String image;
	private int[] uids;

	public Boss(String name, int idleTime, String icon, String image,int[] uids) {
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
	
	public int[] getUids() {
		return uids;
	}
}
