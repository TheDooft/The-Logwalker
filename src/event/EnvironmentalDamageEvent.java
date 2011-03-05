package event;

import report.Damage;
import world.Timestamp;
import world.Unit;

public class EnvironmentalDamageEvent extends LogEvent implements DamageEvent {

    private final Damage damage;
    private final String reason;

    public EnvironmentalDamageEvent(Timestamp time, Unit source, Unit target, String reason, Damage damage) {
        super(time, source, target);
        this.reason = reason;
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
