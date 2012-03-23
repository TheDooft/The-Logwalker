package event;

import world.Unit;

public class RangeEvent extends LogEvent {


    public RangeEvent(int time, Unit source, Unit target) {
        super(time, source, target);

    }

    @Override
    protected String getText() {
        return source.getName() + " tire sur "+ target.getName();
    }

}
