package parser;

import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.LogEvent;
import event.SwingDamageEvent;
import event.SwingMissed;

public class SwingEventParser extends EventParser{

    public SwingEventParser() {
    }

    @Override
    public LogEvent parse(Timestamp time, Unit source, Unit target, String[] params) throws ParseException {

        String key = params[LogParser.BASE_PARAM2];

        if(key.equals("SWING_DAMAGE")) {
            return new SwingDamageEvent(time, source, target, LogParser.parseDamage(params, LogParser.PREFIX_PARAM1));
        }else if(key.equals("SWING_MISSED")) {
            return new SwingMissed(time, source, target, LogParser.parseMiss(params, LogParser.PREFIX_PARAM1));
        }

        throw new ParseException("Unknown event type "+key);

    }

    @Override
    public boolean match(String key) {
        return key.startsWith("SWING");
    }

}
