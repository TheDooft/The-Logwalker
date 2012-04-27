package event;

import tools.SpecialCaseManager;
import world.Spell;
import world.Unit;

public class SpellCastSuccessEvent extends SpellEvent {

	public SpellCastSuccessEvent(int time, Unit caster, Unit target,
			Spell spell) {
		super(time, caster, target, spell);
		if (SpecialCaseManager.isSpecialSpell(spell.getId())){
			SpecialCaseManager specialCaseManager = SpecialCaseManager.getInstance();
			specialCaseManager.addSpecialSummon(caster,spell);
		}
	}

	@Override
	public String getText() {
		String ret = source.getName() + " succesfuly cast " + spell.getName();
		if (target != Unit.nil)
			ret += " on " + target.getName();
		ret += ".";
		return ret;
	}

}
