package event;

public class UnitDiedEvent extends LogEvent {

    private final String unitName;

    public UnitDiedEvent(String date, String time, String unitName) {
        super(date, time);
        this.unitName = unitName;

    }

    @Override
    protected String getText() {
        return unitName + " meurt";
    }





}
