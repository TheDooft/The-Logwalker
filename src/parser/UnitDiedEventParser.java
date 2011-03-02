package parser;

import event.LogEvent;
import event.UnitDiedEvent;

public class UnitDiedEventParser extends EventParser{

    public UnitDiedEventParser() {
        super("UNIT_DIED");
    }

    @Override
    public LogEvent parse(String date, String time, String[] params) {

        String unitName = params[5];

        return new UnitDiedEvent(date, time, unitName);
    }

}
