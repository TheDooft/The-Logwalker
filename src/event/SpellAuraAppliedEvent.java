package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellAuraAppliedEvent extends SpellEvent {

    private final String type; //BUFF, DEBUFF

    public SpellAuraAppliedEvent(Timestamp time, Unit caster, Unit target, Spell spell, String type) {
        super(time, caster, target, spell);
        this.type = type;
    }

    @Override
    protected String getText() {
        if(target == Unit.nil) {
            //Aucune cible
            return source.getName() + " lance "+ spell.getName();
        }
        return source.getName() + " applique le "+(type.equals("BUFF") ? "buff" : "debuff")+" "+ spell.getName() + " sur "+ target.getName();
    }





}
