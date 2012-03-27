package event;

import report.Damage;
import world.Spell;
import world.Unit;

public class DamageSplitEvent extends SpellEvent implements DamageEvent {
	private final Damage damage;

	public DamageSplitEvent(int time, Unit source, Unit target,
			Spell spell, Damage damage) {
		super(time, source, target, spell);
		this.damage = damage;
	}

	@Override
	public String getText() {
		String string = source.getName() + " " + spell.getName() + " deals "
				+ damage.getAmount() + " " + damage.getSchool() + " damage";
		if (target == Unit.nil) {
			string += " to " + target.getName();
		}
		if (damage.getAbsorbed() > 0)
			string += " (" + damage.getAbsorbed() + " absorbed)";
		if (damage.getBlocked() > 0)
			string += " (" + damage.getBlocked() + " blocked)";
		if (damage.getResisted() > 0)
			string += " (" + damage.getResisted() + " resisted)";
		if (damage.getOverkill() > 0)
			string += " (" + damage.getOverkill() + " overkill)";
		return string;
	}

	@Override
	public Damage getDamage() {
		return damage;
	}

}
