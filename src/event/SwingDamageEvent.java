package event;

import report.Damage;
import world.Spell;
import world.Unit;

public class SwingDamageEvent extends SwingEvent implements DamageEvent {

    private final Damage damage;

    public SwingDamageEvent(int time, Unit source, Unit target, Damage damage) {
        super(time, source, target);
        this.damage = damage;
    }

    @Override
	public String getText() {
    	String ret = source.getName() + " hits";
		if (target == Unit.nil) {
			ret += " " + target.getName();
		}
		ret += " for " + damage.getAmount() + " " + Spell.getSchoolName(damage.getSchool()) + " damage";
		if (damage.getAbsorbed() > 0)
			ret += " (" + damage.getAbsorbed() + " absorbed)";
		if (damage.getBlocked() > 0)
			ret += " (" + damage.getBlocked() + " blocked)";
		if (damage.getResisted() > 0)
			ret += " (" + damage.getResisted() + " resisted)";
		if (damage.getOverkill() > 0)
			ret += " (" + damage.getOverkill() + " overkill)";
		return ret;
    }

    @Override
    public Damage getDamage() {
        return damage;
    }

}
