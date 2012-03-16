package parser;

import world.Spell;
import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.LogEvent;
import event.SpellAuraAppliedDoseEvent;
import event.SpellAuraAppliedEvent;
import event.SpellAuraBrokenSpellEvent;
import event.SpellAuraRefreshEvent;
import event.SpellAuraRemovedDoseEvent;
import event.SpellAuraRemovedEvent;
import event.SpellCastFailedEvent;
import event.SpellCastStartEvent;
import event.SpellCastSuccessEvent;
import event.SpellCreateEvent;
import event.SpellDamageEvent;
import event.SpellDispelEvent;
import event.SpellEnergizeEvent;
import event.SpellExtraAttacksEvent;
import event.SpellHealEvent;
import event.SpellInstakillEvent;
import event.SpellInterruptEvent;
import event.SpellMissedEvent;
import event.SpellPeriodicDamageEvent;
import event.SpellPeriodicEnergizeEvent;
import event.SpellPeriodicHealEvent;
import event.SpellPeriodicMissedEvent;
import event.SpellResurrectEvent;
import event.SpellStolenEvent;
import event.SpellSummonEvent;

public class SpellEventParser extends EventParser {

	public SpellEventParser() {
	}

	@Override
	public LogEvent parse(Timestamp time, Unit source, Unit target,
			String[] params) throws ParseException {

		String key = params[0];

		if (key.equals("SPELL_AURA_APPLIED")) {
			return new SpellAuraAppliedEvent(time, source, target,
					getSpell(params), params[10]);
		} else if (key.equals("SPELL_AURA_APPLIED_DOSE")) {
			return new SpellAuraAppliedDoseEvent(time, source, target,
					getSpell(params), params[10], Integer.parseInt(params[11]));
		} else if (key.equals("SPELL_PERIODIC_MISSED")) {
			return new SpellPeriodicMissedEvent(time, source, target,
					getSpell(params), LogParser.parseMiss(params, 10));
		} else if (key.equals("SPELL_INTERRUPT")) {
			return new SpellInterruptEvent(time, source, target,
					getSpell(params), Integer.parseInt(params[10]), params[11],
					Integer.parseInt(params[12]));
		} else if (key.equals("SPELL_SUMMON")) {
			return new SpellSummonEvent(time, source, target, getSpell(params));
		} else if (key.equals("SPELL_EVENT")) {
			return new SpellPeriodicMissedEvent(time, source, target,
					getSpell(params), LogParser.parseMiss(params, 10));
		} else if (key.equals("SPELL_EXTRA_ATTACKS")) {
			return new SpellExtraAttacksEvent(time, source, target,
					getSpell(params), Integer.parseInt(params[10]));
		} else if (key.equals("SPELL_AURA_REMOVED")) {
			return new SpellAuraRemovedEvent(time, source, target,
					getSpell(params), params[10]);
		} else if (key.equals("SPELL_AURA_REFRESH")) {
			return new SpellAuraRefreshEvent(time, source, target,
					getSpell(params), params[10]);
		} else if (key.equals("SPELL_PERIODIC_DAMAGE")) {
			return new SpellPeriodicDamageEvent(time, source, target,
					getSpell(params), LogParser.parseDamage(params, 10));
		} else if (key.equals("SPELL_DAMAGE")) {
			return new SpellDamageEvent(time, source, target, getSpell(params),
					LogParser.parseDamage(params, 10));
		} else if (key.equals("SPELL_HEAL")) {
			return new SpellHealEvent(time, source, target, getSpell(params),
					LogParser.parseHeal(params, 10));
		} else if (key.equals("SPELL_PERIODIC_HEAL")) {
			return new SpellPeriodicHealEvent(time, source, target,
					getSpell(params), LogParser.parseHeal(params, 10));
		} else if (key.equals("SPELL_CAST_START")) {
			return new SpellCastStartEvent(time, source, target,
					getSpell(params));
		} else if (key.equals("SPELL_CAST_SUCCESS")) {
			return new SpellCastSuccessEvent(time, source, target,
					getSpell(params));
		} else if (key.equals("SPELL_CAST_FAILED")) {
			return new SpellCastFailedEvent(time, source, target,
					getSpell(params), params[10]);
		} else if (key.equals("SPELL_MISSED")) {
			return new SpellMissedEvent(time, source, target, getSpell(params),
					LogParser.parseMiss(params, 10));
		} else if (key.equals("SPELL_ENERGIZE")) {
			return new SpellEnergizeEvent(time, source, target,
					getSpell(params), LogParser.parseEnergize(params, 10));
		} else if (key.equals("SPELL_PERIODIC_ENERGIZE")) {
			return new SpellPeriodicEnergizeEvent(time, source, target,
					getSpell(params), LogParser.parseEnergize(params, 10));
		} else if (key.equals("SPELL_AURA_REMOVED_DOSE")) {
			return new SpellAuraRemovedDoseEvent(time, source, target,
					getSpell(params), params[10], Integer.parseInt(params[11]));
		} else if (key.equals("SPELL_CREATE")) {
			return new SpellCreateEvent(time, source, target, getSpell(params));
		} else if (key.equals("SPELL_AURA_BROKEN_SPELL")) {
			return new SpellAuraBrokenSpellEvent(time, source, target,
					getSpell(params), getExtraSpell(params), params[13]);
		} else if (key.equals("SPELL_RESURRECT")) {
			return new SpellResurrectEvent(time, source, target,
					getSpell(params));
		} else if (key.equals("SPELL_DISPEL")) {
			return new SpellDispelEvent(time, source, target, getSpell(params),
					getExtraSpell(params), params[13]);
		} else if (key.equals("SPELL_STOLEN")) {
			return new SpellStolenEvent(time, source, target, getSpell(params),
					getExtraSpell(params), params[13]);
		} else if (key.equals("SPELL_INSTAKILL")) {
			return new SpellInstakillEvent(time, source, target,
					getSpell(params));
		}

		throw new ParseException("Unknown event type " + key);
	}

	public Spell getExtraSpell(String[] params) {
		return new Spell(params[10], LogParser.parseString(params[11]),
						Integer.parseInt(params[12]));
	}

	public Spell getSpell(String[] params) {
		return new Spell(params[7], LogParser.parseString(params[8]),
						LogParser.parseInt(params[9]));
	}

	@Override
	public boolean match(String key) {
		return key.startsWith("SPELL");
	}

}
