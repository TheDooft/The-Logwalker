package parser;

import world.Unit;
import event.LogEvent;
import event.UnitDiedEvent;

public class UnitDiedEventParser extends EventParser{

    public UnitDiedEventParser() {
    }

    @Override
    public LogEvent parse(String date, String time, Unit source, Unit target, String[] params) {

        return new UnitDiedEvent(date, time, target);
    }

    @Override
    public boolean match(String key) {
        return key.equals("UNIT_DIED");
    }

}
