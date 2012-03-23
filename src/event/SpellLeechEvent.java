package event;

import report.Energize;
import world.Spell;
import world.Unit;

public class SpellLeechEvent extends SpellEvent {
	private Energize energize;
	private int extraAmount;

	public SpellLeechEvent(int time, Unit source, Unit target,
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
	protected String getText() {
		if (target == Unit.nil) {
			return source.getName() + " " + spell.getName() + " leechs "
					+ energize.getAmount() + " (+" + extraAmount + ") "
					+ energize.getType() + ".";
		}
		return source.getName() + " " + spell.getName() + " leechs "
				+ this.target.getName() + " " + energize.getAmount() + " (+"
				+ extraAmount + ") " + energize.getType() + ".";
	}
}
