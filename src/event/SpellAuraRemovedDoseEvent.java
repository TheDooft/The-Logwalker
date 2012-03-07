package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellAuraRemovedDoseEvent extends SpellEvent {
	private final String auraType; // BUFF, DEBUFF
	private final int amount; //

	public SpellAuraRemovedDoseEvent(Timestamp time, Unit caster, Unit target,
			Spell spell, String auraType, int amount) {
		super(time, caster, target, spell);
		this.auraType = auraType;
		this.amount = amount;
	}

	public String getAuraType() {
		return auraType;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	protected String getText() {
		if (target == Unit.nil) {
			// No Target
			return source.getName() + " " + spell.getName() + "fades (" + this.amount + ")";
		}
		return source.getName() + " " + spell.getName() + " fades ("
				+ this.amount + ") " + " on " + target.getName();
	}
}
