package event;

import world.Timestamp;
import world.Unit;


public class EnchantAppliedEvent extends LogEvent {


    private final String spellName;
    private final Integer itemId;
    private final String itemName;

    public EnchantAppliedEvent(Timestamp time, Unit source, Unit target, String spellName, Integer itemId, String itemName) {
        super(time, source, target);
        this.spellName = spellName;
        this.itemId = itemId;
        this.itemName = itemName;
    }

    @Override
    protected String getText() {
        return target.getName() + " meurt";
    }





}
