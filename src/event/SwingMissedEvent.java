package event;

import report.Miss;
import world.Unit;

public class SwingMissedEvent extends SwingEvent {

    private final Miss miss;

    public SwingMissedEvent(int time, Unit source, Unit target, Miss miss) {
        super(time, source, target);
        this.miss = miss;
    }

    public Miss getMiss() {
		return miss;
	}
    
    @Override
	public String getText() {
    	String ret = source.getName() + " swing miss";
		if (target != Unit.nil)
			ret += " on " + target.getName();
		ret += " (" + miss.toString() + ").";
		return ret;
    }

}
