package parser;

import world.Spell;
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
import event.SpellDrainEvent;
import event.SpellEnergizeEvent;
import event.SpellExtraAttacksEvent;
import event.SpellHealEvent;
import event.SpellInstakillEvent;
import event.SpellInterruptEvent;
import event.SpellLeechEvent;
import event.SpellMissedEvent;
import event.SpellPeriodicDamageEvent;
import event.SpellPeriodicDrainEvent;
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
	public LogEvent parse(int time, Unit source, Unit target,
			String[] params) throws ParseException {

		String key = params[LogParser.BASE_PARAM2];

		if (key.equals("SPELL_AURA_APPLIED")) {
			return new SpellAuraAppliedEvent(time, source, target,
					getSpell(params), params[LogParser.SUFFIX_PARAM1]);
		} else if (key.equals("SPELL_AURA_APPLIED_DOSE")) {
			return new SpellAuraAppliedDoseEvent(time, source, target,
					getSpell(params), params[LogParser.SUFFIX_PARAM1],
					Integer.parseInt(params[LogParser.SUFFIX_PARAM2]));
		} else if (key.equals("SPELL_PERIODIC_MISSED")) {
			return new SpellPeriodicMissedEvent(time, source, target,
					getSpell(params), LogParser.parseMiss(params,
							LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_INTERRUPT")) {
			return new SpellInterruptEvent(time, source, target,
					getSpell(params),
					Integer.parseInt(params[LogParser.SUFFIX_PARAM1]),
					params[LogParser.SUFFIX_PARAM2],
					Integer.parseInt(params[LogParser.SUFFIX_PARAM3]));
		} else if (key.equals("SPELL_SUMMON")) {
			return new SpellSummonEvent(time, source, target, getSpell(params));
		} else if (key.equals("SPELL_EVENT")) {
			return new SpellPeriodicMissedEvent(time, source, target,
					getSpell(params), LogParser.parseMiss(params,
							LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_EXTRA_ATTACKS")) {
			return new SpellExtraAttacksEvent(time, source, target,
					getSpell(params),
					Integer.parseInt(params[LogParser.SUFFIX_PARAM1]));
		} else if (key.equals("SPELL_AURA_REMOVED")) {
			return new SpellAuraRemovedEvent(time, source, target,
					getSpell(params), params[LogParser.SUFFIX_PARAM1]);
		} else if (key.equals("SPELL_AURA_REFRESH")) {
			return new SpellAuraRefreshEvent(time, source, target,
					getSpell(params), params[LogParser.SUFFIX_PARAM1]);
		} else if (key.equals("SPELL_PERIODIC_DAMAGE")) {
			return new SpellPeriodicDamageEvent(time, source, target,
					getSpell(params), LogParser.parseDamage(params,
							LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_DAMAGE")) {
			return new SpellDamageEvent(time, source, target, getSpell(params),
					LogParser.parseDamage(params, LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_HEAL")) {
			return new SpellHealEvent(time, source, target, getSpell(params),
					LogParser.parseHeal(params, LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_PERIODIC_HEAL")) {
			return new SpellPeriodicHealEvent(time, source, target,
					getSpell(params), LogParser.parseHeal(params,
							LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_CAST_START")) {
			return new SpellCastStartEvent(time, source, target,
					getSpell(params));
		} else if (key.equals("SPELL_CAST_SUCCESS")) {
			return new SpellCastSuccessEvent(time, source, target,
					getSpell(params));
		} else if (key.equals("SPELL_CAST_FAILED")) {
			return new SpellCastFailedEvent(time, source, target,
					getSpell(params), params[LogParser.SUFFIX_PARAM1]);
		} else if (key.equals("SPELL_MISSED")) {
			return new SpellMissedEvent(time, source, target, getSpell(params),
					LogParser.parseMiss(params, LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_ENERGIZE")) {
			return new SpellEnergizeEvent(time, source, target,
					getSpell(params), LogParser.parseEnergize(params,
							LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_PERIODIC_ENERGIZE")) {
			return new SpellPeriodicEnergizeEvent(time, source, target,
					getSpell(params), LogParser.parseEnergize(params,
							LogParser.SUFFIX_PARAM1));
		} else if (key.equals("SPELL_AURA_REMOVED_DOSE")) {
			return new SpellAuraRemovedDoseEvent(time, source, target,
					getSpell(params), params[LogParser.SUFFIX_PARAM1],
					Integer.parseInt(params[LogParser.SUFFIX_PARAM2]));
		} else if (key.equals("SPELL_CREATE")) {
			return new SpellCreateEvent(time, source, target, getSpell(params));
		} else if (key.equals("SPELL_AURA_BROKEN_SPELL")) {
			return new SpellAuraBrokenSpellEvent(time, source, target,
					getSpell(params), getExtraSpell(params),
					params[LogParser.SUFFIX_PARAM4]);
		} else if (key.equals("SPELL_RESURRECT")) {
			return new SpellResurrectEvent(time, source, target,
					getSpell(params));
		} else if (key.equals("SPELL_DISPEL")) {
			return new SpellDispelEvent(time, source, target, getSpell(params),
					getExtraSpell(params), params[LogParser.SUFFIX_PARAM4]);
		} else if (key.equals("SPELL_STOLEN")) {
			return new SpellStolenEvent(time, source, target, getSpell(params),
					getExtraSpell(params), params[LogParser.SUFFIX_PARAM4]);
		} else if (key.equals("SPELL_INSTAKILL")) {
			return new SpellInstakillEvent(time, source, target,
					getSpell(params));
		} else if (key.equals("SPELL_DRAIN")) {
			return new SpellDrainEvent(time, source, target, getSpell(params),
					LogParser.parseEnergize(params, LogParser.SUFFIX_PARAM1),
					Integer.parseInt(params[LogParser.SUFFIX_PARAM3]));
		} else if (key.equals("SPELL_PERIODIC_DRAIN")) {
			return new SpellPeriodicDrainEvent(time, source, target,
					getSpell(params), LogParser.parseEnergize(params,
							LogParser.SUFFIX_PARAM1),
					Integer.parseInt(params[LogParser.SUFFIX_PARAM3]));
		} else if (key.equals("SPELL_LEECH")) {
			return new SpellLeechEvent(time, source, target, getSpell(params),
					LogParser.parseEnergize(params, LogParser.SUFFIX_PARAM1),
					Integer.parseInt(params[LogParser.SUFFIX_PARAM3]));
		}

		throw new ParseException("Unknown event type " + key);
	}

	public Spell getExtraSpell(String[] params) {
		return Spell.getInstance(Integer.parseInt(params[LogParser.SUFFIX_PARAM1]),
				LogParser.parseString(params[LogParser.SUFFIX_PARAM2]),
				Integer.parseInt(params[LogParser.SUFFIX_PARAM3]));
	}

	static public Spell getSpell(String[] params) {
		return Spell.getInstance(Integer.parseInt(params[LogParser.PREFIX_PARAM1]),
				LogParser.parseString(params[LogParser.PREFIX_PARAM2]),
				LogParser.parseInt(params[LogParser.PREFIX_PARAM3]));
	}

	@Override
	public boolean match(String key) {
		return key.startsWith("SPELL");
	}

}
