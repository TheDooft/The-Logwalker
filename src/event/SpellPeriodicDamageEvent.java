package event;

import report.Damage;
import world.Spell;
import world.Timestamp;
import world.Unit;

public class SpellPeriodicDamageEvent extends SpellEvent implements DamageEvent {

    private final Damage damage;

    public SpellPeriodicDamageEvent(Timestamp time, Unit source, Unit target, Spell spell, Damage damage) {
        super(time, source, target, spell);
        this.damage = damage;
    }

    @Override
    protected String getText() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Damage getDamage() {
        return damage;
    }

}
