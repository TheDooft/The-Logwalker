package parser;

import world.Timestamp;
import world.Unit;
import event.LogEvent;
import event.UnitDiedEvent;

public class UnitDiedEventParser extends EventParser{

    public UnitDiedEventParser() {
    }

    @Override
    public LogEvent parse(Timestamp time, Unit source, Unit target, String[] params) {

        return new UnitDiedEvent( time, target);
    }

    @Override
    public boolean match(String key) {
        return key.equals("UNIT_DIED");
    }

}
