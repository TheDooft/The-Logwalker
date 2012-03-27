package event;

import world.Unit;

public class PartyKillEvent extends LogEvent {


    public PartyKillEvent(int time, Unit source, Unit target) {
        super(time, source, target);
    }

    @Override
	public String getText() {
        return source.getName() + " party kill "+ target.getName();
    }





}
