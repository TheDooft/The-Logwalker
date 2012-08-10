package event;

import world.Unit;

public class SwingEvent extends LogEvent {

    public SwingEvent(int time, Unit source, Unit target) {
        super(time, source, target);
    }

    @Override
	public String getText() {
        return source.getName() + " swing at "+ target.getName();
    }

}
