package event;

import world.Unit;

public abstract class LogEvent {

    private final int time;
    protected final Unit source;
    protected final Unit target;

    public LogEvent(int time, Unit source, Unit target) {
        this.time = time;
        this.source = source;
        this.target = target;

    }

    @Override
    public String toString() {
        return time+" "+getText();
    }

    public abstract String getText();

    public Unit getSource() {
        return source;
    }

    public Unit getTarget() {
        return target;
    }

    public boolean involve(Unit unit) {
        return source.equals(unit) || target.equals(unit);
    }

    public int getTime() {
        return time;
    }



}
