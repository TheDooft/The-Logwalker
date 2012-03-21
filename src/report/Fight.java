package report;

import java.util.ArrayList;

import boss.Boss;
import event.LogEvent;

public class Fight {
	private Boss boss;
	private int beginTime;
	private int endTime;
	private ArrayList<LogEvent> eventList;
	
	public Fight(Boss boss, int beginTime) {
		this.boss = boss;
		this.beginTime = beginTime;
		this.eventList = new ArrayList<LogEvent>();
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public Boss getBoss() {
		return boss;
	}
	
	public int getBeginTime() {
		return beginTime;
	}
	
	public int getEndTime() {
		return endTime;
	}

	public ArrayList<LogEvent> getEventList() {
		return eventList;
	}
	
	public void addEvent(LogEvent event) {
		this.eventList.add(event);
	}
	
	public void removeEvent(LogEvent event){
		this.eventList.remove(event);
	}

	public void clear() {
		this.eventList.clear();
		
	}
}
