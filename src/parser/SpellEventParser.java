package parser;

import report.LogReport;
import world.Spell;
import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.LogEvent;
import event.SpellAuraAppliedEvent;
import event.SpellAuraRefreshEvent;
import event.SpellAuraRemovedEvent;
import event.SpellCastFailedEvent;
import event.SpellCastStartEvent;
import event.SpellCastSuccessEvent;
import event.SpellDamageEvent;
import event.SpellEnergizeEvent;
import event.SpellHealEvent;
import event.SpellMissedEvent;
import event.SpellPeriodicDamageEvent;
import event.SpellPeriodicEnergizeEvent;
import event.SpellPeriodicHealEvent;

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
        }else if(key.equals("SPELL_AURA_REFRESH")) {
            return new SpellAuraRefreshEvent(time, source, target,  getSpell(params), params[10]);
        } else if(key.equals("SPELL_PERIODIC_DAMAGE")) {
            return new SpellPeriodicDamageEvent(time, source, target,  getSpell(params), LogParser.parseDamage(params, 10));
        } else if(key.equals("SPELL_DAMAGE")) {
            return new SpellDamageEvent(time, source, target,  getSpell(params), LogParser.parseDamage(params, 10));
        } else if(key.equals("SPELL_HEAL")) {
            return new SpellHealEvent(time, source, target,  getSpell(params), LogParser.parseHeal(params, 10));
        } else if(key.equals("SPELL_PERIODIC_HEAL")) {
            return new SpellPeriodicHealEvent(time, source, target,  getSpell(params), LogParser.parseHeal(params, 10));
        } else if(key.equals("SPELL_CAST_START")) {
            return new SpellCastStartEvent(time, source, target,  getSpell(params));
        } else if(key.equals("SPELL_CAST_SUCCESS")) {
            return new SpellCastSuccessEvent(time, source, target,  getSpell(params));
        } else if(key.equals("SPELL_CAST_FAILED")) {
            return new SpellCastFailedEvent(time, source, target,  getSpell(params), params[10]);
        } else if(key.equals("SPELL_MISSED")) {
            return new SpellMissedEvent(time, source, target, getSpell(params) , LogParser.parseMiss(params, 10));
        } else if(key.equals("SPELL_ENERGIZE")) {
            return new SpellEnergizeEvent(time, source, target, getSpell(params) , LogParser.parseEnergize(params, 10));
        } else if(key.equals("SPELL_PERIODIC_ENERGIZE")) {
            return new SpellPeriodicEnergizeEvent(time, source, target, getSpell(params) , LogParser.parseEnergize(params, 10));
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
