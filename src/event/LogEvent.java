package event;

public abstract class LogEvent {

    private final String date;
    private final String time;

    public LogEvent(String date, String time) {
        this.date = date;
        this.time = time;

    }

    @Override
    public String toString() {
        return time+" "+getText();
    }

    protected abstract String getText();

}
