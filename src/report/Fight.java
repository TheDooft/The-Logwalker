package report;

import java.util.ArrayList;

import world.Unit;
import boss.Boss;
import event.LogEvent;
import event.SpellCastSuccessEvent;

public class Fight {
	private Boss boss;
	private int beginTime;
	private int endTime;
	private ArrayList<LogEvent> eventList;
	private ArrayList<Unit> playerList;
	private ArrayList<Unit> petList;
	private ArrayList<Unit> npcList;
	private ArrayList<Unit> guardianList;
	private Boolean computed;
	private Unit lastBattleChickenOwner;

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
		petList = new ArrayList<Unit>();
		guardianList = new ArrayList<Unit>();

		this.lastBattleChickenOwner = Unit.nil;
		for (LogEvent event : eventList) {
			addToList(event.getSource());
			addToList(event.getTarget());
			// Special case : Battle Chicken
			if (event.getClass().equals(SpellCastSuccessEvent.class)) {
				SpellCastSuccessEvent spellEvent = (SpellCastSuccessEvent) event;
				if (spellEvent.getSpell().getId() == 23133){
					this.lastBattleChickenOwner = event.getSource();
					//System.out.println(event.getText());
					}
			}
		}
		computed = true;
	}

	private void addToList(Unit unit) {
		if (unit == Unit.nil)
			return;
		if (this.lastBattleChickenOwner != Unit.nil && unit.getId() == 8836){
			System.out.println("Owner Found !");
			unit.setOwner(this.lastBattleChickenOwner);
			this.lastBattleChickenOwner = Unit.nil;
		}
		if (unit.isTypePlayer()) {
			if (!playerList.contains(unit))
				playerList.add(unit);
		}
		if (unit.isTypePet()) {
			if (!petList.contains(unit))
				petList.add(unit);
		}
		if (unit.isTypeGuardian()) {
			if (!guardianList.contains(unit))
				guardianList.add(unit);
		}
		if (unit.isTypeNpc() && !unit.getOwner().isTypePlayer()) {
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

	public ArrayList<Unit> getGuardianList() {
		return guardianList;
	}
}
