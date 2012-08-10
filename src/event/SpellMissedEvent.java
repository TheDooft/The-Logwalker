package event;

import report.Miss;
import world.Spell;
import world.Unit;

public class SpellMissedEvent extends SpellEvent {

	private final Miss miss;

	public SpellMissedEvent(int time, Unit source, Unit target, Spell spell,
			Miss miss) {
		super(time, source, target, spell);
		this.miss = miss;
	}

	public Miss getMiss() {
		return miss;
	}

	@Override
	public String getText() {
		String ret = source.getName() + " " + spell.getName() + " miss";
		if (target != null)
			ret += " on " + target.getName();
		ret += " (" + miss.toString() + ").";
		return ret;
	}
}
