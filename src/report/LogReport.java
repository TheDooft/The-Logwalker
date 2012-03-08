package report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import world.TimeInterval;
import world.Unit;
import event.LogEvent;

public class LogReport {

	private static final long MOB_IDLE_TIME = 20000; // 20s
	private static final long MIN_FIGHT_DURATION = 1000; // 1s
	List<LogEvent> logEventList = new ArrayList<LogEvent>();
	private final UnitManager unitManager = new UnitManager();
	private final SpellManager spellManager = new SpellManager();
	private final List<Fight> fights = new ArrayList<Fight>();

	public void addEvent(LogEvent logEvent) {
		logEventList.add(logEvent);
	}

	public int getEventCount() {
		return logEventList.size();
	}

	public List<LogEvent> getEventList() {
		return logEventList;
	}

	public UnitManager getUnitManager() {
		return unitManager;
	}

	public SpellManager getSpellManager() {
		return spellManager;
	}

	public List<Fight> getFigthsWith(String string) {
		// TODO
		return fights;
	}

	public List<Fight> getAllFigths() {
		return fights;
	}

	public void compute() {
		Collection<Unit> mobs = unitManager.getMobs();

		// Map<Unit, UnitActivity> mobsActivity = new HashMap<Unit,
		// UnitActivity>();

		

		List<Fight> tempFightList = new ArrayList<Fight>();

		for (LogEvent event : logEventList) {
			
			
			addInvolvement(event.getSource(), event);
			if(event.getTarget() != event.getSource()) {
				addInvolvement(event.getSource(), event);
			}
			
			/*
			 * for (Unit mob : mobs) { if (event.involve(mob)) { UnitActivity
			 * unitActivity = mobsActivity.get(mob); if
			 * (TimeInterval.getDuration(unitActivity.getLastEventTime(),
			 * event.getTime()) < MOB_IDLE_TIME) { unitActivity.addEvent(event);
			 * } else { Fight fight = new Fight();
			 * fight.addMobActivity(unitActivity);
			 * 
			 * tempFightList.add(fight);
			 * 
			 * // Open new Mob activity UnitActivity newActivity = new
			 * UnitActivity(mob); newActivity.addEvent(event);
			 * mobsActivity.put(mob, newActivity); } } }
			 */
		}

		
		for (Unit mob : mobs) {
			for (UnitActivity mobActivity : mob.getActivities()) {
				Fight fight = new Fight();
				fight.addMobActivity(mobActivity);
				tempFightList.add(fight);
			}
		}

		while (!mergeFights(tempFightList))
			;

		fights.addAll(tempFightList);

		// Clear very small combats
		Iterator<Fight> iterator = fights.iterator();
		while (iterator.hasNext()) {
			Fight next = iterator.next();
			if (next.getTimeInterval().getDuration() < MIN_FIGHT_DURATION) {
				iterator.remove();
			}
		}

		Collections.sort(fights);

		fillCharactersActivities();

	}

	private void fillCharactersActivities() {
		Iterator<Fight> iterator = fights.iterator();
		if (!iterator.hasNext()) {
			return;
		}
		Fight activeCombat = iterator.next();

		Map<Unit, UnitActivity> characterCurrentActivity = new HashMap<Unit, UnitActivity>();

		for (LogEvent event : logEventList) {
			while (event.getTime().after(activeCombat.getEndTime().plus(5000))) {

				for (UnitActivity activity : characterCurrentActivity.values()) {
					activeCombat.addCharacterActivity(activity);
				}
				characterCurrentActivity.clear();

				if (!iterator.hasNext()) {
					return;
				}
				activeCombat = iterator.next();
			}

			if (event.getTime().afterOrEqual(
					activeCombat.getBeginTime().minus(5000))) {

				Unit source = event.getSource();
				if (source.isPlayer()) {
					if (!characterCurrentActivity.containsKey(source)) {
						characterCurrentActivity.put(source, new UnitActivity(
								source));
					}
					UnitActivity activity = characterCurrentActivity
							.get(source);
					activity.addEvent(event);
				}

				Unit target = event.getTarget();
				if (target.isPlayer() && !target.equals(source)) {
					if (!characterCurrentActivity.containsKey(target)) {
						characterCurrentActivity.put(target, new UnitActivity(
								target));
					}

					UnitActivity activity = characterCurrentActivity
							.get(target);
					activity.addEvent(event);
				}
			}

		}

		for (UnitActivity activity : characterCurrentActivity.values()) {
			activeCombat.addCharacterActivity(activity);
		}
		characterCurrentActivity.clear();

	}

	public boolean mergeFights(List<Fight> tempFightList) {

		for (int i = 0; i < tempFightList.size(); i++) {
			for (int j = i + 1; j < tempFightList.size(); j++) {
				if (tempFightList.get(i).getTimeInterval()
						.intersect(tempFightList.get(j).getTimeInterval())) {
					List<UnitActivity> mobActivities = tempFightList.get(j)
							.getMobActivities();
					for (UnitActivity mobActivity : mobActivities) {
						tempFightList.get(i).addMobActivity(mobActivity);
					}
					tempFightList.remove(j);
					return false;
				}
			}
		}
		return true;
	}

	private void addInvolvement(Unit mob, LogEvent event) {
		UnitActivity unitActivity = mob.getLastActivity();
		if (TimeInterval.getDuration(unitActivity.getLastEventTime(),
				event.getTime()) < MOB_IDLE_TIME) {
			unitActivity.addEvent(event);
		} else {
			// Open new Mob activity
			UnitActivity newActivity = mob.newActivity();
			newActivity.addEvent(event);
		}
	}

}
