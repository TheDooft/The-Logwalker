package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellExtraAttacksEvent extends SpellEvent {
	private final int amount;

	public SpellExtraAttacksEvent(Timestamp time, Unit caster, Unit target,
			Spell spell, int amount) {
		super(time, caster, target, spell);
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	protected String getText() {
		if (target == Unit.nil) {
			// No Target
			return source.getName() + " gets " + spell.getName();
		}
		return source.getName() + " casts " + this.amount + " extra "
				+ spell.getName() + " on " + target.getName();
	}
}
