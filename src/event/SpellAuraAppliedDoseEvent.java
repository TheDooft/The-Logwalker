package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellAuraAppliedDoseEvent extends SpellEvent {
	 private final String auraType; //BUFF, DEBUFF
	 private final int amount; // 

	    public SpellAuraAppliedDoseEvent(Timestamp time, Unit caster, Unit target, Spell spell, String auraType, int amount) {
	        super(time, caster, target, spell);
	        this.auraType = auraType;
	        this.amount = amount;
	    }

	    public String getAuraType() {
			return auraType;
		}
	    
	    public int getAmount() {
			return amount;
		}
	    
	    @Override
	    protected String getText() {
	        if(target == Unit.nil) {
	            //No Target
	            return source.getName() + " casts "+ spell.getName();
	        }
	        return source.getName() + " applies " + spell.getName() + "(" + this.amount + ") " 
	        	+ (auraType.equals("BUFF") ? "buff" : "debuff") + " on "+ target.getName();
	    }
}
