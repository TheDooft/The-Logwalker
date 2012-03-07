package event;

import report.Miss;
import world.Spell;
import world.Timestamp;
import world.Unit;


public class SpellPeriodicMissedEvent extends SpellEvent {
	private final Miss miss;
	
	public SpellPeriodicMissedEvent(Timestamp time, Unit caster, Unit target, Spell spell, Miss miss) {
        super(time, caster, target, spell);
        this.miss = miss;
    }
    
    public Miss getMiss() {
		return this.miss;
	}
    
    @Override
    protected String getText() {
        if(target == Unit.nil) {
            //No Target
            return source.getName() + " " + spell.getName() + " miss.";
        }
        return source.getName() + " " + spell.getName() + " miss on "+ target.getName() + ".";
    }
}
