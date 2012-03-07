package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellStolenEvent extends SpellEvent {
	private final Spell extraSpell;
	private final String auraType;

	public SpellStolenEvent(Timestamp time, Unit caster, Unit target,
			Spell spell, Spell extraSpell, String extraType) {
		super(time, caster, target, spell);
		this.extraSpell = extraSpell;
		this.auraType = extraType;
	}

	public Spell getExtraSpell() {
		return extraSpell;
	}

	public String getExtraType() {
		return auraType;
	}

	@Override
	protected String getText() {
		if (target == Unit.nil) {
			return source.getName() + " " + spell.getName() + " stoles "
					+ this.spell.getName() + ".";
		}
		return source.getName() + " " + spell.getName() + " stoles "
				+ this.target.getName() + " " + this.spell.getName() + ".";
	}
}
