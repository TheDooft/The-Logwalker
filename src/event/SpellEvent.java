package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellEvent extends LogEvent {

    private final Spell spell;

    public SpellEvent(Timestamp time, Unit caster, Unit target, Spell spell) {
        super(time, caster, target);
        this.spell = spell;
    }

    @Override
    protected String getText() {
        if(target == Unit.nil) {
            //Aucune cible
            return source.getName() + " lance "+ spell.getName();
        }
        return source.getName() + " lance "+ spell.getName() + " sur "+ target.getName();
    }





}
