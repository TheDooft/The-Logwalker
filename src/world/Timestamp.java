package world;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {

    public static final Timestamp MIN = new Timestamp(Long.MIN_VALUE);
    public static final Timestamp MAX = new Timestamp(Long.MAX_VALUE);

    public static final DateFormat inputDateFormat = new SimpleDateFormat("y/M/d H:m:s.S");
    public static final DateFormat outputDateFormat = new SimpleDateFormat("dd/MM HH:mm:ss.S");

    static {
        inputDateFormat.setLenient(false);
    }

    private final long time;

    public Timestamp(long time) {
        this.time = time;
    }

    public Timestamp(String stringDate, String stringTime) throws ParseException {
        Date date = inputDateFormat.parse("2010/"+stringDate+" "+stringTime);
        time = date.getTime();
    }

    public boolean before(Timestamp cmpTime) {
       return time < cmpTime.getValue();
    }

    public boolean after(Timestamp cmpTime) {
        return time > cmpTime.getValue();
     }

    public boolean beforeOrEqual(Timestamp cmpTime) {
        return time <= cmpTime.getValue();
     }

     public boolean afterOrEqual(Timestamp cmpTime) {
         return time >= cmpTime.getValue();
      }

    public long getValue() {
        return time;
    }

    @Override
    public String toString() {
        return outputDateFormat.format(new Date(time));
    }



}
