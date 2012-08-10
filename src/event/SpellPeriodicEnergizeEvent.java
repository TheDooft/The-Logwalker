package event;

import report.Energize;
import world.Spell;
import world.Unit;

public class SpellPeriodicEnergizeEvent extends SpellEvent {

	private final Energize energize;

	public SpellPeriodicEnergizeEvent(int time, Unit source, Unit target,
			Spell spell, Energize energize) {
		super(time, source, target, spell);
		this.energize = energize;
	}

	public Energize getEnergize() {
		return energize;
	}

	@Override
	public String getText() {
		if (target == Unit.nil) {
			return source.getName() + " " + spell.getName() + " periodic gains "
					+ energize.getAmount() + " " + energize.toString();
		}
		return source.getName() + " " + spell.getName() + " periodic gives "
				+ this.target.getName() + " " + energize.getAmount() + " "
				+ energize.toString();
	}

}
