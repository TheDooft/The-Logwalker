package event;

import report.Energize;
import world.Spell;
import world.Unit;

public class SpellPeriodicDrainEvent extends SpellEvent {
	private Energize energize;
	private int extraAmount;

	public SpellPeriodicDrainEvent(int time, Unit source, Unit target,
			Spell spell, Energize energize, int extraAmount) {
		super(time, source, target, spell);
		this.energize = energize;
		this.extraAmount = extraAmount;
	}

	public Energize getEnergize() {
		return energize;
	}

	public int getExtraAmount() {
		return extraAmount;
	}

	@Override
	public String getText() {
		if (target == Unit.nil) {
			return source.getName() + " " + spell.getName() + " drains "
					+ energize.getAmount() + " (+" + extraAmount + ") "
					+ energize.getType() + ".";
		}
		return source.getName() + " " + spell.getName() + " drains "
				+ this.target.getName() + " " + energize.getAmount() + " (+"
				+ extraAmount + ") " + energize.getType() + ".";
	}
}
