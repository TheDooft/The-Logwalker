package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellCastStartEvent extends SpellEvent {

    public SpellCastStartEvent(Timestamp time, Unit caster, Unit target, Spell spell) {
        super(time, caster, target, spell);
    }

    @Override
    protected String getText() {
        return null;
    }





}
