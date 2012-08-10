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
	public String getText() {
		String ret = source.getName() + " applies "
				+ (auraType == AuraType.BUFF ? "buff " : "debuff ")
				+ spell.getName() + "(" + this.amount + ")";
		if (target != Unit.nil)
			ret += " on " + target.getName();
		return ret;
	}
}
