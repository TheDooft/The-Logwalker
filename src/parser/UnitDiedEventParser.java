package parser;

import world.Unit;
import event.LogEvent;
import event.UnitDiedEvent;

public class UnitDiedEventParser extends EventParser{

    public UnitDiedEventParser() {
        super("UNIT_DIED");
    }

    @Override
    public LogEvent parse(String date, String time, Unit source, Unit target, String[] params) {

        return new UnitDiedEvent(date, time, target);
    }

}
