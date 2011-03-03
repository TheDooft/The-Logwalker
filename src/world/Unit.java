package world;

public class Unit {

    private final String guid;
    private final String name;
    private final long flags;

    public Unit(String guid, String name, long flags) {
        this.guid = guid;
        this.name = name;
        this.flags = flags;

    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }


    public static Unit nil = new Unit("0000000000000000", "nil", 0);



}
