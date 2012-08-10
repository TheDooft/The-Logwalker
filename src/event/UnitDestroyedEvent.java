package event;

import world.Unit;

public class UnitDestroyedEvent extends LogEvent {

    public UnitDestroyedEvent(int time, Unit unit) {
        super(time, Unit.nil, unit);
    }

    @Override
	public String getText() {
        return target.getName() + " has been destroyed";
    }





}
