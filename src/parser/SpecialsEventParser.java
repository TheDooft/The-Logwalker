package parser;

import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.EnchantAppliedEvent;
import event.EnchantRemovedEvent;
import event.LogEvent;
import event.PartyKillEvent;
import event.UnitDestroyedEvent;
import event.UnitDiedEvent;

public class SpecialsEventParser extends EventParser{

    public SpecialsEventParser() {
    }

    @Override
    public LogEvent parse(Timestamp time, Unit source, Unit target, String[] params) throws ParseException {

        //return new SwingEvent(time, source, target);

        String key = params[0];

        if(key.equals("UNIT_DIED")) {
            return new UnitDiedEvent(time, target);
        }else if(key.equals("UNIT_DESTROYED")) {
            return new UnitDestroyedEvent(time, target);
        }else if(key.equals("PARTY_KILL")) {
            return new PartyKillEvent(time, source, target);
        }        else if(key.equals("ENCHANT_APPLIED")) {
            return new EnchantAppliedEvent(time,source, target, LogParser.parseString(params[7]),Integer.valueOf(params[8]),LogParser.parseString(params[9]));
        }else if(key.equals("ENCHANT_REMOVED")) {
            return new EnchantRemovedEvent(time,source, target, LogParser.parseString(params[7]),Integer.valueOf(params[8]),LogParser.parseString(params[9]));
        }

        throw new ParseException("Unknown special event type "+key);

    }

    @Override
    public boolean match(String key) {
        return key.equals("ENCHANT_APPLIED") || key.equals("ENCHANT_REMOVED") || key.equals("PARTY_KILL") || key.equals("UNIT_DIED") || key.equals("UNIT_DESTROYED");
    }

}
