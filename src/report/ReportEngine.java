package report;

import java.util.ArrayList;

import event.LogEvent;

import tools.FileManager;

import boss.Boss;

public class ReportEngine {
	private static ReportEngine instance;
	static private ArrayList<Boss> xmlBossList;
	private ArrayList<Fight> fightList;
	
	public ReportEngine() {
		xmlBossList = FileManager.readBossXml();
		fightList = new ArrayList<Fight>();
		fightList.add(new Fight(new Boss("trash", 0, "", "", null), 0));
	}
	
	static public ReportEngine getInstance(){
		if (instance == null)
			instance = new ReportEngine();
		return instance;
	}
	
	static public ArrayList<Boss> getXmlBossList() {
		return xmlBossList;
	}

	public void addEvent(LogEvent event) {
		fightList.get(0).addEvent(event);
	}

	public Fight getTrash() {
		return fightList.get(0);
	}

	public void clear() {
		fightList.get(0).clear();
		
	}
}
