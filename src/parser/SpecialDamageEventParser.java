package parser;

import world.Spell;
import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.DamageShieldEvent;
import event.DamageSplitEvent;
import event.EnvironmentalDamageEvent;
import event.LogEvent;

public class SpecialDamageEventParser extends EventParser {

	public SpecialDamageEventParser() {
	}

	@Override
	public LogEvent parse(Timestamp time, Unit source, Unit target,
			String[] params) throws ParseException {

		// return new SwingEvent(time, source, target);

		String key = params[LogParser.BASE_PARAM2];

		if (key.equals("DAMAGE_SHIELD")) {
			return new DamageShieldEvent(time, source, target, new Spell(params[LogParser.PREFIX_PARAM1],
							LogParser.parseString(params[LogParser.PREFIX_PARAM2]),
							LogParser.parseInt(params[LogParser.PREFIX_PARAM3])),
					LogParser.parseDamage(params, LogParser.SUFFIX_PARAM1));
		} else if (key.equals("ENVIRONMENTAL_DAMAGE")) {
			return new EnvironmentalDamageEvent(time, source, target,
					params[LogParser.PREFIX_PARAM1], LogParser.parseDamage(params, LogParser.PREFIX_PARAM2));
		} else if (key.equals("DAMAGE_SPLIT")) {
			return new DamageSplitEvent(time, source, target, new Spell(params[LogParser.PREFIX_PARAM1],
							LogParser.parseString(params[LogParser.PREFIX_PARAM2]),
							LogParser.parseInt(params[LogParser.PREFIX_PARAM3])),
					LogParser.parseDamage(params, LogParser.SUFFIX_PARAM1));
		}

		throw new ParseException("Unknown event type " + key);

	}

	@Override
	public boolean match(String key) {
		return key.startsWith("DAMAGE_")
				|| key.startsWith("ENVIRONMENTAL_DAMAGE");
	}

}
