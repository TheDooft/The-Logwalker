package world;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {

    public static final DateFormat inputDateFormat = new SimpleDateFormat("y/M/d H:m:s.S");
    public static final DateFormat outputDateFormat = new SimpleDateFormat("[HH:mm:ss.SSS]");

    static {
        inputDateFormat.setLenient(false);
    }

    private static long baseTime = 0;

    public static int getTime(String stringDate, String stringTime) throws ParseException {
        Date date = inputDateFormat.parse("2010/"+stringDate+" "+stringTime);
        Long time = date.getTime();
        if (baseTime == 0)
        	baseTime = time;
        time -= baseTime;        
        return time.intValue();
    }
    

     public String toString(int time) {
        return outputDateFormat.format(new Date(baseTime + time));
    }


	public static String displayTime(int time) {
		Date date = new Date(time + baseTime);
		return outputDateFormat.format(date);
	}
}
