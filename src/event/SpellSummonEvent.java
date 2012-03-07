package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellSummonEvent extends SpellEvent {
	public SpellSummonEvent(Timestamp time, Unit caster, Unit target, Spell spell) {
		super(time, caster, target, spell);
	}
	
	@Override
	protected String getText() {
		return source.getName() + " summons " + spell.getName() + ".";
	}
}
