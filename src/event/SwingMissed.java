package event;

import report.Miss;
import world.Timestamp;
import world.Unit;

public class SwingMissed extends LogEvent {

    private final Miss miss;

    public SwingMissed(Timestamp time, Unit source, Unit target, Miss miss) {
        super(time, source, target);
        this.miss = miss;
    }

    @Override
    protected String getText() {
        // TODO Auto-generated method stub
        return null;
    }

}
