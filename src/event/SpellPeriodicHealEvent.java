package event;

import report.Heal;
import world.Spell;
import world.Unit;

public class SpellPeriodicHealEvent extends SpellEvent implements HealEvent {

	private final Heal heal;

	public SpellPeriodicHealEvent(int time, Unit source, Unit target,
			Spell spell, Heal heal) {
		super(time, source, target, spell);
		this.heal = heal;
	}

	@Override
	public String getText() {
		String ret = source.getName() + " " + spell.getName();
		if (heal.isCritical())
			ret += " critically";
		ret += " periodic heals ";
		if (target != Unit.nil)
			ret += target.getName();
		ret += " for " + heal.getAmount();
		if (heal.getAbsorbed() > 0)
			ret += " (" + heal.getAbsorbed() + " absorbed)";
		if (heal.getOverHealing() > 0)
			ret += " (" + heal.getOverHealing() + " overheal)";
		return ret;
	}

	@Override
	public Heal getHeal() {
		return heal;
	}

}
