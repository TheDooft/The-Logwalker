package report;

import java.util.ArrayList;
import java.util.List;

import world.Timestamp;
import world.Unit;
import event.LogEvent;

public class UnitActivity {

    private final Unit unit;
    List<LogEvent> logEventList = new ArrayList<LogEvent>();
    Timestamp beginTime = null;
    Timestamp endTime = null;
    private LogEvent lastEvent;

    public UnitActivity(Unit mob) {
        this.unit = mob;
    }

    public void addEvent(LogEvent event) {
        logEventList.add(event);
        lastEvent = event;
    }

    public Timestamp getBeginTime() {

        if(beginTime != null) {
            return beginTime;
        }

        beginTime = Timestamp.MAX;

        for(LogEvent event : logEventList) {
            Timestamp time = event.getTime();
            if(time.before(beginTime)) {
                beginTime = time;
            }
        }
        return beginTime;
    }

    public Timestamp getEndTime() {

        if(endTime != null) {
            return endTime;
        }

        endTime = Timestamp.MIN;

        for(LogEvent event : logEventList) {
            Timestamp time = event.getTime();
            if(time.after(endTime)) {
                endTime = time;
            }
        }
        return endTime;
    }

    public Unit getUnit() {
        return unit;
    }

    public Timestamp getLastEventTime() {
        if(lastEvent == null) {
            return Timestamp.MAX;
        }
        return lastEvent.getTime();
    }



}
