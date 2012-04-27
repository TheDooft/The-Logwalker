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
	private ArrayList<Unit> ownedPetList;
	private ArrayList<Unit> npcList;
	private ArrayList<Unit> freePetList;
	private Boolean computed;

	public Fight(Boss boss, int beginTime) {
		this.boss = boss;
		this.beginTime = beginTime;
		this.eventList = new ArrayList<LogEvent>();
		this.computed = false;
	}

	public boolean isActive(Unit unit) {
		return playerList.contains(unit) || ownedPetList.contains(unit)
				|| npcList.contains(unit) || freePetList.contains(unit);
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

	public void removeEvent(LogEvent event) {
		this.eventList.remove(event);
	}

	public void clear() {
		this.eventList.clear();

	}

	public Boolean getComputed() {
		return computed;
	}

	public void compute() {
		playerList = new ArrayList<Unit>();
		npcList = new ArrayList<Unit>();
		ownedPetList = new ArrayList<Unit>();
		freePetList = new ArrayList<Unit>();

		for (LogEvent event : eventList) {
			addToList(event.getSource());
			addToList(event.getTarget());
		}

		for (Unit unit : playerList)
			unit.update();
		for (Unit unit : npcList)
			unit.update();
		for (Unit unit : ownedPetList)
			unit.update();
		for (Unit unit : freePetList)
			unit.update();

		computed = true;
	}

	private void addToList(Unit unit) {
		if (unit == Unit.nil)
			return;
		if (unit.isTypePlayer()) {
			if (!playerList.contains(unit))
				add(playerList, unit);
		}
		if (unit.isTypePet() || unit.isTypeGuardian()) {
			if (unit.getOwner() != Unit.nil) {
				if (!ownedPetList.contains(unit))
					add(ownedPetList, unit);
			} else {
				if (!freePetList.contains(unit))
					add(freePetList, unit);
			}
		}
		if (unit.isTypeNpc() && !unit.getOwner().isTypePlayer()) {
			if (!npcList.contains(unit))
				add(npcList, unit);
		}
	}

	private void add(ArrayList<Unit> list, Unit unit) {
		list.add(unit);
	}

	public ArrayList<Unit> getPlayerList() {
		return playerList;
	}

	public ArrayList<Unit> getNpcList() {
		return npcList;
	}

	public ArrayList<Unit> getOwnedPetList() {
		return ownedPetList;
	}

	public ArrayList<Unit> getFreePetList() {
		return freePetList;
	}
}
