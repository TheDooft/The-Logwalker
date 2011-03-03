package event;

import world.Unit;

public class UnitDiedEvent extends LogEvent {

    private final Unit unit;

    public UnitDiedEvent(String date, String time, Unit unit) {
        super(date, time);
        this.unit = unit;

    }

    @Override
    protected String getText() {
        return unit.getName() + " meurt";
    }





}
