package report;

import java.util.ArrayList;

import tools.FileManager;
import boss.Boss;
import event.LogEvent;

public class ReportEngine {
	private static ReportEngine instance;
	static private ArrayList<Boss> xmlBossList;
	private ArrayList<Fight> fightList;
	private Fight raw;
	private Fight currentFight;
	
	public ReportEngine() {
		xmlBossList = FileManager.readBossXml();
		fightList = new ArrayList<Fight>();
		fightList.add(new Fight(new Boss("Trash", 0, "achievement_arena_5v5_3.jpg", "", null), 0));
		raw = new Fight(new Boss("raw", 0, "", "", null), 0);
	}
	
	public void setCurrentFight(Fight currentFight) {
		this.currentFight = currentFight;
	}
	
	public Fight getCurrentFight() {
		return currentFight;
	}
	
	static public ReportEngine getInstance(){
		if (instance == null)
			instance = new ReportEngine();
		return instance;
	}
	
	public ArrayList<Fight> getFightList() {
		return fightList;
	}
	
	static public ArrayList<Boss> getXmlBossList() {
		return xmlBossList;
	}

	public void addEvent(LogEvent event) {
		raw.addEvent(event);
	}

	public void addTrashEvent (LogEvent event){
		fightList.get(0).addEvent(event);
	}
	
	public Fight getRaw() {
		return raw;
	}
	
	public void addFight (Fight fight){
		fightList.add(fight);
	}

	public void clear() {
		fightList.get(0).clear();
		
	}
}
