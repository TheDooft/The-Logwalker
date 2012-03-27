package event;

import world.AuraType;
import world.Spell;
import world.Unit;

public class SpellAuraRemovedEvent extends SpellEvent {

	private final AuraType auraType; // BUFF, DEBUFF

	public SpellAuraRemovedEvent(int time, Unit caster, Unit target,
			Spell spell, String auraType) {
		super(time, caster, target, spell);
		this.auraType = AuraType.valueOf(auraType);
	}

	@Override
	public String getText() {
		if (target == Unit.nil) {
			return source.getName() + " lance " + spell.getName();
		}
		return source.getName() + " applique le "
				+ (auraType == AuraType.BUFF ? "buff" : "debuff") + " "
				+ spell.getName() + " sur " + target.getName();
	}

}
