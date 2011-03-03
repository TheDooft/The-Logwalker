package parser;

import world.Unit;
import event.LogEvent;

public abstract class EventParser {


    public EventParser() {
    }

    public abstract LogEvent parse(String date, String time, Unit source, Unit target, String[] params);

    public abstract boolean match(String key);

}
