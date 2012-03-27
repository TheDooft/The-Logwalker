package event;

import world.Spell;
import world.Unit;

public class RangeEvent extends SpellEvent {


    public RangeEvent(int time, Unit source, Unit target, Spell spell) {
        super(time, source, target, spell);
    }

    @Override
	public String getText() {
        return source.getName() + " shoots "+ target.getName();
    }

}
