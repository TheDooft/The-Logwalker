package event;

import world.AuraType;
import world.Spell;
import world.Unit;

public class SpellAuraRefreshEvent extends SpellEvent {

	private final AuraType auraType; // BUFF, DEBUFF

	public SpellAuraRefreshEvent(int time, Unit caster, Unit target,
			Spell spell, String auraType) {
		super(time, caster, target, spell);
		this.auraType = AuraType.valueOf(auraType);
	}

	@Override
	public String getText() {
		if (target == Unit.nil) {
			// Aucune cible
			return source.getName() + " refresh " + spell.getName();
		}
		return source.getName() + " refresh "
				+ (auraType == AuraType.BUFF ? "buff" : "debuff") + " "
				+ spell.getName() + " on " + target.getName();
	}

}
