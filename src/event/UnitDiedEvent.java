package event;

import world.Unit;

public class UnitDiedEvent extends LogEvent {

	public UnitDiedEvent(int time, Unit unit) {
		super(time, Unit.nil, unit);
	}

	@Override
	protected String getText() {
		return target.getName() + " meurt";
	}

}
