package world;




public class TimeInterval {

    private final Timestamp beginTime;
    private final Timestamp endTime;

    public TimeInterval(Timestamp beginTime, Timestamp endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public boolean intersect(TimeInterval timeInterval) {
        return contains(timeInterval.beginTime) || contains(timeInterval.endTime) || timeInterval.contains(beginTime) || timeInterval.contains(endTime);
    }

    private boolean contains(Timestamp time) {
        return beginTime.beforeOrEqual(time) && endTime.afterOrEqual(time);
    }

    public long getDuration() {
        return endTime.getValue() - beginTime.getValue();
    }

    public static String print(long duration) {
       return String.format("%d min %d s %s ms", duration/60000, (duration%60000/1000), duration%1000);
    }

    public static long getDuration(Timestamp time1, Timestamp time2) {
        return time2.getValue() - time1.getValue();
    }

}
