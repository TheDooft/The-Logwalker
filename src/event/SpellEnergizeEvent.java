package event;

import report.Energize;
import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellEnergizeEvent extends SpellEvent {

    private final Energize energize;

    public SpellEnergizeEvent(Timestamp time, Unit source, Unit target, Spell spell, Energize energize) {
        super(time, source, target, spell);
        this.energize = energize;
    }

    @Override
    protected String getText() {
        // TODO Auto-generated method stub
        return null;
    }

}
