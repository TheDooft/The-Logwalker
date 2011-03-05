package parser;

import report.LogReport;
import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.DamageShieldEvent;
import event.EnvironmentalDamageEvent;
import event.LogEvent;

public class SpecialDamageEventParser extends EventParser{

    private final LogReport report;

    public SpecialDamageEventParser(LogReport report) {
        this.report = report;
    }

    @Override
    public LogEvent parse(Timestamp time, Unit source, Unit target, String[] params) throws ParseException {

        //return new SwingEvent(time, source, target);

        String key = params[0];

        if(key.equals("DAMAGE_SHIELD")) {
            return new DamageShieldEvent(time, source, target,report.getSpellManager().parseSpell(params[7], LogParser.parseString(params[8]), LogParser.parseInt(params[9])), LogParser.parseDamage(params, 10));
        }else if(key.equals("ENVIRONMENTAL_DAMAGE")) {
            return new EnvironmentalDamageEvent(time, source, target, params[7],LogParser.parseDamage(params, 8));
        }

        throw new ParseException("Unknown event type "+key);

    }

    @Override
    public boolean match(String key) {
        return key.startsWith("DAMAGE_") || key.startsWith("ENVIRONMENTAL_DAMAGE");
    }

}
