package parser;

import event.LogEvent;

public abstract class EventParser {

    private final String key;

    public EventParser(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

     public abstract LogEvent parse(String date, String time, String[] params);

}
