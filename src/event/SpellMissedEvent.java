package event;

import report.Miss;
import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellMissedEvent extends SpellEvent {

    private final Miss miss;

    public SpellMissedEvent(Timestamp time, Unit source, Unit target, Spell spell, Miss miss) {
        super(time, source, target, spell);
        this.miss = miss;
    }

    @Override
    protected String getText() {
        // TODO Auto-generated method stub
        return null;
    }

}
