package parser;

import world.Timestamp;
import world.Unit;
import event.LogEvent;
import event.RangeEvent;

public class RangeEventParser extends EventParser{

    public RangeEventParser() {
    }

    @Override
    public LogEvent parse(Timestamp time, Unit source, Unit target, String[] params) {

        return new RangeEvent(time, source, target);
    }

    @Override
    public boolean match(String key) {
        return key.startsWith("RANGE");
    }

}
