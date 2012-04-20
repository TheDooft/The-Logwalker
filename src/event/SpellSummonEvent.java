package event;

import world.Spell;
import world.Unit;

public class SpellSummonEvent extends SpellEvent {
	public SpellSummonEvent(int time, Unit caster, Unit target,
			Spell spell) {
		super(time, caster, target, spell);
		target.setOwner(caster);
	}

	@Override
	public String getText() {
		return source.getName() + " summons " + spell.getName() + ".";
	}
}
