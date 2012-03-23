package event;

import world.AuraType;
import world.Spell;
import world.Unit;

public class SpellAuraRemovedDoseEvent extends SpellEvent {
	private final AuraType auraType; // BUFF, DEBUFF
	private final int amount; //

	public SpellAuraRemovedDoseEvent(int time, Unit caster, Unit target,
			Spell spell, String auraType, int amount) {
		super(time, caster, target, spell);
		this.auraType = AuraType.valueOf(auraType);
		this.amount = amount;
	}

	public AuraType getAuraType() {
		return auraType;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	protected String getText() {
		if (target == Unit.nil) {
			// No Target
			return source.getName() + " " + spell.getName() + "fades ("
					+ this.amount + ")";
		}
		return source.getName() + " " + spell.getName() + " fades ("
				+ this.amount + ") " + " on " + target.getName();
	}
}
