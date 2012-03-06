package event;

import world.Timestamp;
import world.Unit;

public class PartyKillEvent extends LogEvent {


    public PartyKillEvent(Timestamp time, Unit source, Unit target) {
        super(time, source, target);
    }

    @Override
    protected String getText() {
        return source.getName() + " party kill "+ target.getName();
    }





}
