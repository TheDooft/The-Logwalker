package report;

import java.util.ArrayList;
import java.util.List;

import world.TimeInterval;
import world.Timestamp;
import world.Unit;
import event.LogEvent;

public class Fight implements Comparable<Fight>{
    List<LogEvent> logEventList = new ArrayList<LogEvent>();
    List<UnitActivity> mobActivities = new ArrayList<UnitActivity>();
    List<UnitActivity> charactersActivities = new ArrayList<UnitActivity>();

    public List<LogEvent> getEventList() {
        return logEventList;
    }

    public void addMobActivity(UnitActivity mobActivity) {
        mobActivities.add(mobActivity);
    }

    public void addCharacterActivity(UnitActivity activity) {
        charactersActivities.add(activity);
    }

    public TimeInterval getTimeInterval() {
        return new TimeInterval(getBeginTime(), getEndTime());
    }

    public Timestamp getBeginTime() {
        Timestamp beginTime = Timestamp.MAX;

        for(UnitActivity mobActivity : mobActivities) {
            Timestamp time = mobActivity.getBeginTime();
            if(time.before(beginTime)) {
                beginTime = time;
            }
        }
        return beginTime;
    }

    public Timestamp getEndTime() {
        Timestamp endTime = Timestamp.MIN;

        for(UnitActivity mobActivity : mobActivities) {
            Timestamp time = mobActivity.getEndTime();
            if(time.after(endTime)) {
                endTime = time;
            }
        }
        return endTime;
    }

    public List<UnitActivity> getMobActivities() {
        return mobActivities;
    }

    public List<Unit> getMobs() {
        List<Unit> mobs = new ArrayList<Unit>();

        for(UnitActivity mobActivity : mobActivities) {
            mobs.add(mobActivity.getUnit());
        }
        return mobs;
    }

    @Override
    public int compareTo(Fight o) {
        if(getBeginTime().after(o.getBeginTime())) {
            return 1;
        }

        if(getBeginTime().before(o.getBeginTime())) {
            return -1;
        }
        return 0;
    }

    public List<UnitActivity> getCharacterActivities() {
        return charactersActivities;
    }

    public List<Unit> getCharacters() {
        List<Unit> characters = new ArrayList<Unit>();

        for(UnitActivity characterActivity : charactersActivities) {
            characters.add(characterActivity.getUnit());
        }
        return characters;
    }
}
