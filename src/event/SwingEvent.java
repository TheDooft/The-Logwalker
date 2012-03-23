package event;

import world.Unit;

public class SwingEvent extends LogEvent {

    public SwingEvent(int time, Unit source, Unit target) {
        super(time, source, target);
    }

    @Override
    protected String getText() {
        return source.getName() + " tape "+ target.getName();
    }

}
