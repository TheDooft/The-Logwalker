package event;

import world.Spell;
import world.Unit;

public class SpellEvent extends LogEvent {

    private final Unit caster;
    private final Unit target;
    private final Spell spell;

    public SpellEvent(String date, String time, Unit caster, Unit target, Spell spell) {
        super(date, time);
        this.caster = caster;
        this.target = target;
        this.spell = spell;
    }

    @Override
    protected String getText() {
        if(target == Unit.nil) {
            //Aucune cible
            return caster.getName() + " lance "+ spell.getName();
        }
        return caster.getName() + " lance "+ spell.getName() + " sur "+ target.getName();
    }





}
