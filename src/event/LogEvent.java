package event;

import world.Timestamp;
import world.Unit;

public abstract class LogEvent {

    private final Timestamp time;
    protected final Unit source;
    protected final Unit target;

    public LogEvent(Timestamp time, Unit source, Unit target) {
        this.time = time;
        this.source = source;
        this.target = target;

    }

    @Override
    public String toString() {
        return time+" "+getText();
    }

    protected abstract String getText();

    public Unit getSource() {
        return source;
    }

    public Unit getTarget() {
        return target;
    }

    public boolean involve(Unit unit) {
        return source.equals(unit) || target.equals(unit);
    }

    public Timestamp getTime() {
        return time;
    }



}
