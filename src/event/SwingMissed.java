package event;

import report.Miss;
import world.Unit;

public class SwingMissed extends SwingEvent {

    private final Miss miss;

    public SwingMissed(int time, Unit source, Unit target, Miss miss) {
        super(time, source, target);
        this.miss = miss;
    }

    public Miss getMiss() {
		return miss;
	}
    
    @Override
    protected String getText() {
        // TODO Auto-generated method stub
        return null;
    }

}
