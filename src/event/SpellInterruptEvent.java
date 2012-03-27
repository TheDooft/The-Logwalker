package event;

import world.Spell;
import world.Unit;

public class SpellInterruptEvent extends SpellEvent {
	final private Spell extraSpell;

	public SpellInterruptEvent(int time, Unit caster, Unit target,
			Spell spell, int extraSpellId, String extraSpellName,
			int extraSchool) {
		super(time, caster, target, spell);
		this.extraSpell = Spell.getInstance(extraSpellId, extraSpellName,
				extraSchool);
	}

	public Spell getExtraSpell() {
		return extraSpell;
	}

	@Override
	public String getText() {
		if (target == Unit.nil) {
			// No Target
			return source.getName() + " " + spell.getName() + " interrupts "
					+ this.extraSpell.getName() + ".";
		}
		return source.getName() + " " + spell.getName() + " interrupts "
				+ target.getName() + this.extraSpell.getName() + ".";
	}
}
