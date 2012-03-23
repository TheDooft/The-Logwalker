package event;

import world.Spell;
import world.Unit;

public class SpellInstakillEvent extends SpellEvent {
	public SpellInstakillEvent(int time, Unit source, Unit target,
			Spell spell) {
		super(time, source, target, spell);
	}

	@Override
	protected String getText() {
		if (target == Unit.nil) {
			return source.getName() + " " + spell.getName() + " instakills.";
		}
		return source.getName() + " " + spell.getName() + " instakills "
				+ target.getName() + ".";
	}
}
