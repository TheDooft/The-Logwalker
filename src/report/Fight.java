package report;

import java.util.ArrayList;

import world.Unit;
import boss.Boss;
import event.LogEvent;

public class Fight {
	private Boss boss;
	private int beginTime;
	private int endTime;
	private ArrayList<LogEvent> eventList;
	private ArrayList<Unit> playerList;
	private ArrayList<Unit> petList;
	private ArrayList<Unit> npcList;
	private Boolean computed;
	
	public Fight(Boss boss, int beginTime) {
		this.boss = boss;
		this.beginTime = beginTime;
		this.eventList = new ArrayList<LogEvent>();
		this.computed = false;
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
	
	public Boolean getComputed() {
		return computed;
	}
	
	public void compute(){
		playerList = new ArrayList<>();
		npcList = new ArrayList<>();
		petList = new ArrayList<>();
		
		for (LogEvent event : eventList){
			addToList(event.getSource());
			addToList(event.getTarget());
		}
		computed = true;
	}

	private void addToList(Unit unit) {
		if (unit == Unit.nil)
			return;
		if (unit.isTypePlayer()){
			if (!playerList.contains(unit))
				playerList.add(unit);
		}
		if (unit.isTypeGuardian() || unit.isTypePet()){
			if (!petList.contains(unit))
				petList.add(unit);
		}
		if (unit.isTypeNpc()){
			if (!npcList.contains(unit))
				npcList.add(unit);
		}
	}

	public ArrayList<Unit> getPlayerList() {
		return playerList;
	}
	
	public ArrayList<Unit> getNpcList() {
		return npcList;
	}
	
	public ArrayList<Unit> getPetList() {
		return petList;
	}
}
