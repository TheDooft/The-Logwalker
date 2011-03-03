package report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import world.TimeInterval;
import world.Unit;
import event.LogEvent;

public class LogReport {

    private static final long MOB_IDLE_TIME = 20000; //20s
    List<LogEvent> logEventList = new ArrayList<LogEvent>();
    private final UnitManager unitManager = new UnitManager();
    private final SpellManager spellManager = new SpellManager();
    private final List<Fight> fights = new ArrayList<Fight>();

    public void addEvent(LogEvent logEvent) {
        logEventList.add(logEvent);
    }

    public int getEventCount(){
        return logEventList.size();
    }

    public List<LogEvent> getEventList() {
        return logEventList;
    }

    public UnitManager getUnitManager() {
        return unitManager;
    }

    public SpellManager getSpellManager() {
        return spellManager ;
    }

    public List<Fight> getFigthsWith(String string) {
        //TODO
        return fights;
    }

    public List<Fight> getAllFigths() {
        return fights;
    }


    public void compute() {
        Collection<Unit> mobs = unitManager.getMobs();

        Map<Unit, UnitActivity> mobsActivity = new HashMap<Unit, UnitActivity>();

        for(Unit mob: mobs) {
            mobsActivity.put(mob, new UnitActivity(mob));
        }

        List<Fight> tempFightList = new ArrayList<Fight>();

        for(LogEvent event: logEventList) {
            for(Unit mob: mobs) {
                if(event.involve(mob)) {
                    UnitActivity unitActivity = mobsActivity.get(mob);
                    if(TimeInterval.getDuration(unitActivity.getLastEventTime(), event.getTime()) < MOB_IDLE_TIME) {
                        unitActivity.addEvent(event);
                    } else {
                        Fight fight = new Fight();
                        fight.addMobActivity(unitActivity);

                        tempFightList.add(fight);

                        //Open new Mob activity
                        UnitActivity newActivity = new UnitActivity(mob);
                        newActivity.addEvent(event);
                        mobsActivity.put(mob, newActivity);
                    }
                }
            }
        }




        for(UnitActivity mobActivity: mobsActivity.values()) {
            Fight fight = new Fight();
            fight.addMobActivity(mobActivity);

            tempFightList.add(fight);
        }

        while (!mergeFights(tempFightList));






        fights.addAll(tempFightList);
        Collections.sort(fights);

    }

    public boolean mergeFights(List<Fight> tempFightList) {

        for(int i = 0; i <  tempFightList.size() ; i++) {
            for(int j = i+1; j <  tempFightList.size() ; j++) {
                if(tempFightList.get(i).getTimeInterval().intersect(tempFightList.get(j).getTimeInterval())) {
                    List<UnitActivity> mobActivities = tempFightList.get(j).getMobActivities();
                    for(UnitActivity mobActivity: mobActivities) {
                        tempFightList.get(i).addMobActivity(mobActivity);
                    }
                    tempFightList.remove(j);
                    return false;
                }
            }
        }
        return true;
    }


}
