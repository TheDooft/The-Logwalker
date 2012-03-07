package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellResurrectEvent extends SpellEvent {
	public SpellResurrectEvent(Timestamp time, Unit caster, Unit target,
			Spell spell) {
		super(time, caster, target, spell);
	}

	@Override
	protected String getText() {
		if (target == Unit.nil) {
			// No Target
			return source.getName() + " " + spell.getName() + " resurrects.";
		}
		return source.getName() + " " + spell.getName() + " resurrects "
				+ target.getName() + ".";
	}
}
