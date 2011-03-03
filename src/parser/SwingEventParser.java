package parser;

import world.Timestamp;
import world.Unit;
import event.LogEvent;
import event.SwingEvent;

public class SwingEventParser extends EventParser{

    public SwingEventParser() {
    }

    @Override
    public LogEvent parse(Timestamp time, Unit source, Unit target, String[] params) {

        return new SwingEvent(time, source, target);
    }

    @Override
    public boolean match(String key) {
        return key.startsWith("SWING");
    }

}
