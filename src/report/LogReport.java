package report;

import java.util.ArrayList;
import java.util.List;

import event.LogEvent;

public class LogReport {

    List<LogEvent> logEventList = new ArrayList<LogEvent>();
    private final UnitManager unitManager = new UnitManager();

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
        // TODO Auto-generated method stub
        return unitManager;
    }


}
