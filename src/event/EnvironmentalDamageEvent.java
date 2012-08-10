package event;

import report.Damage;
import world.EnvironmentalType;
import world.Spell;
import world.Unit;

public class EnvironmentalDamageEvent extends LogEvent implements DamageEvent {

	private final Damage damage;
	private final EnvironmentalType type;

	public EnvironmentalDamageEvent(int time, Unit source, Unit target,
			String reason, Damage damage) {
		super(time, source, target);
		this.damage = damage;
		this.type = EnvironmentalType.valueOf(reason);
	}

	@Override
	public String getText() {
		String ret = source.getName();
		if (damage.isCritical())
			ret += " criticaly";
		ret += " hits";
		if (target == Unit.nil) {
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
		ret += " (" + type.toString() + ")";
		return ret;
	}

	@Override
	public Damage getDamage() {
		return damage;
	}

}
