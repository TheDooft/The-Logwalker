package event;

import report.Damage;
import world.Timestamp;
import world.Unit;

public class SwingDamageEvent extends LogEvent implements DamageEvent {

    private final Damage damage;

    public SwingDamageEvent(Timestamp time, Unit source, Unit target, Damage damage) {
        super(time, source, target);
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
