package world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import report.Fight;
import report.ReportEngine;
import tools.SpecialCaseManager;

public class Unit {
	private static final long AFFILIATION_MINE = 0x00000001L;
	private static final long AFFILIATION_PARTY = 0x00000002L;
	private static final long AFFILIATION_RAID = 0x00000004L;
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

	private final long uid;
	private final String name;
	private final UnitType unitType;
	private final int spawnCounter;
	private final int id;
	private long flags;
	private long flags2;
	private Unit owner;
	private ArrayList<Unit> summonList;
	private ArrayList<Unit> fightSummonList;
	private static Map<Long, Unit> instanceList;
	private String tooltip;

	public Unit(String guid, String name, long flags, long flags2) {
		this.name = new String(name);
		this.flags = flags;
		this.flags2 = flags2;
		this.uid = parseUid(guid);
		this.summonList = new ArrayList<Unit>();
		this.fightSummonList = this.summonList;
		int type = Integer.parseInt(guid.substring(2, 5), 16);
		spawnCounter = Integer.parseInt(guid.substring(12, 18), 16);
		type &= 0x007;
		this.owner = nil;
		switch (type) {
		case 0:
			unitType = UnitType.player;
			id = 0;
			break;
		case 1:
			unitType = UnitType.worldObject;
			id = Integer.parseInt(guid.substring(5, 12), 16);
			break;
		case 3:
			unitType = UnitType.npc;
			id = Integer.parseInt(guid.substring(6, 10), 16);
			break;
		case 4:
			unitType = UnitType.pet;
			id = Integer.parseInt(guid.substring(6, 10), 16);
			break;
		case 5:
			unitType = UnitType.vehicles;
			id = Integer.parseInt(guid.substring(6, 10), 16);
			break;
		default:
			unitType = UnitType.unknow;
			id = 0;
		}
	}

	static public Unit getInstance(String guid, String name, long flags,
			long flags2) {
		Unit unit;
		if (instanceList == null)
			instanceList = new HashMap<Long, Unit>();
		long uid = parseUid(guid);
		if (uid == 0)
			return nil;
		if (instanceList.containsKey(uid) == true) {
			unit = instanceList.get(uid);
			if (unit.isReactionNeutral())
				unit.updateFlags(flags, flags2);
		} else {
			unit = new Unit(guid, name, flags, flags2);
			instanceList.put(uid, unit);
		}
		if (unit.getUnitType() != UnitType.player
				&& unit.getSpawnCounter() == 0) {
			List<Unit> list = new ArrayList<Unit>(instanceList.values());
			for (int i = list.size() - 1; i >= 0; i--)
				if (unit.getName().equals(list.get(i).getName())) {
					unit = list.get(i);
					break;
				}
		}
		SpecialCaseManager specialCaseManager = SpecialCaseManager
				.getInstance();
		Unit summoner = specialCaseManager.getSpecialSummon(unit.getId());
		if (summoner != Unit.nil) {
			unit.setOwner(summoner);
			summoner.addSummon(unit);
		}
		return unit;
	}

	public void update() {
		updateFightSummonList();
		updateTooltip();
	}

	public void updateFightSummonList() {
		ReportEngine reportEngine = ReportEngine.getInstance();
		Fight currentFight = reportEngine.getCurrentFight();
		fightSummonList = new ArrayList<Unit>();
		for (Unit unit : summonList) {
			if (currentFight.isActive(unit))
				fightSummonList.add(unit);
		}
	}

	public void updateTooltip() {
		tooltip = new String("<html>");

		tooltip += "<b>" + this.name + "</b>";
		if (spawnCounter > 0)
			tooltip += " #" + Integer.toHexString(this.spawnCounter);
		tooltip += "<br/>";
		tooltip += "<i>" + Long.toHexString(this.uid) + "</i><br/>";
		tooltip += this.unitType.name() + "<br/>";
		if (id > 0)
			tooltip += "ID : " + this.id + "<br/>";
		tooltip += "Flags : " + Long.toHexString(flags) + " / "
				+ Long.toHexString(flags2) + "<br/>";
		if (owner != Unit.nil)
			tooltip += "owned by " + owner.getName() + "<br/>";
		if (!this.fightSummonList.isEmpty()) {
			tooltip += "owns :<br/>";
			for (Unit unit : this.fightSummonList) {
				tooltip += "&nbsp- " + unit.getName() + " #"
						+ Integer.toHexString(unit.getSpawnCounter()) + "<br/>";
			}
		}

		tooltip += "</html>";
	}

	public String getTooltip() {
		return tooltip;
	}

	private void updateFlags(long flags, long flags2) {
		this.flags = flags;
		this.flags2 = flags2;
	}

	public void setOwner(Unit owner) {
		this.owner = owner;
	}

	public Unit getOwner() {
		return owner;
	}

	private static long parseUid(String guid) {
		return Long.parseLong(guid.substring(5), 16);
	}

	public String getName() {
		return name;
	}

	public long getUid() {
		return uid;
	}

	public int getId() {
		return id;
	}

	public int getSpawnCounter() {
		return spawnCounter;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public long getFlags2() {
		return flags2;
	}

	public static Unit nil = new Unit("0x0000000000000000", "nil", 0, 0);

	public boolean isTypeObject() {
		return ((flags & TYPE_MASK) == TYPE_OBJECT);
	}

	public boolean isTypeGuardian() {
		return ((flags & TYPE_MASK) == TYPE_GUARDIAN);
	}

	public boolean isTypePet() {
		return ((flags & TYPE_MASK) == TYPE_PET);
	}

	public boolean isTypeNpc() {
		return ((flags & TYPE_MASK) == TYPE_NPC);
	}

	public boolean isTypePlayer() {
		return ((flags & TYPE_MASK) == TYPE_PLAYER);
	}

	public boolean isControlPlayer() {
		return ((flags & CONTROL_MASK) == CONTROL_PLAYER);
	}

	public boolean isControlNpc() {
		return ((flags & CONTROL_MASK) == CONTROL_NPC);
	}

	public boolean isReactionHostile() {
		return ((flags & REACTION_MASK) == REACTION_HOSTILE);
	}

	public boolean isReactionNeutral() {
		return ((flags & REACTION_MASK) == REACTION_NEUTRAL);
	}

	public boolean isReactionFriendly() {
		return ((flags & REACTION_MASK) == REACTION_FRIENDLY);
	}

	public boolean isControlAffOutsider() {
		return ((flags & AFFILIATION_MASK) == AFFILIATION_OUTSIDER);
	}

	public boolean isControlAffRaid() {
		return ((flags & AFFILIATION_MASK) == AFFILIATION_RAID);
	}

	public boolean isControlAffParty() {
		return ((flags & AFFILIATION_MASK) == AFFILIATION_PARTY);
	}

	public boolean isControlAffMine() {
		return ((flags & AFFILIATION_MASK) == AFFILIATION_MINE);
	}

	public void addSummon(Unit target) {
		this.summonList.add(target);
	}

	public ArrayList<Unit> getSummonList() {
		return summonList;
	}

}
