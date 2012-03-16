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

		String key = params[0];

		if (key.equals("DAMAGE_SHIELD")) {
			return new DamageShieldEvent(time, source, target, new Spell(params[7],
							LogParser.parseString(params[8]),
							LogParser.parseInt(params[9])),
					LogParser.parseDamage(params, 10));
		} else if (key.equals("ENVIRONMENTAL_DAMAGE")) {
			return new EnvironmentalDamageEvent(time, source, target,
					params[7], LogParser.parseDamage(params, 8));
		} else if (key.equals("DAMAGE_SPLIT")) {
			return new DamageSplitEvent(time, source, target, new Spell(params[7],
							LogParser.parseString(params[8]),
							LogParser.parseInt(params[9])),
					LogParser.parseDamage(params, 10));
		}

		throw new ParseException("Unknown event type " + key);

	}

	@Override
	public boolean match(String key) {
		return key.startsWith("DAMAGE_")
				|| key.startsWith("ENVIRONMENTAL_DAMAGE");
	}

}
