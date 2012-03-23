package event;

import report.Damage;
import world.EnvironmentalType;
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
	protected String getText() {
		String string = source.getName() + " " + " deals " + damage.getAmount()
				+ " " + damage.getSchool() + " damage";
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
		string += " (" + type.toString() + ")";
		return string;
	}

	@Override
	public Damage getDamage() {
		return damage;
	}

}
