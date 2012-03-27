package event;

import report.Heal;
import world.Spell;
import world.Unit;

public class SpellHealEvent extends SpellEvent implements HealEvent {

	private final Heal heal;

	public SpellHealEvent(int time, Unit source, Unit target,
			Spell spell, Heal heal) {
		super(time, source, target, spell);
		this.heal = heal;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Heal getHeal() {
		return heal;
	}

}
