package event;

import world.Spell;
import world.Unit;

public class SpellCreateEvent extends SpellEvent {
	public SpellCreateEvent(int time, Unit caster, Unit target,
			Spell spell) {
		super(time, caster, target, spell);
	}

	@Override
	protected String getText() {
		if (target == Unit.nil) {
			// No Target
			return source.getName() + " creates " + spell.getName() + ".";
		}
		return source.getName() + " creates " + spell.getName() + " on "
				+ target.getName() + ".";
	}

}
