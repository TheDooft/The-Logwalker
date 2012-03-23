package event;

import world.AuraType;
import world.Spell;
import world.Unit;

public class SpellAuraAppliedDoseEvent extends SpellEvent {
	private final AuraType auraType; // BUFF, DEBUFF
	private final int amount; //

	public SpellAuraAppliedDoseEvent(int time, Unit caster, Unit target,
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
			return source.getName() + " casts " + spell.getName();
		}
		return source.getName() + " applies " + spell.getName() + "("
				+ this.amount + ") "
				+ (auraType == AuraType.BUFF ? "buff" : "debuff") + " on "
				+ target.getName();
	}
}
