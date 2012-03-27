package event;

import world.Spell;
import world.Unit;

public class SpellEvent extends LogEvent {

	protected final Spell spell;

	public SpellEvent(int time, Unit caster, Unit target, Spell spell) {
		super(time, caster, target);
		this.spell = spell;
	}

	public Spell getSpell() {
		return spell;
	}
	
	@Override
	public String getText() {
		if (target == Unit.nil) {
			// Aucune cible
			return source.getName() + " lance " + spell.getName();
		}
		return source.getName() + " lance " + spell.getName() + " sur "
				+ target.getName();
	}

}
