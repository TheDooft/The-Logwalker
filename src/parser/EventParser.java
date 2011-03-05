package parser;

import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.LogEvent;

public abstract class EventParser {


    public EventParser() {
    }

    public abstract LogEvent parse(Timestamp time, Unit source, Unit target, String[] params) throws ParseException;

    public abstract boolean match(String key);

}
