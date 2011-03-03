package parser;

import world.Timestamp;
import world.Unit;
import event.LogEvent;

public abstract class EventParser {


    public EventParser() {
    }

    public abstract LogEvent parse(Timestamp time, Unit source, Unit target, String[] params);

    public abstract boolean match(String key);

}
