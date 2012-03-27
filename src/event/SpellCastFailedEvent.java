package event;

import world.FailedType;
import world.Spell;
import world.Unit;

public class SpellCastFailedEvent extends SpellEvent {

	private final String failedType;

	public SpellCastFailedEvent(int time, Unit caster, Unit target,
			Spell spell, String failedType) {
		super(time, caster, target, spell);
		this.failedType = FailedType.getFailedType(failedType);
	}

	@Override
	public String getText() {
		String ret = new String();
		ret += source.getName() + " " + spell.getName() + " failed ";
		if (target != null)
			ret += " on " + target.getName();
		ret += ". Reason: " + failedType + ".";
		return ret;
	}

}
