package event;

import report.Damage;
import world.Spell;
import world.Timestamp;
import world.Unit;

public class DamageShieldEvent extends LogEvent implements DamageEvent {

    private final Spell spell;
    private final Damage damage;

    public DamageShieldEvent(Timestamp time, Unit source, Unit target, Spell spell, Damage damage) {
        super(time, source, target);
        this.spell = spell;
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
