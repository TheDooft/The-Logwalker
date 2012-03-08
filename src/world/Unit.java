package world;

import java.util.ArrayList;
import java.util.List;

import report.UnitActivity;


public class Unit {

    private static final long AFFILIATION_OUTSIDER = 0x00000008L;
    private static final long AFFILIATION_MASK = 0x0000000FL;

    private static final long REACTION_FRIENDLY = 0x00000010L;
    private static final long REACTION_NEUTRAL = 0x00000020L;
    private static final long REACTION_HOSTILE = 0x00000040L;
    private static final long REACTION_MASK = 0x000000F0L;

    private static final long CONTROL_PLAYER = 0x00000100L;
    private static final long CONTROL_NPC = 0x00000200L;
    private static final long CONTROL_MASK = 0x00000300L;

    private static final long TYPE_PLAYER = 0x00000400L;
    private static final long TYPE_NPC = 0x00000800L;
    private static final long TYPE_PET = 0x00001000L;
    private static final long TYPE_GUARDIAN = 0x00002000L;
    private static final long TYPE_OBJECT = 0x00004000L;
    private static final long TYPE_MASK = 0x0000FC00L;

    private static final long NONE = 0x80000000L;
    private static final long SPECIAL_MASK = 0xFFFF0000L;


    private final String guid;
    private final String name;
    private final long flags;
	private List<UnitActivity> activities;

    public Unit(String guid, String name, long flags) {
        this.guid = guid;
        this.name = name;
        this.flags = flags;
        this.activities = new ArrayList<UnitActivity>();
        this.activities.add(new UnitActivity(this));

    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((guid == null) ? 0 : guid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Unit other = (Unit) obj;
        if (guid == null) {
            if (other.guid != null)
                return false;
        } else if (!guid.equals(other.guid))
            return false;
        return true;
    }




    public static Unit nil = new Unit("0000000000000000", "nil", 0);

    public boolean isPlayer() {
        return ((flags & CONTROL_MASK) == CONTROL_PLAYER) && ((flags & TYPE_MASK) == TYPE_PLAYER);
    }

    public boolean isMob() {
        return ((flags & REACTION_MASK) == REACTION_HOSTILE) && ((flags & TYPE_MASK) == TYPE_NPC);
    }

	public UnitActivity getLastActivity() {
		return activities.get(activities.size() -1);
	}

	public UnitActivity newActivity() {
		UnitActivity activity = new UnitActivity(this);
		this.activities.add(activity);
		return activity;
	}

	public List<UnitActivity> getActivities() {
		return activities;
	}





}
