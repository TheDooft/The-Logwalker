package event;

import world.AuraType;
import world.Spell;
import world.Unit;

public class SpellAuraBrokenSpellEvent extends SpellEvent {
	private final Spell extraSpell;
	private final AuraType auraType;

	public SpellAuraBrokenSpellEvent(int time, Unit caster, Unit target,
			Spell spell, Spell extraSpell, String auraType) {
		super(time, caster, target, spell);
		this.extraSpell = extraSpell;
		this.auraType = AuraType.valueOf(auraType);
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
			return source.getName() + " " + spell.getName() + " brokes "
					+ this.spell.getName() + ".";
		}
		return source.getName() + " " + spell.getName() + " brokes "
				+ this.target.getName() + " " + this.spell.getName() + ".";
	}
}
