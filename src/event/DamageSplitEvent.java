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
		String ret = source.getName() + " " + spell.getName();
		if (damage.isCritical())
			ret += " critically";
		ret += " splits";
		if (target != Unit.nil) {
			ret += " " + target.getName();
		}
		ret += " for " + damage.getAmount() + " "
				+ Spell.getSchoolName(damage.getSchool()) + " damage";
		if (damage.getAbsorbed() > 0)
			ret += " (" + damage.getAbsorbed() + " absorbed)";
		if (damage.getBlocked() > 0)
			ret += " (" + damage.getBlocked() + " blocked)";
		if (damage.getResisted() > 0)
			ret += " (" + damage.getResisted() + " resisted)";
		if (damage.getOverkill() > 0)
			ret += " (" + damage.getOverkill() + " overkill)";
		if (damage.isGlancing())
			ret += " (Glancing)";
		if (damage.isCrushing())
			ret += " (Crushing)";
		return ret;
	}

	@Override
	public Damage getDamage() {
		return damage;
	}

}
