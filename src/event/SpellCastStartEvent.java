package event;

import world.Spell;
import world.Unit;

public class SpellCastStartEvent extends SpellEvent {

	public SpellCastStartEvent(int time, Unit caster, Unit target,
			Spell spell) {
		super(time, caster, target, spell);
	}

	@Override
	public String getText() {
		String ret = source.getName() + " begins to cast " + spell.getName();
		if (target != Unit.nil)
			ret += " on " + target.getName();
		ret += ".";
		return ret;
	}

}
