package parser;

import report.LogReport;
import world.Spell;
import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.LogEvent;
import event.SpellAuraAppliedEvent;
import event.SpellAuraRemovedEvent;
import event.SpellDamageEvent;
import event.SpellPeriodicDamageEvent;

public class SpellEventParser extends EventParser{

    private final LogReport report;

    public SpellEventParser(LogReport report) {
        this.report = report;
    }

    @Override
    public LogEvent parse(Timestamp time, Unit source, Unit target, String[] params) throws ParseException {


        String key = params[0];

        if(key.equals("SPELL_AURA_APPLIED")) {
            return new SpellAuraAppliedEvent(time, source, target,  getSpell(params), params[10]);
        }else if(key.equals("SPELL_AURA_REMOVED")) {
            return new SpellAuraRemovedEvent(time, source, target,  getSpell(params), params[10]);
        } else if(key.equals("SPELL_PERIODIC_DAMAGE")) {
            return new SpellPeriodicDamageEvent(time, source, target,  getSpell(params), LogParser.parseDamage(params, 10));
        } else if(key.equals("SPELL_DAMAGE")) {
            return new SpellDamageEvent(time, source, target,  getSpell(params), LogParser.parseDamage(params, 10));
        }



        throw new ParseException("Unknown event type "+key);


        //return new SpellEvent(time, source, target, report.getSpellManager().parseSpell(params[7], LogParser.parseString(params[8]), LogParser.parseInt(params[9])));
    }

    public Spell getSpell(String[] params) {
        return report.getSpellManager().parseSpell(params[7], LogParser.parseString(params[8]), LogParser.parseInt(params[9]));
    }

    @Override
    public boolean match(String key) {
        return key.startsWith("SPELL");
    }

}
