package event;

import report.Heal;
import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellPeriodicHealEvent extends SpellEvent implements HealEvent {

    private final Heal heal;

    public SpellPeriodicHealEvent(Timestamp time, Unit source, Unit target, Spell spell, Heal heal) {
        super(time, source, target, spell);
        this.heal = heal;
    }

    @Override
    protected String getText() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Heal getHeal() {
        return heal;
    }

}
