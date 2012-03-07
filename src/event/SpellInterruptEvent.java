package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellInterruptEvent extends SpellEvent {
	final private int extraSpellId;
	final private String extraSpellName;
	final private int extraSchool;
	
	public SpellInterruptEvent(Timestamp time, Unit caster, Unit target, Spell spell,
			int extraSpellId, String extraSpellName, int extraSchool){
		super(time, caster, target, spell);
		this.extraSchool = extraSchool;
		this.extraSpellId = extraSpellId;
		this.extraSpellName = extraSpellName;
	}
	
	public int getExtraSpellId() {
		return extraSpellId;
	}
	
	public String getExtraSpellName() {
		return extraSpellName;
	}
	
	public int getExtraSchool() {
		return extraSchool;
	}
	
	@Override
    protected String getText() {
        if(target == Unit.nil) {
            //No Target
            return source.getName() + " " + spell.getName() + " interrupts " + this.extraSpellName + ".";
        }
        return source.getName() + " " + spell.getName() + " interrupts " + target.getName() + this.extraSpellName + ".";
    }
}
