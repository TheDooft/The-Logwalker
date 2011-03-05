package event;

import world.Timestamp;
import world.Unit;

public class UnitDestroyedEvent extends LogEvent {

    public UnitDestroyedEvent(Timestamp time, Unit unit) {
        super(time, Unit.nil, unit);
    }

    @Override
    protected String getText() {
        return target.getName() + " meurt";
    }





}
