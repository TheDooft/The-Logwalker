package event;

import world.AuraType;
import world.Spell;
import world.Unit;

public class SpellStolenEvent extends SpellEvent {
	private final Spell extraSpell;
	private final AuraType auraType;

	public SpellStolenEvent(int time, Unit caster, Unit target,
			Spell spell, Spell extraSpell, String extraType) {
		super(time, caster, target, spell);
		this.extraSpell = extraSpell;
		this.auraType = AuraType.valueOf(extraType);
	}

	public Spell getExtraSpell() {
		return extraSpell;
	}

	public AuraType getExtraType() {
		return auraType;
	}

	@Override
	public String getText() {
		if (target == Unit.nil) {
			return source.getName() + " " + spell.getName() + " stoles "
					+ this.spell.getName() + ".";
		}
		return source.getName() + " " + spell.getName() + " stoles "
				+ this.target.getName() + " " + this.spell.getName() + ".";
	}
}
