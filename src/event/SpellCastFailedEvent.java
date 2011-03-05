package event;

import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellCastFailedEvent extends SpellEvent {

    private final String failedType;

    public SpellCastFailedEvent(Timestamp time, Unit caster, Unit target, Spell spell, String failedType) {
        super(time, caster, target, spell);
        this.failedType = failedType;
    }

    @Override
    protected String getText() {
        return null;
    }





}
